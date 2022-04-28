package chess.model.piece;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import chess.model.ChessBoard;
import chess.model.Move;
import grid.Location;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class PawnTest {

    @Test
    public void pawnAtStartingPositionMoves() {

        ChessBoard board = new ChessBoard();
        Location startingLocationWP = new Location(6, 4);
        java.util.List<Move> moves = board.get(startingLocationWP).piece.getValidMoves(board, startingLocationWP);

        List<Move> validMoves = new ArrayList<>();
        validMoves.add(new Move(startingLocationWP, new Location(5,4)));
        validMoves.add(new Move(startingLocationWP, new Location(4,4)));

        assertEquals(validMoves.size(), moves.size());
        for (Move move : moves) {
            assertTrue(validMoves.contains(move));
        }
    }

    @Test
    public void pawnNotAtStartingPositionMoves() {

        ChessBoard board = new ChessBoard();
        // move some pieces around
        Location startingLocationBB = new Location(0, 2);
        Location newLocationBB = new Location(3,3);
        Location startingLocationWP = new Location(6, 4);
        Location newLocationWP = new Location(4,4);
        board.movePiece(board, new Move(startingLocationBB, newLocationBB));
        board.movePiece(board, new Move(startingLocationWP, newLocationWP));
        board.get(newLocationWP).piece.setHasMovedBefore(true);

        // get possible pawn moves
        java.util.List<Move> moves = board.get(newLocationWP).piece.getValidMoves(board, newLocationWP);

        List<Move> validMoves = new ArrayList<>();
        validMoves.add(new Move(newLocationWP, new Location(3,3)));
        validMoves.add(new Move(newLocationWP, new Location(3,4)));

        assertEquals(validMoves.size(), moves.size());
        for (Move move : moves) {
            assertTrue(validMoves.contains(move));
        }
    }

    @Test
    public void enPassantTest() {

        ChessBoard board = new ChessBoard();
        // move the attacking white pawn
        Location startingLocationWP = new Location(6, 5);
        Location newLocationWP = new Location(3,5);
        board.movePiece(board, new Move(startingLocationWP, newLocationWP));
        // move the black pawn to a location which allows for en passant
        Location startingLocationBP = new Location(1, 6);
        Location newLocationBP = new Location(3,6);
        board.movePiece(board, new Move(startingLocationBP, newLocationBP));
        board.addMoveToMoveHistory(new Move(startingLocationBP, newLocationBP));

        // get possible white pawn moves
        java.util.List<Move> moves = board.get(newLocationWP).piece.getValidMoves(board, newLocationWP);

        List<Move> validMoves = new ArrayList<>();
        validMoves.add(new Move(newLocationWP, new Location(2,5)));
        validMoves.add(new Move(newLocationWP, new Location(2,6)));

        assertEquals(validMoves.size(), moves.size());
        for (Move move : moves) {
            assertTrue(validMoves.contains(move));
        }
    }

}
