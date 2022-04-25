package chess.model.piece;

import chess.model.ChessModel;
import chess.model.Move;
import grid.Location;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {

    public Rook(Color color) {
        super(color);
    }

    @Override
    public Type getPiece() {
        return Type.ROOK;
    }

    @Override
    public List<Move> getValidMoves(ChessModel board, Location source) {
        List<Move> validMoves = new ArrayList<>();
        addMoves(board, source, 1,0, validMoves);
        addMoves(board, source, 0,1, validMoves);
        addMoves(board, source, -1,0, validMoves);
        addMoves(board, source, 0,-1, validMoves);
        return validMoves;
    }

}
