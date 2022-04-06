package chess.model;

import grid.Location;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ChessBoardTest {

    @Test
    void createBoardTest() {
        ChessBoard board = new ChessBoard(8,8,null);
        board.createBoard();
        // check color
        assertFalse(board.get(new Location(0,0)).isWhite());
        assertTrue(board.get(new Location(7,0)).isWhite());
    }
}
