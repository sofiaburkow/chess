package chess.model.piece;

import chess.model.ChessBoard;
import grid.Location;
import org.junit.jupiter.api.Test;

public class RookTest {

    @Test
    void getValidMovesTest() {
        ChessBoard board = new ChessBoard();
        board.set(new Location(6,7), null);
        Location locRook = new Location(7,7);
        System.out.println(board.get(locRook).piece.getValidMoves(board,locRook));

    }
}
