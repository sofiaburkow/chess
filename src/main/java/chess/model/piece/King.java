package chess.model.piece;

import chess.model.ChessModel;
import chess.model.Tile;
import grid.Location;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class King extends Piece {

    public King(Color color) {
        super(color);
    }

    @Override
    public Type getPiece() {
        return Type.KING;
    }

    @Override
    public List<Location> getValidMoves(ChessModel board, Location start) {
        List<Location> validMoves = new ArrayList<>();
        Collection<Location> neighbors = start.allNeighbors();
        for (Location loc : neighbors) {
            if (isValidMove(board, loc)) {
                validMoves.add(loc);
            }
        }
        return validMoves;
    }

}
