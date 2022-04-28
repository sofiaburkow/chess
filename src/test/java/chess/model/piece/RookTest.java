package chess.model.piece;

import chess.model.ChessBoard;
import chess.model.Move;
import grid.Location;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RookTest {

    @Test
    void getValidMovesTest() {

        ChessBoard board = new ChessBoard();
        // move the white rook to a new location
        Location startingLocation = new Location(7, 7);
        Location newLocation = new Location(4,4);
        board.movePiece(board, new Move(startingLocation, newLocation));
        // get possible rook moves
        List<Move> moves = board.get(newLocation).piece.getValidMoves(board, newLocation);

        List<Move> validMoves = new ArrayList<>();
        validMoves.add(new Move(newLocation, new Location(4,3)));
        validMoves.add(new Move(newLocation, new Location(4,2)));
        validMoves.add(new Move(newLocation, new Location(4,1)));
        validMoves.add(new Move(newLocation, new Location(4,0)));
        validMoves.add(new Move(newLocation, new Location(3,4)));
        validMoves.add(new Move(newLocation, new Location(2,4)));
        validMoves.add(new Move(newLocation, new Location(1,4)));
        validMoves.add(new Move(newLocation, new Location(4,5)));
        validMoves.add(new Move(newLocation, new Location(4,6)));
        validMoves.add(new Move(newLocation, new Location(4,7)));
        validMoves.add(new Move(newLocation, new Location(5,4)));

        assertEquals(validMoves.size(), moves.size());

        for (Move move : moves) {
            assertTrue(validMoves.contains(move));
        }

    }
}
