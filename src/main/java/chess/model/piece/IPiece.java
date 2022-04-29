package chess.model.piece;

import chess.model.IBoard;
import chess.model.Move;
import chess.model.Team;
import grid.Location;

import java.util.List;

public interface IPiece {

    /**
     * Keeps track of whether the piece has moved before or not.
     *
     * @return true if the piece has moved before, otherwise return false.
     */
    boolean hasMovedBefore();

    /**
     * Set the value of the boolean variable hasMovedBefore to either true or false.
     */
    void setHasMovedBefore(boolean hasMovedBefore);

    /**
     * @return true if the piece is white, otherwise return false.
     */
    boolean isWhite();

    /**
     * @return WHITE if the piece is on the white team, otherwise return BLACK.
     */
    Team getTeam();

    /**
     * The initials of the color of the piece.
     *
     * @return "W" if the piece is white, otherwise "B"
     */
    String getPieceColor();

    /**
     * @return the type of the given piece.
     */
    Type getPiece();

    /**
     * List of valid moves according to the state of the board and the given piece.
     *
     * @return list of valid moves.
     */
    List<Move> getValidMoves(IBoard board, Location source);

    /**
     * Checks whether a given move is valid or not.
     *
     * @return true if the move is valid, otherwise return false.
     */
    boolean canMove(IBoard board, Move move);

}
