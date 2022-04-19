package chess.model.piece;

import chess.model.ChessModel;
import grid.Location;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Queen extends Piece {

    public Queen(Color color) {
        super(color);
    }

    @Override
    public Type getPiece() {
        return Type.QUEEN;
    }

    @Override
    public List<Location> getValidMoves(ChessModel board, Location start) {
        List<Location> validMoves = new ArrayList<>();
        addNeighbors(board, start, validMoves);
        addHorizontalAndVerticalMoves(board, start, validMoves);
        addDiagonalMoves(board, start, validMoves);
        return validMoves;
    }

    private void addNeighbors(ChessModel board, Location loc, List<Location> moves) {
        Collection<Location> neighbors = loc.allNeighbors();
        for (Location location : neighbors) {
            if (isValidMove(board, location)) {
                moves.add(location);
            }
        }
    }

    private void addHorizontalAndVerticalMoves(ChessModel board, Location start, List<Location> validMoves) {
        addMoves(board, start, 1,0, validMoves);
        addMoves(board, start, 0,1, validMoves);
        addMoves(board, start, -1,0, validMoves);
        addMoves(board, start, 0,-1, validMoves);
    }

    private void addDiagonalMoves(ChessModel board, Location start, List<Location> validMoves) {
        addMoves(board, start, 1,1, validMoves);
        addMoves(board, start, 1,-1, validMoves);
        addMoves(board, start, -1,1, validMoves);
        addMoves(board, start, -1,-1, validMoves);
    }

}
