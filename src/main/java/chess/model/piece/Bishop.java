package chess.model.piece;

import chess.model.ChessBoard;
import chess.model.IBoard;
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
    public List<Move> getValidMoves(IBoard board, Location source) {
        List<Move> validMoves = new ArrayList<>();
        addValidMoves(board, source, 1,1, validMoves);
        addValidMoves(board, source, 1,-1, validMoves);
        addValidMoves(board, source, -1,1, validMoves);
        addValidMoves(board, source, -1,-1, validMoves);
        return validMoves;
    }

}
