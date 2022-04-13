package chess.model.piece;

import chess.model.ChessModel;
import chess.model.Tile;
import grid.Location;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {

    public Knight(Color color) {
        super(color);
    }

    @Override
    public Type getPiece() {
        return Type.KNIGHT;
    }

    @Override
    public List<Location> getValidMoves(ChessModel board, Location start) {

        List<Location> possibleMoves = getPossibleMoves(board, start);

        List<Location> validMoves = new ArrayList<>();
        for (Location loc : possibleMoves) {
            if (board.isOnBoard(loc)) {
                if (board.getTile(loc) == null) {
                    validMoves.add(loc);
                }
                else if (board.getTile(loc).piece.isWhite() != isWhite()) {
                    validMoves.add(loc);
                }
            }
        }
        return validMoves;
    }

    private List<Location> getPossibleMoves(ChessModel board, Location start) {

        List<Location> moves = new ArrayList<>();

        moves.add(new Location(start.row+2, start.col+1));
        moves.add(new Location(start.row+2, start.col-1));
        moves.add(new Location(start.row+1, start.col+2));
        moves.add(new Location(start.row+1, start.col-2));
        moves.add(new Location(start.row-1, start.col+2));
        moves.add(new Location(start.row-1, start.col-2));
        moves.add(new Location(start.row-2, start.col+1));
        moves.add(new Location(start.row-2, start.col-1));

        return moves;
    }

    @Override
    public boolean canMove(ChessModel board, Location start, Location end) {
        if (getValidMoves(board, start).contains(end)) {
            return true;
        }
        return false;
    }

}
