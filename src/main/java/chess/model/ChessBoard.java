package chess.model;

import chess.model.piece.*;
import grid.Grid;
import grid.GridLocationIterator;
import grid.Location;

import java.awt.*;

public class ChessBoard {

    private Grid<IPiece> board;

    public ChessBoard(int rows, int cols, IPiece element) {
        this.board = new Grid<>(rows, cols, element);
    }

    /**
     * Create a standard chess board.
     */
    public void createBoard() {
        // black pieces
        board.set(new Location(0,0), new Rook(Color.BLACK));
        board.set(new Location(0,1), new Knight(Color.BLACK));
        board.set(new Location(0,2), new Bishop(Color.BLACK));
        board.set(new Location(0,3), new Queen(Color.BLACK));
        board.set(new Location(0,4), new King(Color.BLACK));
        board.set(new Location(0,5), new Bishop(Color.BLACK));
        board.set(new Location(0,6), new Knight(Color.BLACK));
        board.set(new Location(0,7), new Rook(Color.BLACK));

        board.set(new Location(1,0), new Pawn(Color.BLACK));
        board.set(new Location(1,1), new Pawn(Color.BLACK));
        board.set(new Location(1,2), new Pawn(Color.BLACK));
        board.set(new Location(1,3), new Pawn(Color.BLACK));
        board.set(new Location(1,4), new Pawn(Color.BLACK));
        board.set(new Location(1,5), new Pawn(Color.BLACK));
        board.set(new Location(1,6), new Pawn(Color.BLACK));
        board.set(new Location(1,7), new Pawn(Color.BLACK));

        // white pieces
        board.set(new Location(6,0), new Pawn(Color.WHITE));
        board.set(new Location(6,1), new Pawn(Color.WHITE));
        board.set(new Location(6,2), new Pawn(Color.WHITE));
        board.set(new Location(6,3), new Pawn(Color.WHITE));
        board.set(new Location(6,4), new Pawn(Color.WHITE));
        board.set(new Location(6,5), new Pawn(Color.WHITE));
        board.set(new Location(6,6), new Pawn(Color.WHITE));
        board.set(new Location(6,7), new Pawn(Color.WHITE));

        board.set(new Location(7,0), new Rook(Color.WHITE));
        board.set(new Location(7,1), new Knight(Color.WHITE));
        board.set(new Location(7,2), new Bishop(Color.WHITE));
        board.set(new Location(7,3), new Queen(Color.WHITE));
        board.set(new Location(7,4), new King(Color.WHITE));
        board.set(new Location(7,5), new Bishop(Color.WHITE));
        board.set(new Location(7,6), new Knight(Color.WHITE));
        board.set(new Location(7,7), new Rook(Color.WHITE));
    }

    public IPiece get(Location location) {
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
