package chess.model.piece;

import chess.model.ChessBoard;
import chess.model.IBoard;
import chess.model.Move;
import grid.Location;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class King extends Piece {

    public King(Color color) {
        super(color);
    }

    @Override
    public Type getPiece() {
        return Type.KING;
    }

    @Override
    public List<Move> getValidMoves(IBoard board, Location source) {
        List<Move> validMoves = new ArrayList<>();
        Collection<Location> neighbors = source.allNeighbors();
        for (Location loc : neighbors) {
            if (isValidDestinationTile(board, loc)) {
                validMoves.add(new Move(source, loc));
            }
        }
        addCastleMoves(board, source, validMoves);
        return checkForCheck(board, validMoves);
    }

    /**
     * Checks whether castling is possible or not.
     * If a castling is possible, add it to the list of possible moves.
     * Check for both castling king side and castling queen side.
     */
    private void addCastleMoves(IBoard board, Location source, List<Move> moves) {
        if (!this.hasMovedBefore() && !board.isCheck(board)) {
            // king side castling
            Location castleKS = new Location(source.row, source.col+3);
            if (!board.get(castleKS).isEmpty() && !board.get(castleKS).piece.hasMovedBefore()) {
                if (board.get(new Location(source.row, source.col+1)).isEmpty() && board.get(new Location(source.row, source.col+2)).isEmpty()) {
                    Location newKingLocation = new Location(source.row, source.col+2);
                    moves.add(new Move(source, newKingLocation));
                    board.get(newKingLocation).setCastleMove(true);
                }
            }
            // queen side castling
            Location castleQS = new Location(source.row, source.col-4);
            if (!board.get(castleQS).isEmpty() && !board.get(castleQS).piece.hasMovedBefore()) {
                if (board.get(new Location(source.row, source.col-1)).isEmpty() && board.get(new Location(source.row, source.col-2)).isEmpty() && board.get(new Location(source.row, source.col-3)).isEmpty()) {
                    Location newKingLocation = new Location(source.row, source.col-2);
                    moves.add(new Move(source, newKingLocation));
                    board.get(newKingLocation).setCastleMove(true);
                }
            }
        }
    }

    /**
     * Check whether the given list contains moves that result in check or not.
     * Make a new list where no moves result in check.
     *
     * @return a list of valid moves.
     */
    private List<Move> checkForCheck(IBoard board, List<Move> moves) {
        List<Move> validMoves = new ArrayList<>();
        List<Location> underAttack = board.tilesUnderAttack(board);
        for (Move move : moves) {
            if (!underAttack.contains(move.destination)) {
                validMoves.add(move);
            }
        }
        return validMoves;
    }

}
