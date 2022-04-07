package chess.model.piece;

import grid.Location;

import java.awt.*;

public class Rook extends Piece {
    public Rook(Color color) {
        super(color);
    }

    @Override
    public Pieces getPiece() {
        return Pieces.ROOK;
    }
}
