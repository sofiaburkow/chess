package chess.model;

import grid.GridLocationIterator;
import grid.Location;

import java.util.ArrayList;

public class ChessModel {

    private ChessBoard board;
    private ArrayList<Player> players;
    private int currentIndex;

    public ChessModel () {
        this.board = new ChessBoard(8,8, null);
        players = new ArrayList<>(2);
        players.add(Player.WHITE);
        players.add(Player.BLACK);
        currentIndex = 0;
        board.initializeBoard();
    }

    public Tile getTile(Location location) {
        return board.get(location);
    }

    public void setTile(Location loc, Tile tile) {
        board.set(loc, tile);
    }

    public int numRows() {
        return board.numRows();
    }

    public int numColumns() {
        return board.numColumns();
    }

    public GridLocationIterator locations() {
        return board.locations();
    }

    public boolean isOnBoard(Location loc) {
        return board.isOnGrid(loc);
    }

    /**
     * @return the player whose turn it is now
     */
    public Player getCurrentPlayer() {
        return players.get(currentIndex);
    }

    /**
     * advances the list of players to the next player
     */
    public Player nextPlayer() {
        currentIndex = (currentIndex+1) % 2;
        return getCurrentPlayer();
    }

}
