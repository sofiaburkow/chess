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
    public abstract List<Location> getValidMoves(ChessModel board, Location start);

    @Override
    public boolean isValidMove(ChessModel board, Location loc) {
        if (board.isOnBoard(loc)) {
            if (board.getTile(loc) == null) {
                return true;
            } else if (board.getTile(loc).piece.isWhite() != isWhite()) {
                return true;
            }
        }
        return false;
    }

    public void addMoves(ChessModel board, Location start, int rowOperand, int columnOperand, List<Location> moves) {
        for (int i = 1; i < board.numColumns(); i++) {
            Location loc = new Location(start.row+rowOperand*i, start.col+columnOperand*i);
            if (isValidMove(board, loc)) {
                moves.add(loc);
                // if it is of the opposite color
                if (board.getTile(loc) != null) {
                    break;
                }
            } else {
                break;
            }
        }
    }

    @Override
    public boolean canMove(ChessModel board, Location start, Location end) {
        if (getValidMoves(board, start).contains(end)) {
            return true;
        }
        return false;
    }

}
