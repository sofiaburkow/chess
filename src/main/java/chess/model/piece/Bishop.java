package chess.model.piece;

import chess.model.ChessModel;
import chess.model.Tile;
import grid.Location;

import java.awt.*;
import java.util.List;

public class Bishop extends Piece {

    public Bishop(Color color) {
        super(color);
    }

    @Override
    public Type getPiece() {
        return Type.BISHOP;
    }

    @Override
    public List<Location> getPossibleMoves(ChessModel board, Location start) {
        return null;
    }

    @Override
    public boolean canMove(ChessModel board, Location start, Location end) {
        return false;
    }

}
