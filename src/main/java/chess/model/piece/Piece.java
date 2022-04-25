package chess.model.piece;

import chess.model.ChessModel;
import chess.model.Move;
import chess.model.Team;
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
    public boolean hasMovedBefore() {
        return hasMovedBefore;
    }

    @Override
    public void setHasMovedBefore(boolean hasMovedBefore) {
        this.hasMovedBefore = hasMovedBefore;
    }

    @Override
    public boolean isWhite() {
        return this.color == Color.WHITE;
    }

    @Override
    public Team getTeam() {
        if (isWhite()) {
            return Team.WHITE;
        }
        return Team.BLACK;
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
    public abstract List<Move> getValidMoves(ChessModel board, Location source);

    @Override
    public boolean canMove(ChessModel board, Move move) {
        if (getValidMoves(board, move.source).contains(move)) {
            return true;
        }
        return false;
    }

    /**
     * Checks whether the destination tile is valid or not. The location is valid if the
     * tile is empty, or if the piece on the given tile is of the opposite team.
     *
     * @return true if the destination location is valid, otherwise false.
     */
    protected boolean isValidDestinationTile(ChessModel board, Location destination) {
        if (board.isOnBoard(destination)) {
            if (board.getTile(destination).isEmpty() || board.getTile(destination).piece.isWhite() != isWhite()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Add valid horizontal, vertical and diagonal moves to the list of moves.
     * The method will loop over the tiles on the board according to rowOperand and columnOperand.
     */
    protected void addValidMoves(ChessModel board, Location source, int rowOperand, int columnOperand, List<Move> moves) {
        for (int i = 1; i < board.numColumns(); i++) {
            Location destination = new Location(source.row+rowOperand*i, source.col+columnOperand*i);
            if (isValidDestinationTile(board, destination)) {
                moves.add(new Move(source, destination));
                if (!board.getTile(destination).isEmpty()) {
                    break;
                }
            } else {
                break;
            }
        }
    }
}
