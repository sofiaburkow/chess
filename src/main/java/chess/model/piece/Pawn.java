package chess.model.piece;

import chess.model.ChessModel;
import chess.model.Tile;
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

        List<Location> possibleMoves = new ArrayList<>();

        if (isWhite()) {
            if (board.getTile(new Location(start.row-1, start.col)) == null) {
                possibleMoves.add(new Location(start.row-1, start.col));
            }
            if (start.row == 6) {
                possibleMoves.add(new Location(start.row-2, start.col));
            }
            if (!board.getTile(new Location(start.row-1, start.col-1)).piece.isWhite()) {
                possibleMoves.add(new Location(start.row-1, start.col-1));
            }
            if (!board.getTile(new Location(start.row-1, start.col+1)).piece.isWhite()) {
                possibleMoves.add(new Location(start.row-1, start.col+1));
            }
        } else {
            possibleMoves.add(new Location(start.row+1, start.col));
            if (start.row == 1) {
                possibleMoves.add(new Location(start.row+2, start.col));
            }
        }

        for (Location loc : possibleMoves) {
            if (board.getTile(loc) == null) {
                possibleMoves.remove(loc);
            }
        }
        return possibleMoves;
    }

    private boolean emptyTile(ChessModel board, Location loc) {
       return board.getTile(loc) == null;
    }

    private boolean newLocation(Location loc, int deltaRow, int deltaCol) {


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
