package chess.model.piece;

import chess.model.ChessModel;
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
    public List<Move> getValidMoves(ChessModel board, Location source) {

        List<Move> possibleMoves = getPossibleMoves(board, source);

        List<Move> validMoves = new ArrayList<>();
        for (Move move : possibleMoves) {
            if (isValidMove(board,move.destination)) {
                validMoves.add(move);
            }
        }
        return validMoves;
    }

    private List<Move> getPossibleMoves(ChessModel board, Location source) {

        List<Move> moves = new ArrayList<>();

        moves.add(new Move(source, new Location(source.row+2, source.col+1), false, false));
        moves.add(new Move(source, new Location(source.row+2, source.col-1), false, false));
        moves.add(new Move(source, new Location(source.row+1, source.col+2), false, false));
        moves.add(new Move(source, new Location(source.row+1, source.col-2), false, false));
        moves.add(new Move(source, new Location(source.row-1, source.col+2), false, false));
        moves.add(new Move(source, new Location(source.row-1, source.col-2), false, false));
        moves.add(new Move(source, new Location(source.row-2, source.col+1), false, false));
        moves.add(new Move(source, new Location(source.row-2, source.col-1), false, false));

        return moves;
    }

}
