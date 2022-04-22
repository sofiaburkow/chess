package chess.GUI;

import chess.model.Player;
import chess.model.Tile;
import grid.GridLocationIterator;
import grid.Location;

public interface IMovable {

    Tile getTile(Location loc);

    void setTile(Location loc, Tile tile);

    int numRows();

    int numColumns();

    GridLocationIterator locations();

    /**
     * @return the player whose turn it is now
     */
    Player getCurrentPlayer();

    /**
     * advances the list of players to the next player
     */
    Player nextPlayer();

    boolean validMove(Location source, Location destination);

    /**
     * Move the piece from the source tile, to the destination tile.
     */
    boolean movePiece(Location source, Location destination);

}
