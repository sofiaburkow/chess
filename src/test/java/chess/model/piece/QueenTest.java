package chess.model.piece;

import chess.model.ChessBoard;
import chess.model.Tile;
import grid.Location;
import org.junit.jupiter.api.Test;

import java.awt.*;

public class QueenTest {

    @Test
    void getValidMovesTest() {
        ChessBoard board = new ChessBoard();
        Location loc = new Location(4,4);
        board.set((loc), new Tile(new Queen(Color.WHITE)));
        System.out.println(board.get(loc).piece.getValidMoves(board,loc));
    }

}
