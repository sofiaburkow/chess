package chess.model;

import grid.GridLocationIterator;
import grid.Location;

import java.awt.*;

public class ChessModel {

    private ChessBoard board;

    //private Graphics graphics;

    public ChessModel () {
        this.board = new ChessBoard(8,8, null);
        board.initiateBoard();
    }

    public Tile get(Location location) {
        return board.get(location);
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

}
