package chess.model.piece;

import chess.model.ChessModel;
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
    public List<Location> getValidMoves(ChessModel board, Location start) {
        List<Location> validMoves = new ArrayList<>();

        addMoves(board, start, 1,0, validMoves);
        addMoves(board, start, 0,1, validMoves);
        addMoves(board, start, -1,0, validMoves);
        addMoves(board, start, 0,-1, validMoves);

        return validMoves;
    }

}
