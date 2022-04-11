package chess.model;

import org.junit.jupiter.api.Test;

public class ChessBoardTest {

    @Test
    void createBoardTest() {
        ChessBoard board = new ChessBoard(8,8,null);
        board.initializeBoard();
        // check color
        //assertTrue("ROOK".equals(board.get(new Location(0,0)).getPiece()));
        //assertTrue(board.get(new Location(7,0)).isWhite());
    }
}
