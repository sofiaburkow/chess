package chess.model.piece;

import chess.model.ChessModel;
import chess.model.Player;
import grid.Location;

import java.util.List;

public interface IPiece {

    /**
     * The color of the piece.
     *
     * @return true if white, false if black
     */
    boolean isWhite();

    /**
     * @return whether the given piece is on the white or black team.
     */
    Player getPlayer();

    /**
     * @return the initials of the color of the piece.
     */
    String getPieceColor();

    /**
     * @return the type of the given piece.
     */
    Type getPiece();

    /**
     * List of possible moves according to the state of the board and the given piece.
     *
     * @return list of possible end locations.
     */
    List<Location> getValidMoves(ChessModel board, Location start);

    /**
     *
     * @return
     */
    boolean isValidMove(ChessModel board, Location loc);

    void addMoves(ChessModel board, Location start, int rowOperand, int columnOperand, List<Location> moves);

    /**
     * Checks whether a given move is valid or not.
     *
     * @return true if move is valid, otherwise false.
     */
    boolean canMove(ChessModel board, Location start, Location end);

    boolean hasMovedBefore();

    void setHasMovedBefore(boolean hasMovedBefore);

}
