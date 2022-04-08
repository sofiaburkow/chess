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
     * Create a standard chess board.
     */
    public void initiateBoard() {
        // black pieces
        this.set(new Location(0,0), new Tile(new Rook(Color.BLACK)));
        this.set(new Location(0,1), new Tile(new Knight(Color.BLACK)));
        this.set(new Location(0,2), new Tile(new Bishop(Color.BLACK)));
        this.set(new Location(0,3), new Tile(new Queen(Color.BLACK)));
        this.set(new Location(0,4), new Tile(new King(Color.BLACK)));
        this.set(new Location(0,5), new Tile(new Bishop(Color.BLACK)));
        this.set(new Location(0,6), new Tile(new Knight(Color.BLACK)));
        this.set(new Location(0,7), new Tile(new Rook(Color.BLACK)));

        this.set(new Location(1,0), new Tile(new Pawn(Color.BLACK)));
        this.set(new Location(1,1), new Tile(new Pawn(Color.BLACK)));
        this.set(new Location(1,2), new Tile(new Pawn(Color.BLACK)));
        this.set(new Location(1,3), new Tile(new Pawn(Color.BLACK)));
        this.set(new Location(1,4), new Tile(new Pawn(Color.BLACK)));
        this.set(new Location(1,5), new Tile(new Pawn(Color.BLACK)));
        this.set(new Location(1,6), new Tile(new Pawn(Color.BLACK)));
        this.set(new Location(1,7), new Tile(new Pawn(Color.BLACK)));

        // white pieces
        this.set(new Location(6,0), new Tile(new Pawn(Color.WHITE)));
        this.set(new Location(6,1), new Tile(new Pawn(Color.WHITE)));
        this.set(new Location(6,2), new Tile(new Pawn(Color.WHITE)));
        this.set(new Location(6,3), new Tile(new Pawn(Color.WHITE)));
        this.set(new Location(6,4), new Tile(new Pawn(Color.WHITE)));
        this.set(new Location(6,5), new Tile(new Pawn(Color.WHITE)));
        this.set(new Location(6,6), new Tile(new Pawn(Color.WHITE)));
        this.set(new Location(6,7), new Tile(new Pawn(Color.WHITE)));

        this.set(new Location(7,0), new Tile(new Rook(Color.WHITE)));
        this.set(new Location(7,1), new Tile(new Knight(Color.WHITE)));
        this.set(new Location(7,2), new Tile(new Bishop(Color.WHITE)));
        this.set(new Location(7,3), new Tile(new Queen(Color.WHITE)));
        this.set(new Location(7,4), new Tile(new King(Color.WHITE)));
        this.set(new Location(7,5), new Tile(new Bishop(Color.WHITE)));
        this.set(new Location(7,6), new Tile(new Knight(Color.WHITE)));
        this.set(new Location(7,7), new Tile(new Rook(Color.WHITE)));
    }

}
