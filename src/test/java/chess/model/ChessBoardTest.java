package chess.model;

import grid.Location;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChessBoardTest {

    @Test
    void createChessBoardTest() {

        ChessBoard board = new ChessBoard();
        char[][] charArray = board.fromBoardToCharArray();
        String charString = board.fromCharArrayToString(charArray);

        String expectedBoardString =
                    "RNBQKBNR\n" +
                    "PPPPPPPP\n" +
                    "--------\n" +
                    "--------\n" +
                    "--------\n" +
                    "--------\n" +
                    "pppppppp\n" +
                    "rnbqkbnr\n";

        assertEquals(expectedBoardString, charString);
    }

    @Test
    public void movePieceTest() {

        ChessBoard board = new ChessBoard();
        // move the black king
        board.movePiece(board, new Move(new Location(0,4),new Location(4,2)));
        // move the rightmost white pawn
        board.movePiece(board, new Move(new Location(6,7),new Location(4,7)));
        char[][] charArray = board.fromBoardToCharArray();
        String charString = board.fromCharArrayToString(charArray);

        String expectedBoardString =
                        "RNBQ-BNR\n" +
                        "PPPPPPPP\n" +
                        "--------\n" +
                        "--------\n" +
                        "--K----p\n" +
                        "--------\n" +
                        "ppppppp-\n" +
                        "rnbqkbnr\n";

        assertEquals(expectedBoardString, charString);
    }


}
