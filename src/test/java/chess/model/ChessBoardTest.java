package chess.model;

import grid.Location;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
    public void playerTest() {
        ChessBoard board = new ChessBoard();
        assertTrue(board.getCurrentPlayer() == Team.WHITE);
        board.nextPlayer();
        assertTrue(board.getCurrentPlayer() == Team.BLACK);
        board.nextPlayer();
        assertTrue(board.getCurrentPlayer() == Team.WHITE);
    }

    @Test
    public void copyTest() {

        ChessBoard board = new ChessBoard();
        ChessBoard copy = board.copy();
        char[][] charArray = copy.fromBoardToCharArray();
        String charString = copy.fromCharArrayToString(charArray);

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
    public void isValidMoveTest() {

        ChessBoard board = new ChessBoard();
        // move white pieces
        board.movePiece(board, new Move(new Location(7,5), new Location(3,1)));
        board.movePiece(board, new Move(new Location(7,1), new Location(5,2)));
        board.movePiece(board, new Move(new Location(6,3), new Location(5,3)));
        board.movePiece(board, new Move(new Location(6,4), new Location(4,4)));
        board.movePiece(board, new Move(new Location(7,3), new Location(4,6)));
        board.nextPlayer();

        // move black pieces
        Location newLocationBK = new Location(3,2);
        board.movePiece(board, new Move(new Location(0,4), newLocationBK));
        board.get(newLocationBK).piece.setHasMovedBefore(true);
        board.movePiece(board, new Move(new Location(1,4), new Location(3,4)));

        assertTrue(board.isValidMove(new Move(newLocationBK, new Location(2,1))));
        assertTrue(board.isValidMove(new Move(newLocationBK, new Location(2,3))));
        assertTrue(board.isValidMove(new Move(newLocationBK, new Location(4,1))));
        assertTrue(board.isValidMove(new Move(newLocationBK, new Location(4,3))));

        assertFalse(board.isValidMove(new Move(newLocationBK, new Location(2,2))));
        assertFalse(board.isValidMove(new Move(newLocationBK, new Location(3,1))));
        assertFalse(board.isValidMove(new Move(newLocationBK, new Location(3,3))));
        assertFalse(board.isValidMove(new Move(newLocationBK, new Location(4,2))));
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

    @Test
    public void checkMateTest() {

    }

    @Test
    public void staleMateTest() {


    }

}
