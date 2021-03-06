package chess.model.piece;

import chess.model.IBoard;
import chess.model.Move;
import grid.Location;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {

    public Knight(Color color) {
        super(color);
    }

    @Override
    public Type getPiece() {
        return Type.KNIGHT;
    }

    @Override
    public List<Move> getValidMoves(IBoard board, Location source) {

        List<Move> validMoves = new ArrayList<>();

        List<Move> possibleMoves = getPossibleMoves(source);
        for (Move move : possibleMoves) {
            if (isValidDestinationTile(board,move.destination)) {
                validMoves.add(move);
            }
        }

        return validMoves;
    }

    /**
     * Get all possible knight moves without checking if the move is possible or not.
     */
    private List<Move> getPossibleMoves(Location source) {

        List<Move> moves = new ArrayList<>();

        moves.add(new Move(source, new Location(source.row+2, source.col+1)));
        moves.add(new Move(source, new Location(source.row+2, source.col-1)));
        moves.add(new Move(source, new Location(source.row+1, source.col+2)));
        moves.add(new Move(source, new Location(source.row+1, source.col-2)));
        moves.add(new Move(source, new Location(source.row-1, source.col+2)));
        moves.add(new Move(source, new Location(source.row-1, source.col-2)));
        moves.add(new Move(source, new Location(source.row-2, source.col+1)));
        moves.add(new Move(source, new Location(source.row-2, source.col-1)));

        return moves;
    }

}
