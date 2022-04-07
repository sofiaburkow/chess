package chess.model.piece;

import grid.Location;

import java.awt.*;

public class Queen extends Piece {
    public Queen(Color color) {
        super(color);
    }

    @Override
    public Pieces getPiece() {
        return Pieces.QUEEN;
    }
}
