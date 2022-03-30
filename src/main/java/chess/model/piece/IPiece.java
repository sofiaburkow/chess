package chess.model.piece;

public interface IPiece {

    /**
     * The color of the piece.
     *
     * @return true if white, false if black
     */
    boolean isWhite();

    /**
     * Sets the color of the piece to white.
     */
    void setWhite();

    /**
     * Check if the piece is still in the game or not.
     *
     * @return true if killed, return false if the piece is still alive.
     */
    boolean isKilled();

    /**
     * Sets the state of the given piece to killed.
     */
    void setKilled();

    /**
     * Checks whether a given move is valid or not.
     *
     * @return true if the move is valid, if not valid then return false.
     */
    boolean isValidMove();

}
