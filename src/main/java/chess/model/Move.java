package chess.model;

import grid.Location;

public class Move {

    boolean castle;
    boolean enPassant;

    public Move(boolean castle, boolean enPassant) {
        this.castle = castle;
        this.enPassant = enPassant;
    }

    public boolean isEnPassant() {
        return enPassant;
    }

    public boolean canCastle() {
        return castle;
    }

}
