package chess.model.piece;

import chess.model.IBoard;
import chess.model.Move;
import grid.Location;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

    private int direction;

    public Pawn(Color color) {
        super(color);
        // which way to move on the board according to the color of the piece
        if (isWhite()) {
            direction = -1;
        } else {
            direction = 1;
        }
    }

    @Override
    public Type getPiece() {
        return Type.PAWN;
    }

    @Override
    public List<Move> getValidMoves(IBoard board, Location source) {

        List<Move> moves = new ArrayList<>();

        addAdvanceMoves(board, source, moves);
        addCaptureMoves(board, source, moves);
        addEnPassant(board, source, moves);

        return moves;
    }

    /**
     * Add valid moves where the pawn advances on the board to the list of possible moves.
     */
    private void addAdvanceMoves(IBoard board, Location source, List<Move> moves) {
        // advance one tile
        Location advanceOne = new Location(source.row+direction, source.col);
        if (board.isOnGrid(advanceOne)) {
            if (board.get(advanceOne).isEmpty()) {
                Move move = new Move(source, advanceOne);
                move.setNotCaptureMove(true);
                moves.add(move);
            }
        }
        // advance two tiles
        if (!this.hasMovedBefore) {
            Location advanceTwo = new Location(source.row+direction*2, source.col);
            if (board.isOnGrid(advanceTwo)) {
                if (board.get(new Location(source.row+direction*1, source.col)).isEmpty() && (board.get(advanceTwo).isEmpty())) {
                    Move move = new Move(source, advanceTwo);
                    // necessary to keep track of this when trying to figure out if a king move results in check
                    move.setNotCaptureMove(true);
                    moves.add(move);
                }
            }
        }
    }

    /**
     * Add moves where the pawn can capture an opponents piece diagonally.
     */
    private void addCaptureMoves(IBoard board, Location source, List<Move> moves) {
        List<Location> captureMoves = new ArrayList<>();
        captureMoves.add(new Location(source.row+direction, source.col-1));
        captureMoves.add(new Location(source.row+direction, source.col+1));
        for (Location loc : captureMoves) {
            if (board.isOnGrid(loc)) {
                if (!board.get(loc).isEmpty() && board.get(loc).piece.getTeam() != getTeam()) {
                    moves.add(new Move(source, loc));
                }
            }
        }
    }

    /**
     * Add en passant moves to the list of valid moves and set the en passant field variable
     * of the destination tile to true.
     */
    private void addEnPassant(IBoard board, Location source, List<Move> moves) {
        if (board.getMoveHistory().size() >= 1) {
            Move previousMove = board.getMoveHistory().get(board.getMoveHistory().size()-1);
            if (isEnPassant(board, source, previousMove)) {
                Location enPassantLocation = new Location(previousMove.destination.row+direction, previousMove.destination.col);
                moves.add(new Move(source, enPassantLocation));
                board.get(enPassantLocation).setEnPassant(true);
            } else { // make sure that a tile is not set to en passant for more than one round
                for (Location loc : board.locations()) {
                    board.get(loc).setEnPassant(false);
                }
            }
        }
    }

    /**
     * Check whether the previous move results in the possibility of en passant.
     */
    private boolean isEnPassant(IBoard board, Location source, Move previousMove) {
        List<Location> neighbourPawn = new ArrayList<>();
        neighbourPawn.add(new Location(source.row, source.col-1));
        neighbourPawn.add(new Location(source.row, source.col+1));
        for (Location loc : neighbourPawn) {
            if (board.isOnGrid(loc) && !board.get(loc).isEmpty() && loc.equals(previousMove.destination)) {
                if (board.get(loc).piece.getPiece().equals(Type.PAWN) && board.get(loc).piece.isWhite() != isWhite()) {
                    if (Math.abs(previousMove.destination.row - previousMove.source.row) == 2) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
