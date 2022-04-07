package chess.model;

import chess.model.piece.Piece;
import chess.model.piece.Pieces;

import java.util.Objects;

public class Tile {

    //public int id;
    public final Piece piece;

    public Tile(Piece piece) {
        this.piece = piece;
    }

    public Pieces getPiece() {
        return piece.getPiece();
    }
}