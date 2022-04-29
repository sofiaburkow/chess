package chess.model;

import grid.GridLocationIterator;
import grid.Location;

import java.util.List;

public interface IBoard {

    /**
     * @return number of rows on the board.
     */
    int numRows();

    /**
     * @return number of columns on the board.
     */
    int numColumns();

    /**
     * Get the tile on the given location.
     */
    Tile get(Location loc);

    /**
     * Iterate over all grid locations
     */
    GridLocationIterator locations();

    /**
     * Check whether the given location is within the bounds of the board.
     *
     * @return true if the location is within bounds, false otherwise.
     */
    boolean isOnBoard(Location loc);

    /**
     * @return a list of previous moves.
     */
    List<Move> getMoveHistory();

    /**
     * Iterate over the tiles on the board, and make a list of all potential locations that
     * a piece of the opposite team might move if it was that teams turn.
     */
    List<Location> tilesUnderAttack(ChessBoard board);

    /**
     * Check whether the king of the current team is in check or not.
     *
     * @return true if check, otherwise false.
     */
    boolean isCheck(ChessBoard board);

}
