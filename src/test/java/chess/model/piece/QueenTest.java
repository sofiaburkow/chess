package chess.model.piece;

import chess.model.ChessModel;
import chess.model.Tile;
import grid.Location;
import org.junit.jupiter.api.Test;

import java.awt.*;

public class QueenTest {

    @Test
    void getValidMovesTest() {
        ChessModel board = new ChessModel();
        Location loc = new Location(4,4);
        board.setTile((loc), new Tile(new Queen(Color.WHITE)));
        System.out.println(board.getTile(loc).piece.getValidMoves(board,loc));
    }

}
