package chess.model;

import chess.model.piece.*;
import grid.Grid;
import grid.Location;

import java.awt.*;

public class ChessBoard extends Grid<Tile> {

    public ChessBoard(int rows, int cols, Tile element) {
        super(rows, cols, element);
    }

    /**
     * Initialize a standard chess board.
     */
    public void initializeBoard() {
        // black pieces
        this.set(new Location(0,0), new Tile(new Rook(Color.BLACK), 'R'));
        this.set(new Location(0,1), new Tile(new Knight(Color.BLACK), 'N'));
        this.set(new Location(0,2), new Tile(new Bishop(Color.BLACK), 'B'));
        this.set(new Location(0,3), new Tile(new Queen(Color.BLACK), 'Q'));
        this.set(new Location(0,4), new Tile(new King(Color.BLACK), 'K'));
        this.set(new Location(0,5), new Tile(new Bishop(Color.BLACK), 'B'));
        this.set(new Location(0,6), new Tile(new Knight(Color.BLACK), 'N'));
        this.set(new Location(0,7), new Tile(new Rook(Color.BLACK), 'R'));

        this.set(new Location(1,0), new Tile(new Pawn(Color.BLACK), 'P'));
        this.set(new Location(1,1), new Tile(new Pawn(Color.BLACK), 'P'));
        this.set(new Location(1,2), new Tile(new Pawn(Color.BLACK), 'P'));
        this.set(new Location(1,3), new Tile(new Pawn(Color.BLACK), 'P'));
        this.set(new Location(1,4), new Tile(new Pawn(Color.BLACK), 'P'));
        this.set(new Location(1,5), new Tile(new Pawn(Color.BLACK), 'P'));
        this.set(new Location(1,6), new Tile(new Pawn(Color.BLACK), 'P'));
        this.set(new Location(1,7), new Tile(new Pawn(Color.BLACK), 'P'));

        // white pieces
        this.set(new Location(6,0), new Tile(new Pawn(Color.WHITE), 'p'));
        this.set(new Location(6,1), new Tile(new Pawn(Color.WHITE), 'p'));
        this.set(new Location(6,2), new Tile(new Pawn(Color.WHITE), 'p'));
        this.set(new Location(6,3), new Tile(new Pawn(Color.WHITE), 'p'));
        this.set(new Location(6,4), new Tile(new Pawn(Color.WHITE), 'p'));
        this.set(new Location(6,5), new Tile(new Pawn(Color.WHITE), 'p'));
        this.set(new Location(6,6), new Tile(new Pawn(Color.WHITE), 'p'));
        this.set(new Location(6,7), new Tile(new Pawn(Color.WHITE), 'p'));

        this.set(new Location(7,0), new Tile(new Rook(Color.WHITE), 'r'));
        this.set(new Location(7,1), new Tile(new Knight(Color.WHITE), 'n'));
        this.set(new Location(7,2), new Tile(new Bishop(Color.WHITE), 'b'));
        this.set(new Location(7,3), new Tile(new Queen(Color.WHITE), 'q'));
        this.set(new Location(7,4), new Tile(new King(Color.WHITE), 'k'));
        this.set(new Location(7,5), new Tile(new Bishop(Color.WHITE), 'b'));
        this.set(new Location(7,6), new Tile(new Knight(Color.WHITE), 'n'));
        this.set(new Location(7,7), new Tile(new Rook(Color.WHITE), 'r'));
    }

    public void resetBoard() {
        this.initializeBoard();
    }

}
