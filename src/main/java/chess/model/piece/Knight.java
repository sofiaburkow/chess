package chess.model.piece;

import chess.model.ChessModel;
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
            if (isValidMove(board,loc)) {
                validMoves.add(loc);
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

}
