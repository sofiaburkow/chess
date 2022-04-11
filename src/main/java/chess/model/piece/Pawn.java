package chess.model.piece;

import chess.model.ChessBoard;
import chess.model.ChessModel;
import chess.model.Tile;
import grid.GridDirection;
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
    public List<Location> getPossibleMoves(ChessModel board, Location start) {

        List<Location> moves = new ArrayList<>();

        getAdvanceMoves(board, start, moves);
        getCaptureMoves(board, start, moves);

        return moves;
    }

    private void getAdvanceMoves(ChessModel board, Location loc, List<Location> moves) {
        int move;
        if (isWhite()) {
            move = -1;
        } else {
            move = 1;
        }
        Location advanceOne = new Location(loc.row+move, loc.col);
        if (board.isOnBoard(advanceOne)) {
            if (board.getTile(advanceOne) != null) {
                moves.add(advanceOne);
            }
        }
        // if the pawn has yet to move
        if ((isWhite() && loc.row == 6) || (!isWhite() && loc.row == 1) ) {
            Location advanceTwo = new Location(loc.row+move, loc.col);
            if (board.isOnBoard(advanceTwo) && (board.getTile(advanceTwo) == null)) {
                moves.add(advanceTwo);
            }
        }
    }

    private void getCaptureMoves(ChessModel board, Location loc, List<Location> moves) {
        int move;
        if (isWhite()) {
            move = -1;
        } else {
            move = 1;
        }
        Location captureNorthWest = new Location(loc.row+move, loc.col-1);
        if (board.isOnBoard(captureNorthWest)) {
            if (board.getTile(captureNorthWest) != null) {
                if (board.getTile(captureNorthWest).piece.getPlayer() != getPlayer()) {
                    moves.add(captureNorthWest);
                }
            }
        }
        Location captureNorthEast = new Location(loc.row+move, loc.col+1);
        if (board.isOnBoard(captureNorthEast)) {
            if (board.getTile(captureNorthEast) != null) {
                if (board.getTile(captureNorthEast).piece.getPlayer() != getPlayer()) {
                    moves.add(captureNorthEast);
                }
            }
        }
    }

    @Override
    public boolean canMove(ChessModel board, Location start, Location end) {
        List<Location> possibleMoves = getPossibleMoves(board, start);
        if (possibleMoves.contains(end)) {
            return true;
        }
        return false;
    }

}
