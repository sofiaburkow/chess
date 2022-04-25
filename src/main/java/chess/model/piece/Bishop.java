package chess.model.piece;

import chess.model.ChessModel;
import chess.model.Move;
import grid.Location;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {

    public Bishop(Color color) {
        super(color);
    }

    @Override
    public Type getPiece() {
        return Type.BISHOP;
    }

    @Override
    public List<Move> getValidMoves(ChessModel board, Location start) {

        List<Move> validMoves = new ArrayList<>();

        addMoves(board, start, 1,1, validMoves);
        addMoves(board, start, 1,-1, validMoves);
        addMoves(board, start, -1,1, validMoves);
        addMoves(board, start, -1,-1, validMoves);

        return validMoves;
    }

}
