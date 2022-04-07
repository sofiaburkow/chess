package chess.model.piece;

import grid.Location;

import java.awt.*;

public class Bishop extends Piece {
    public Bishop(Color color) {
        super(color);
    }

    @Override
    public Pieces getPiece() {
        return Pieces.BISHOP;
    }
}
