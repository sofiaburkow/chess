package chess.model.piece;

import chess.model.ChessModel;
import chess.model.Move;
import chess.model.Player;
import chess.model.Tile;
import grid.Location;

import java.awt.Color;
import java.util.List;

public abstract class Piece implements IPiece {

    protected Color color;
    protected boolean hasMovedBefore;

    public Piece(Color color) {
        this.color = color;
        this.hasMovedBefore = false;
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
    public abstract List<Move> getValidMoves(ChessModel board, Location start);

    @Override
    public boolean isValidMove(ChessModel board, Location loc) {
        if (board.isOnBoard(loc)) {
            if (board.getTile(loc).isEmpty()) {
                return true;
            }
            if (board.getTile(loc).piece.isWhite() != isWhite()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void addMoves(ChessModel board, Location source, int rowOperand, int columnOperand, List<Move> moves) {
        for (int i = 1; i < board.numColumns(); i++) {
            Location destination = new Location(source.row+rowOperand*i, source.col+columnOperand*i);
            if (isValidMove(board, destination)) {
                Move move = new Move(source, destination, false, false);
                moves.add(move);
                if (!board.getTile(destination).isEmpty()) {
                    break;
                }
            } else {
                break;
            }
        }
    }

    @Override
    public boolean canMove(ChessModel board, Move move) {
        for (Move m : getValidMoves(board, move.source)) {
            System.out.println(m.destination);
        }
        if (getValidMoves(board, move.source).contains(move)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean hasMovedBefore() {
        return hasMovedBefore;
    }

    @Override
    public void setHasMovedBefore(boolean hasMovedBefore) {
        this.hasMovedBefore = hasMovedBefore;
    }

}
