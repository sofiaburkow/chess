package chess.model.piece;

import chess.model.ChessModel;
import chess.model.Tile;
import grid.Location;
import org.junit.jupiter.api.Test;

import java.awt.*;

public class RookTest {

    @Test
    void getValidMovesTest() {
        ChessModel board = new ChessModel();
        board.setTile(new Location(6,7), null);
        Location locRook = new Location(7,7);
        System.out.println(board.getTile(locRook).piece.getValidMoves(board,locRook));

    }
}
