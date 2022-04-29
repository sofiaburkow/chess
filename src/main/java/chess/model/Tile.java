package chess.model;

import chess.model.piece.IPiece;

public class Tile {

    public final IPiece piece;
    private boolean castleMove;
    private boolean enPassant;
    public final char initial;

    /**
     * Constructor if there is given no initial (if the piece is null).
     * The given piece is either one of the six different chess pieces or null.
     */
    public Tile(IPiece piece) {
        this.piece = piece;
        this.initial = '-';
        this.castleMove = false;
        this.enPassant = false;
    }

    /**
     * Constructor if there is given a char representing the initial of the piece.
     */
    public Tile(IPiece piece, char initial) {
        this.piece = piece;
        this.initial = initial;
        this.castleMove = false;
        this.enPassant = false;
    }

    /**
     * @return true if the tile is empty (no piece on it), otherwise false.
     */
    public boolean isEmpty() {
        if (piece == null) {
            return true;
        }
        return false;
    }

    /**
     * @return true if the piece on the given tile just did a castle move.
     */
    public boolean isCastleMove() {
        return this.castleMove;
    }

    /**
     * Set the field variable castleMove to either true or false.
     * Set it to true if the piece on the tile just did a castle move.
     */
    public void setCastleMove(boolean castleMove) {
        this.castleMove = castleMove;
    }

    /**
     * @return if the piece/pawn on the given tile just did an en passant move.
     */
    public boolean isEnPassant() {
        return enPassant;
    }

    /**
     * Set the field variable enPassant to true if the piece on the tile just did an
     * en passant move.
     */
    public void setEnPassant(boolean enPassant) {
        this.enPassant = enPassant;
    }

}
