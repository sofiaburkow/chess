package chess.model.piece;

import chess.model.ChessBoard;
import chess.model.Move;
import grid.Location;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class KnightTest {

    @Test
    public void getValidMovesTest() {

        ChessBoard board = new ChessBoard();
        // move the white knight to a new location
        Location startingLocation = new Location(7, 6);
        Location newLocation = new Location(2,4);
        board.movePiece(board, new Move(startingLocation, newLocation));
        // get possible knight moves
        java.util.List<Move> moves = board.get(newLocation).piece.getValidMoves(board, newLocation);

        List<Move> validMoves = new ArrayList<>();
        validMoves.add(new Move(newLocation, new Location(0,3)));
        validMoves.add(new Move(newLocation, new Location(0,5)));
        validMoves.add(new Move(newLocation, new Location(1,2)));
        validMoves.add(new Move(newLocation, new Location(3,2)));
        validMoves.add(new Move(newLocation, new Location(4,3)));
        validMoves.add(new Move(newLocation, new Location(4,5)));
        validMoves.add(new Move(newLocation, new Location(3,6)));
        validMoves.add(new Move(newLocation, new Location(1,6)));

        assertEquals(validMoves.size(), moves.size());

        for (Move move : moves) {
            assertTrue(validMoves.contains(move));
        }
    }

}
