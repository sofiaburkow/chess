package chess.model;

import grid.GridLocationIterator;
import grid.Location;

import java.util.ArrayList;

public class ChessModel {

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

    public Tile getTile(Location loc) {
        return board.get(loc);
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

    public Player getCurrentPlayer() {
        return players.get(currentIndex);
    }

    public Player nextPlayer() {
        currentIndex = (currentIndex+1) % 2;
        return getCurrentPlayer();
    }

    public boolean validMove(Location source, Location destination) {
        if (this.getTile(source).isEmpty()) {
            return false;
        }
        return this.getTile(source).piece.canMove(this, source, destination);
    }

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

    public void castleKingSideMove(Location source) {
        Tile kingTile = this.getTile(source);
        this.setTile(new Location(source.row, source.col+2), kingTile);
        this.setTile(source, new Tile(null));
        Tile rookTile = this.getTile(new Location(source.row, source.col+3));
        this.setTile(new Location(source.row, source.col+3), new Tile(null));
        this.setTile(new Location(source.row, source.col+1), rookTile);
    }

    public void castleQueenSideMove(Location source) {
        Tile kingTile = this.getTile(source);
        this.setTile(new Location(source.row, source.col-2), kingTile);
        this.setTile(source, new Tile(null));
        Tile rookTile = this.getTile(new Location(source.row, source.col-4));
        this.setTile(new Location(source.row, source.col-4), new Tile(null));
        this.setTile(new Location(source.row, source.col-1), rookTile);
    }

}
