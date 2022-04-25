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
            if (isValidMove(board, loc)) {
                validMoves.add(new Move(source, loc, false, false));
            }
        }
        addCastleMoves(board, source, validMoves);
        return validMoves;
    }

    private void addCastleMoves(ChessModel board, Location source, List<Move> moves) {
        // castle king side
        if (!this.hasMovedBefore() && !board.getTile(new Location(source.row, source.col + 3)).isEmpty()) {
            if (!board.getTile(new Location(source.row, source.col + 3)).piece.hasMovedBefore()) {
                if (board.getTile(new Location(source.row, source.col + 1)).isEmpty() && board.getTile(new Location(source.row, source.col + 2)).isEmpty()) {
                    moves.add(new Move(source, new Location(source.row, source.col + 2), true, false));
                    //board.getTile(new Location(source.row, source.col+2)).setCastleMove(true);
                }
            }
        }
        if (!this.hasMovedBefore() && !board.getTile(new Location(source.row, source.col - 4)).isEmpty()) {
            if (!board.getTile(new Location(source.row, source.col-4)).piece.hasMovedBefore()) {
                if (board.getTile(new Location(source.row, source.col-1)).isEmpty() && board.getTile(new Location(source.row, source.col-2)).isEmpty() && board.getTile(new Location(source.row, source.col-3)).isEmpty()) {
                    moves.add(new Move(source, new Location(source.row, source.col - 2), true, false));
                    //board.getTile(new Location(source.row, source.col-2)).setCastleMove(true);
                }
            }
        }
    }

}
