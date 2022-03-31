package chess.model;

import grid.Grid;

public class ChessBoard {

    private Grid<Spot> grid;

    public ChessBoard(int rows, int cols) {
        grid = new Grid<>(rows, cols);
    }

}
