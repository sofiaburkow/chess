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
        //addEnPassant(board, start, moves);

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
                moves.add(new Move(source, advanceOne, false, false));
            }
        }
        // if the pawn has yet to move
        if ((isWhite() && source.row == 6) || (!isWhite() && source.row == 1)) {
            Location advanceTwo = new Location(source.row + move * 2, source.col);
            if (board.isOnBoard(advanceTwo)) {
                if (board.getTile(new Location(source.row + move * 1, source.col)).isEmpty() && (board.getTile(advanceTwo).isEmpty())) {
                    moves.add(new Move(source, advanceTwo, false, false));
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
                if (board.getTile(captureNorthWest).piece.getPlayer() != getPlayer()) {
                    moves.add(new Move(source, captureNorthWest, false, false));
                }
            }
        }
        Location captureNorthEast = new Location(source.row+move, source.col+1);
        if (board.isOnBoard(captureNorthEast)) {
            if (!board.getTile(captureNorthEast).isEmpty()) {
                if (board.getTile(captureNorthEast).piece.getPlayer() != getPlayer()) {
                    moves.add(new Move(source, captureNorthEast, false, false));
                }
            }
        }
    }


    private void addEnPassant(ChessModel board, Location loc, List<Location> moves) {
        if (board.getTile(loc).isEnPassant()) {

        }



        int move = 0;

        List<Location> enPassant = new ArrayList<>();
        enPassant.add(new Location(loc.row, loc.col-1));
        enPassant.add(new Location(loc.row, loc.col+1));

        for (Location enPassantLoc : enPassant) {
            if (board.isOnBoard(enPassantLoc)) {
                if (board.getTile(enPassantLoc) != null) {
                    if (board.getTile(enPassantLoc).isEnPassant()) {
                        if (isWhite()) {
                            move = -1;
                        } else {
                            move = 1;
                        }
                        moves.add(new Location(enPassantLoc.row+move, enPassantLoc.col));
                    }
                }
            }
        }
    }



}
