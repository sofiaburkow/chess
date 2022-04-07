package chess.model.piece;

import grid.Location;

import java.awt.*;

public class Knight extends Piece {
    public Knight(Color color) {
        super(color);
    }

    @Override
    public Pieces getPiece() {
        return Pieces.KNIGHT;
    }
}
