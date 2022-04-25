package chess.model.piece;

import chess.model.ChessModel;
import chess.model.Move;
import grid.Location;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

    public Pawn(Color color) {
        super(color);
    }

    @Override
    public Type getPiece() {
        return Type.PAWN;
    }

    @Override
    public List<Move> getValidMoves(ChessModel board, Location source) {
        List<Move> moves = new ArrayList<>();
        addAdvanceMoves(board, source, moves);
        addCaptureMoves(board, source, moves);
        addEnPassant(board, source, moves);
        return moves;
    }

    /**
     * Add valid moves to the list of possible moves where the pawn advances on the board.
     */
    private void addAdvanceMoves(ChessModel board, Location source, List<Move> moves) {
        int move;
        if (isWhite()) {
            move = -1;
        } else {
            move = 1;
        }
        Location advanceOne = new Location(source.row + move, source.col);
        if (board.isOnBoard(advanceOne)) {
            if (board.getTile(advanceOne).isEmpty()) {
                moves.add(new Move(source, advanceOne));
            }
        }
        // if the pawn has yet to move
        if ((isWhite() && source.row == 6) || (!isWhite() && source.row == 1)) {
            Location advanceTwo = new Location(source.row+move*2, source.col);
            if (board.isOnBoard(advanceTwo)) {
                if (board.getTile(new Location(source.row+move*1, source.col)).isEmpty() && (board.getTile(advanceTwo).isEmpty())) {
                    Move advanceTwoMove = new Move(source, advanceTwo);
                    moves.add(advanceTwoMove);
                }
            }
        }
    }

    /**
     * Add moves where the pawn captures an opponents piece to the list of possible moves.
     */
    private void addCaptureMoves(ChessModel board, Location source, List<Move> moves) {
        int move;
        if (isWhite()) {
            move = -1;
        } else {
            move = 1;
        }
        Location captureNorthWest = new Location(source.row+move, source.col-1);
        if (board.isOnBoard(captureNorthWest)) {
            if (!board.getTile(captureNorthWest).isEmpty()) {
                if (board.getTile(captureNorthWest).piece.getTeam() != getTeam()) {
                    moves.add(new Move(source, captureNorthWest));
                }
            }
        }
        Location captureNorthEast = new Location(source.row+move, source.col+1);
        if (board.isOnBoard(captureNorthEast)) {
            if (!board.getTile(captureNorthEast).isEmpty()) {
                if (board.getTile(captureNorthEast).piece.getTeam() != getTeam()) {
                    moves.add(new Move(source, captureNorthEast));
                }
            }
        }
    }

    private void addEnPassant(ChessModel board, Location source, List<Move> moves) {
        if (board.getMoveHistory().size() >= 1) {
            Move previousMove = board.getMoveHistory().get(board.getMoveHistory().size()-1);
            if (isnEnPassant(board, source, previousMove)) {
                int move;
                if (isWhite()) {
                    move = -1;
                } else {
                    move = 1;
                }
                moves.add(new Move(source, new Location(previousMove.destination.row+move, previousMove.destination.col)));
                board.getTile(new Location(previousMove.destination.row+move, previousMove.destination.col)).setEnPassant(true);
            }
        }
    }

    private boolean isnEnPassant(ChessModel board, Location source, Move move) {
        List<Location> locEnPassant = new ArrayList<>();
        locEnPassant.add(new Location(source.row, source.col-1));
        locEnPassant.add(new Location(source.row, source.col+1));
        for (Location loc : locEnPassant) {
            if (board.isOnBoard(loc) && !board.getTile(loc).isEmpty()) {
                if (loc.equals(move.destination)) {
                    if (board.getTile(loc).piece.getPiece().equals(Type.PAWN) && board.getTile(loc).piece.isWhite() != isWhite()) {
                        if (Math.abs(move.destination.row - move.source.row) == 2) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

}
