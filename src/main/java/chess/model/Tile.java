package chess.model;

import chess.model.piece.IPiece;
import chess.model.piece.Type;

public class Tile {

    public final IPiece piece;
    private boolean castleMove;
    private boolean enPassant;
    // Represents the initial of the piece. For test purposes.
    public final char initial;

    public Tile(IPiece piece) {
        this.piece = piece;
        this.initial = '-';
        this.castleMove = false;
        this.enPassant = false;
    }

    public Tile(IPiece piece, char initial) {
        this.piece = piece;
        this.initial = initial;
        this.castleMove = false;
        this.enPassant = false;
    }

    public boolean isEmpty() {
        if (piece == null) {
            return true;
        }
        return false;
    }

    public boolean isCastleMove() {
        return this.castleMove;
    }

    public void setCastleMove(boolean castleMove) {
        this.castleMove = castleMove;
    }

    public boolean isEnPassant() {
        return enPassant;
    }

    public void setEnPassant(boolean enPassant) {
        this.enPassant = enPassant;
    }

}
