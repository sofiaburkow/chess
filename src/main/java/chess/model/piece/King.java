package chess.model.piece;

import grid.Location;

import java.awt.*;

public class King extends Piece {
    public King(Color color) {
        super(color);
    }

    @Override
    public Pieces getPiece() {
        return Pieces.KING;
    }

}
