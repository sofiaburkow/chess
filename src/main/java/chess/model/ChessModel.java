package chess.model;

import chess.GUI.IMovable;
import grid.GridLocationIterator;
import grid.Location;

import java.util.ArrayList;

public class ChessModel implements IMovable {

    private ChessBoard board;
    private ArrayList<Player> players;
    private int currentIndex;

    public ChessModel () {
        this.board = new ChessBoard(8,8, new Tile(null));

        players = new ArrayList<>(2);
        players.add(Player.WHITE);
        players.add(Player.BLACK);
        currentIndex = 0;

        board.initializeBoard();
    }

    @Override
    public Tile getTile(Location loc) {
        return board.get(loc);
    }

    @Override
    public void setTile(Location loc, Tile tile) {
        board.set(loc, tile);
    }

    @Override
    public int numRows() {
        return board.numRows();
    }

    @Override
    public int numColumns() {
        return board.numColumns();
    }

    @Override
    public GridLocationIterator locations() {
        return board.locations();
    }

    @Override
    public Player getCurrentPlayer() {
        return players.get(currentIndex);
    }

    @Override
    public Player nextPlayer() {
        currentIndex = (currentIndex+1) % 2;
        return getCurrentPlayer();
    }

    @Override
    public boolean validMove(Location source, Location destination) {
        if (this.getTile(source).isEmpty()) {
            return false;
        }
        return this.getTile(source).piece.canMove(this, source, destination);
    }

    @Override
    public boolean movePiece(Location source, Location destination) {
        if (validMove(source, destination)) {
            Tile sourceTile = this.getTile(source);
            this.setTile(source, new Tile(null));
            this.setTile(destination, sourceTile);
            return true;
        }
        return false;
    }

    public boolean isOnBoard(Location loc) {
        return board.isOnGrid(loc);
    }

}
