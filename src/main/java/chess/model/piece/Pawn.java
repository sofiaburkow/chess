package chess.model.piece;

import grid.Location;

import java.awt.*;

public class Pawn extends Piece {
    public Pawn(Color color) {
        super(color);
    }

    @Override
    public Pieces getPiece() {
        return Pieces.PAWN;
    }
}
