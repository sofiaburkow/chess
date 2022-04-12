package chess.model.piece;

import chess.model.ChessModel;
import chess.model.Player;
import chess.model.Tile;
import grid.Location;

import java.awt.Color;
import java.util.List;

public abstract class Piece implements IPiece {

    protected Color color;

    public Piece (Color color) {
        this.color = color;
    }

    @Override
    public boolean isWhite() {
        return this.color == Color.WHITE;
    }

    @Override
    public Player getPlayer() {
        if (isWhite()) {
            return Player.WHITE;
        }
        return Player.BLACK;
    }

    @Override
    public String getPieceColor() {
        if (isWhite()) {
            return "W";
        }
        return "B";
    }

    @Override
    public abstract Type getPiece();

    @Override
    public abstract List<Location> getPossibleMoves(ChessModel board, Location start);

    @Override
    public abstract boolean canMove(ChessModel board, Location start, Location end);
}
