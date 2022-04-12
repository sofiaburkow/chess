package chess.model.piece;

import chess.model.ChessModel;
import chess.model.Player;
import chess.model.Tile;
import grid.Location;

import java.awt.*;
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
    List<Location> getPossibleMoves(ChessModel board, Location start);

    /**
     * Checks whether a given move is valid or not.
     *
     * @return true if move is valid, otherwise false.
     */
    boolean canMove(ChessModel board, Location start, Location end);

}
