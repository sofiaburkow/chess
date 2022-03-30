package chess.model.piece;

import chess.model.piece.IPiece;

import java.awt.Color;

public class Piece implements IPiece {

    private Color color;
    private boolean killed = false;

    public Piece (Color color) {
        this.color = color;

    }

    @Override
    public boolean isWhite() {
        return this.color == Color.white;
    }

    @Override
    public void setWhite() {
        // a check
        this.color = Color.white;
    }

    @Override
    public boolean isKilled() {
        return this.killed;
    }

    @Override
    public void setKilled() {
        // a check
        this.killed = true;
    }

    @Override
    public boolean isValidMove() {
        return false;
    }
}
