package chess.model.piece;

import chess.model.ChessModel;
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
    public List<Move> getValidMoves(ChessModel board, Location source) {
        List<Move> validMoves = new ArrayList<>();
        Collection<Location> neighbors = source.allNeighbors();
        for (Location loc : neighbors) {
            if (isValidDestinationTile(board, loc)) {
                validMoves.add(new Move(source, loc));
            }
        }
        if (!board.isCheck()) {
            addCastleMoves(board, source, validMoves);
        }
        return checkForCheck(board, validMoves);
        //return validMoves;
    }

    /**
     * Checks whether castling is possible or not.
     * If a castling move is possible, add it to the list of possible moves.
     * Check for both castling king side and castling queen side.
     */
    private void addCastleMoves(ChessModel board, Location source, List<Move> moves) {
        if (!this.hasMovedBefore()) {
            Location castleKS = new Location(source.row, source.col+3);
            if (!board.getTile(castleKS).isEmpty() && !board.getTile(castleKS).piece.hasMovedBefore()) {
                if (board.getTile(new Location(source.row, source.col+1)).isEmpty() && board.getTile(new Location(source.row, source.col+2)).isEmpty()) {
                    moves.add(new Move(source, new Location(source.row, source.col+2)));
                    board.getTile(new Location(source.row, source.col+2)).setCastleMove(true);
                }
            }
            Location castleQS = new Location(source.row, source.col-4);
            if (!board.getTile(castleQS).isEmpty() && !board.getTile(castleQS).piece.hasMovedBefore()) {
                if (board.getTile(new Location(source.row, source.col-1)).isEmpty() && board.getTile(new Location(source.row, source.col-2)).isEmpty() && board.getTile(new Location(source.row, source.col-3)).isEmpty()) {
                    moves.add(new Move(source, new Location(source.row, source.col-2)));
                    board.getTile(new Location(source.row, source.col-2)).setCastleMove(true);
                }
            }
        }
    }

    /**
     * Check whether the given list contains moves that result in check.
     *
     * @return a list with valid moves.
     */
    private List<Move> checkForCheck(ChessModel board, List<Move> moves) {
        List<Move> validMoves = new ArrayList<>();
        List<Location> chess = board.tilesUnderAttack(board);
        for (Move move : moves) {
            if (!chess.contains(move.destination)) {
                validMoves.add(move);
            }
        }
        return validMoves;
    }

}
