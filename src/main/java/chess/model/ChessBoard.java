package chess.model;

import grid.Grid;
import grid.GridLocationIterator;

public class ChessBoard {

    private Grid<Spot> grid;

    public ChessBoard(int rows, int cols, Spot element) {
        grid = new Grid<>(rows, cols, element);
    }

    public int numRows() {
        return grid.numRows();
    }

    public int numColumns() {
        return grid.numColumns();
    }

    public GridLocationIterator locations() {
        return grid.locations();
    }


}
