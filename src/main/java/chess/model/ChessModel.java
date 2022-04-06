package chess.model;

import chess.model.piece.King;
import chess.model.piece.Knight;
import chess.model.piece.Piece;
import chess.model.piece.Rook;
import grid.Location;

import java.awt.*;

public class ChessModel {

    private ChessBoard board;

    private Graphics graphics;

    public ChessModel () {
        this.board = new ChessBoard(8,8, null);
    }
}
