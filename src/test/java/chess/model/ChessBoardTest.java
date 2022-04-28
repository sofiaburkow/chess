package chess.model;

import grid.Location;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        // move black pieces
        board.movePiece(board, new Move(new Location(0,4), new Location(3,2)));
        board.movePiece(board, new Move(new Location(1,4), new Location(3,4)));
        // move white pieces
        board.movePiece(board, new Move(new Location(7,5), new Location(3,1)));
        board.movePiece(board, new Move(new Location(7,1), new Location(5,2)));
        board.movePiece(board, new Move(new Location(6,3), new Location(5,3)));
        board.movePiece(board, new Move(new Location(6,4), new Location(4,4)));
        board.movePiece(board, new Move(new Location(7,3), new Location(4,6)));

        char[][] charArray = board.fromBoardToCharArray();
        String charString = board.fromCharArrayToString(charArray);

        String expectedBoardString =
                        "RNBQ-BNR\n" +
                        "PPPP-PPP\n" +
                        "--------\n" +
                        "-bK-P---\n" +
                        "----p-q-\n" +
                        "--np----\n" +
                        "ppp--ppp\n" +
                        "r-b-k-nr\n";

        assertEquals(expectedBoardString, charString);
        //List<Move> moves = board.get()
        //assertTrue(board.isValidMove(new Move(new Location(3,2), new Location(4,3))));
        //assertTrue(board.isValidMove(new Move(new Location(3,2), new Location(4,1))));
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
