package chess.model.piece;

import chess.model.ChessBoard;
import chess.model.Move;
import grid.Location;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class KingTest {

    @Test
    public void getValidMovesTest() {
        ChessBoard board = new ChessBoard();

        // move some pieces around
        Location startingLocationBP = new Location(1, 3);
        Location newLocationBP = new Location(3,3);
        board.movePiece(board, new Move(startingLocationBP, newLocationBP));

        Location startingLocationBK = new Location(0, 4);
        Location newLocationBK = new Location(3,2);
        board.movePiece(board, new Move(startingLocationBK, newLocationBK));
        board.get(newLocationBK).piece.setHasMovedBefore(true);

        Location startingLocationWB = new Location(7, 5);
        Location newLocationWB = new Location(4,2);
        board.movePiece(board, new Move(startingLocationWB, newLocationWB));

        board.nextPlayer();

        String expectedBoardString =
                        "RNBQ-BNR\n" +
                        "PPP-PPPP\n" +
                        "--------\n" +
                        "--KP----\n" +
                        "--b-----\n" +
                        "--------\n" +
                        "pppppppp\n" +
                        "rnbqk-nr\n";

        System.out.println(board.get(newLocationBK).piece.getPiece());
        // get possible black king moves
        java.util.List<Move> moves = board.get(newLocationBK).piece.getValidMoves(board, newLocationBK);


        List<Move> validMoves = new ArrayList<>();
        validMoves.add(new Move(newLocationBK, new Location(2,1)));
        validMoves.add(new Move(newLocationBK, new Location(2,2)));
        validMoves.add(new Move(newLocationBK, new Location(2,3)));
        validMoves.add(new Move(newLocationBK, new Location(4,1)));
        validMoves.add(new Move(newLocationBK, new Location(4,2)));
        validMoves.add(new Move(newLocationBK, new Location(4,3)));



        //assertEquals(validMoves.size(), moves.size());


        for (Move move : moves) {
            System.out.println(move.destination.row + " " + move.destination.col);
            //assertTrue(validMoves.contains(move));
        }



    }

    @Test
    public void castleMoveTest() {
        ChessBoard board = new ChessBoard();
    }

}
