package chess.model.piece;

import chess.model.IBoard;
import chess.model.Move;
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
    public List<Move> getValidMoves(IBoard board, Location source) {
        List<Move> validMoves = new ArrayList<>();
        addNeighborMoves(board, source, validMoves);
        addHorizontalAndVerticalMoves(board, source, validMoves);
        addDiagonalMoves(board, source, validMoves);
        return validMoves;
    }

    private void addNeighborMoves(IBoard board, Location source, List<Move> moves) {
        Collection<Location> neighbors = source.allNeighbors();
        for (Location loc : neighbors) {
            if (isValidDestinationTile(board, loc)) {
                moves.add(new Move(source, loc));
            }
        }
    }

    private void addHorizontalAndVerticalMoves(IBoard board, Location source, List<Move> validMoves) {
        addValidMoves(board, source, 1,0, validMoves);
        addValidMoves(board, source, 0,1, validMoves);
        addValidMoves(board, source, -1,0, validMoves);
        addValidMoves(board, source, 0,-1, validMoves);
    }

    private void addDiagonalMoves(IBoard board, Location source, List<Move> validMoves) {
        addValidMoves(board, source, 1,1, validMoves);
        addValidMoves(board, source, 1,-1, validMoves);
        addValidMoves(board, source, -1,1, validMoves);
        addValidMoves(board, source, -1,-1, validMoves);
    }

}
