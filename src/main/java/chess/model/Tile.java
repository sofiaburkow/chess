package chess.model;

import chess.model.piece.Piece;
import chess.model.piece.Type;

public class Tile {

    //public int id;
    public final Piece piece;

    public Tile(Piece piece) {
        this.piece = piece;
    }

    public Type getPiece() {
        return piece.getPiece();
    }

    /**
     * @return the initial of the color
     */
    public String getPieceColor() {
        if (piece.isWhite()) {
            return "W";
        }
        return "B";
    }

}
