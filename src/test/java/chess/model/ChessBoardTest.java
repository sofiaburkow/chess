package chess.model;

import chess.model.piece.Pieces;
import grid.Location;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ChessBoardTest {

    @Test
    void createBoardTest() {
        ChessBoard board = new ChessBoard(8,8,null);
        board.initiateBoard();
        // check color
        //assertTrue("ROOK".equals(board.get(new Location(0,0)).getPiece()));
        //assertTrue(board.get(new Location(7,0)).isWhite());
    }
}
