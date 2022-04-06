package chess.model;

import java.awt.*;

public class ChessModel {

    private ChessBoard board;

    private Graphics graphics;

    public ChessModel () {
        this.board = new ChessBoard(8,8, null);
    }
}
