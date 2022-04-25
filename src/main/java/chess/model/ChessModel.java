package chess.model;

import grid.GridLocationIterator;
import grid.Location;

import java.util.ArrayList;
import java.util.List;

public class ChessModel {

    private ChessBoard board;
    private ArrayList<Team> players;
    private int currentIndex;
    private List<Move> moveHistory;

    public ChessModel () {
        this.board = new ChessBoard(8,8, new Tile(null));
        this.moveHistory = new ArrayList<>();

        players = new ArrayList<>(2);
        players.add(Team.WHITE);
        players.add(Team.BLACK);
        currentIndex = 0;

        board.initializeBoard();
    }

    public List<Move> getMoveHistory() {
        return this.moveHistory;
    }

    public void addMoveToMoveHistory(Move move) {
        moveHistory.add(move);
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

    public Team getCurrentPlayer() {
        return players.get(currentIndex);
    }

    public Team nextPlayer() {
        currentIndex = (currentIndex+1) % 2;
        return getCurrentPlayer();
    }

    public boolean validMove(Move move) {
        if (this.getTile(move.source).isEmpty()) {
            return false;
        }
        return this.getTile(move.source).piece.canMove(this, move);
    }

    /**
     * Move the piece from the source tile to the destination tile and empty the source tile.
     */
    public boolean movePiece(Move move) {
        if (validMove(move)) {
            this.setTile(move.destination, this.getTile(move.source));
            this.setTile(move.source, new Tile(null));
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

    public void enPassantMove(Move move) {
        Tile kingTile = this.getTile(move.source);
        this.setTile(move.destination, kingTile);
        this.setTile(move.source, new Tile(null));
        if (move.destination.row == 2) {
            this.setTile(new Location(move.destination.row+1, move.destination.col), new Tile(null));
        } else {
            this.setTile(new Location(move.destination.row-1, move.destination.col), new Tile(null));
        }
    }

}
