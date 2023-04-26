package chess.model.piece;

import chess.model.ChessBoard;
import chess.model.Move;
import grid.Location;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class QueenTest {

    @Test
    void getValidMovesTest() {

        ChessBoard board = new ChessBoard();
        // move the black queen to a new location
        Location startingLocation = new Location(0, 3);
        Location newLocation = new Location(2,1);
        board.movePiece(board, new Move(startingLocation, newLocation));
        // get possible queen moves
        java.util.List<Move> moves = board.get(newLocation).piece.getValidMoves(board, newLocation);

        List<Move> validMoves = new ArrayList<>();
        validMoves.add(new Move(newLocation, new Location(2,0)));
        validMoves.add(new Move(newLocation, new Location(3,0)));
        validMoves.add(new Move(newLocation, new Location(3,1)));
        validMoves.add(new Move(newLocation, new Location(4,1)));
        validMoves.add(new Move(newLocation, new Location(5,1)));
        validMoves.add(new Move(newLocation, new Location(6,1)));
        validMoves.add(new Move(newLocation, new Location(3,2)));
        validMoves.add(new Move(newLocation, new Location(4,3)));
        validMoves.add(new Move(newLocation, new Location(5,4)));
        validMoves.add(new Move(newLocation, new Location(6,5)));
        validMoves.add(new Move(newLocation, new Location(2,2)));
        validMoves.add(new Move(newLocation, new Location(2,3)));
        validMoves.add(new Move(newLocation, new Location(2,4)));
        validMoves.add(new Move(newLocation, new Location(2,5)));
        validMoves.add(new Move(newLocation, new Location(2,6)));
        validMoves.add(new Move(newLocation, new Location(2,7)));

        // I do not test whether the size of these lists are equal since moves contains a valid move multiple times
        for (Move move : moves) {
            assertTrue(validMoves.contains(move));
        }
    }

}
