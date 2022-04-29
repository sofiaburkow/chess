package chess.model.piece;

import chess.model.ChessBoard;
import chess.model.Move;
import grid.Location;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

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

        // get possible black king moves
        java.util.List<Move> moves = board.get(newLocationBK).piece.getValidMoves(board, newLocationBK);

        List<Move> validMoves = new ArrayList<>();
        validMoves.add(new Move(newLocationBK, new Location(2,1)));
        validMoves.add(new Move(newLocationBK, new Location(2,2)));
        validMoves.add(new Move(newLocationBK, new Location(2,3)));
        validMoves.add(new Move(newLocationBK, new Location(4,1)));
        validMoves.add(new Move(newLocationBK, new Location(4,2)));
        validMoves.add(new Move(newLocationBK, new Location(4,3)));

        assertEquals(validMoves.size(), moves.size());
        for (Move move : moves) {
            assertTrue(validMoves.contains(move));
        }
    }

    @Test
    public void castleKingSideTest() {

        ChessBoard board = new ChessBoard();

        // move away bishop and knight
        Location startingLocationWB = new Location(7, 5);
        Location newLocationWB = new Location(5,5);
        board.movePiece(board, new Move(startingLocationWB, newLocationWB));
        Location startingLocationWN = new Location(7, 6);
        Location newLocationWN = new Location(5,6);
        board.movePiece(board, new Move(startingLocationWN, newLocationWN));

        // check if it is possible to castle king side
        Location startingLocationWK = new Location(7, 4);
        Location newLocationWK = new Location(7,6);
        java.util.List<Move> moves = board.get(startingLocationWK).piece.getValidMoves(board, startingLocationWK);

        assertTrue(moves.contains(new Move(startingLocationWK, newLocationWK)));

        // test that no castling move is possible when a piece has moved before
        board.get(startingLocationWK).piece.setHasMovedBefore(true);
        java.util.List<Move> movesWithoutCastling = board.get(startingLocationWK).piece.getValidMoves(board, startingLocationWK);
        assertFalse(movesWithoutCastling.contains(new Move(startingLocationWK, newLocationWK)));
    }

    @Test
    public void castleQueenSideTest() {

        ChessBoard board = new ChessBoard();

        // move away queen, bishop and knight
        Location startingLocationWQ = new Location(7, 3);
        Location newLocationWQ = new Location(5,3);
        board.movePiece(board, new Move(startingLocationWQ, newLocationWQ));
        Location startingLocationWB = new Location(7, 2);
        Location newLocationWB = new Location(5,2);
        board.movePiece(board, new Move(startingLocationWB, newLocationWB));
        Location startingLocationWN = new Location(7, 1);
        Location newLocationWN = new Location(5,1);
        board.movePiece(board, new Move(startingLocationWN, newLocationWN));

        // check if it is possible to castle queen side
        Location startingLocationWK = new Location(7, 4);
        Location newLocationWK = new Location(7,2);
        java.util.List<Move> moves = board.get(startingLocationWK).piece.getValidMoves(board, startingLocationWK);
        assertTrue(moves.contains(new Move(startingLocationWK, newLocationWK)));

        // test that no castling move is possible when a piece has moved before
        board.get(startingLocationWK).piece.setHasMovedBefore(true);
        java.util.List<Move> movesWithoutCastling = board.get(startingLocationWK).piece.getValidMoves(board, startingLocationWK);
        assertFalse(movesWithoutCastling.contains(new Move(startingLocationWK, newLocationWK)));
    }

}
