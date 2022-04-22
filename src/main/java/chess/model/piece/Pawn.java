package chess.model.piece;

import chess.model.ChessModel;
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
    public List<Location> getValidMoves(ChessModel board, Location start) {

        List<Location> moves = new ArrayList<>();

        addAdvanceMoves(board, start, moves);
        addCaptureMoves(board, start, moves);
        //addEnPassant(board, start, moves);

        return moves;
    }

    /**
     * Add valid moves to the list of possible moves where the pawn advances on the board.
     */
    private void addAdvanceMoves(ChessModel board, Location loc, List<Location> moves) {
        int move;
        if (isWhite()) {
            move = -1;
        } else {
            move = 1;
        }
        Location advanceOne = new Location(loc.row + move, loc.col);
        if (board.isOnBoard(advanceOne)) {
            if (board.getTile(advanceOne).isEmpty()) {
                moves.add(advanceOne);
            }
        }
        // if the pawn has yet to move
        if ((isWhite() && loc.row == 6) || (!isWhite() && loc.row == 1)) {
            Location advanceTwo = new Location(loc.row + move * 2, loc.col);
            if (board.isOnBoard(advanceTwo)) {
                if (board.getTile(new Location(loc.row + move * 1, loc.col)).isEmpty() && (board.getTile(advanceTwo).isEmpty())) {
                    moves.add(advanceTwo);
                }
            }
        }
    }


    /**
     * Add moves where the pawn captures an opponents piece to the list of possible moves.
     */
    private void addCaptureMoves(ChessModel board, Location loc, List<Location> moves) {
        int move;
        if (isWhite()) {
            move = -1;
        } else {
            move = 1;
        }
        Location captureNorthWest = new Location(loc.row+move, loc.col-1);
        if (board.isOnBoard(captureNorthWest)) {
            if (!board.getTile(captureNorthWest).isEmpty()) {
                if (board.getTile(captureNorthWest).piece.getPlayer() != getPlayer()) {
                    moves.add(captureNorthWest);
                }
            }
        }
        Location captureNorthEast = new Location(loc.row+move, loc.col+1);
        if (board.isOnBoard(captureNorthEast)) {
            if (!board.getTile(captureNorthEast).isEmpty()) {
                if (board.getTile(captureNorthEast).piece.getPlayer() != getPlayer()) {
                    moves.add(captureNorthEast);
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
