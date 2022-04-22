package chess.model.piece;

import chess.model.ChessModel;
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
    public List<Location> getValidMoves(ChessModel board, Location start) {
        List<Location> validMoves = new ArrayList<>();
        Collection<Location> neighbors = start.allNeighbors();
        for (Location loc : neighbors) {
            if (isValidMove(board, loc)) {
                validMoves.add(loc);
            }
        }
        addCastleMoves(board, start, validMoves);
        return validMoves;
    }

    private void addCastleMoves(ChessModel board, Location loc, List<Location> moves) {
        // castle king side
        if (!this.hasMovedBefore() && !board.getTile(new Location(loc.row, loc.col + 3)).isEmpty()) {
            if (!board.getTile(new Location(loc.row, loc.col + 3)).piece.hasMovedBefore()) {
                if (board.getTile(new Location(loc.row, loc.col + 1)).isEmpty() && board.getTile(new Location(loc.row, loc.col + 2)).isEmpty()) {
                    moves.add(new Location(loc.row, loc.col + 2));
                    board.getTile(new Location(loc.row, loc.col+2)).setCastleMove(true);
                }

            }
        }
    }

}
