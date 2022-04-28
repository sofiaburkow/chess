package chess.model.piece;

import chess.model.ChessBoard;
import chess.model.Move;
import chess.model.Team;
import grid.Location;

import java.awt.Color;
import java.util.List;

public abstract class Piece implements IPiece {

    protected Color color;
    // Boolean value to keep track of whether a piece has moved before or not.
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
    public abstract List<Move> getValidMoves(ChessBoard board, Location source);

    @Override
    public boolean canMove(ChessBoard board, Move move) {
        if (getValidMoves(board, move.source).contains(move) && !board.resultsInCheck(move)) {
            return true;
        }
        return false;
    }

    /**
     * Checks whether the destination tile is valid or not. The location is valid if the
     * tile is empty, or if the piece on the given tile is of the opposite team/color.
     *
     * @return true if the destination is valid, otherwise false.
     */
    protected boolean isValidDestinationTile(ChessBoard board, Location destination) {
        if (board.isOnGrid(destination)) {
            if (board.get(destination).isEmpty() || board.get(destination).piece.isWhite() != isWhite()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Add new valid moves to the list of valid moves according to rowOperand and columnOperand.
     * This method will be used to add horizontal, vertical and diagonal moves.
     */
    protected void addValidMoves(ChessBoard board, Location source, int rowOperand, int columnOperand, List<Move> moves) {
        for (int i = 1; i < board.numColumns(); i++) {
            Location destination = new Location(source.row+rowOperand*i, source.col+columnOperand*i);
            if (isValidDestinationTile(board, destination)) {
                moves.add(new Move(source, destination));
                if (!board.get(destination).isEmpty()) {
                    break;
                }
            } else {
                break;
            }
        }
    }
}
