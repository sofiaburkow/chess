package chess.model;

import chess.model.piece.IPiece;
import chess.model.piece.Type;

public class Tile {

    public final IPiece piece;

    private boolean castleMove;
    private boolean enPassant;

    public Tile(IPiece piece) {
        this.piece = piece;
        this.enPassant = false;
        this.castleMove = false;
    }

    public Type getPiece() {
        return piece.getPiece();
    }

    /**
     * @return the initial of the color of the piece
     */
    public String getPieceColor() {
        if (piece.isWhite()) {
            return "W";
        }
        return "B";

        //return null;
    }

    public boolean isEnPassant() {
        return enPassant;
    }

    public void setEnPassant(boolean enPassant) {
        this.enPassant = enPassant;
    }

    public boolean isCastleMove() {
        return this.castleMove;
    }

    public void setCastleMove(boolean castleMove) {
        this.castleMove = castleMove;
    }

    public boolean isEmpty() {
        if (piece == null) {
            return true;
        }
        return false;
    }
}
