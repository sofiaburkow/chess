package chess.model.piece;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import chess.model.ChessBoard;
import chess.model.IBoard;
import chess.model.Move;
import grid.Location;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class BishopTest {

    @Test
    public void getValidMovesTest() {

        ChessBoard board = new ChessBoard();
        // move the black bishop to a new location
        Location startingLocation = new Location(0, 2);
        Location newLocation = new Location(3,2);
        board.movePiece(board, new Move(startingLocation, newLocation));
        // get possible bishop moves
        List<Move> moves = board.get(newLocation).piece.getValidMoves(board, newLocation);
        char[][] charArray = board.fromBoardToCharArray();
        String charString = board.fromCharArrayToString(charArray);

        List<Move> validMoves = new ArrayList<>();
        validMoves.add(new Move(newLocation, new Location(2,1)));
        validMoves.add(new Move(newLocation, new Location(2,3)));
        validMoves.add(new Move(newLocation, new Location(4,1)));
        validMoves.add(new Move(newLocation, new Location(5,0)));
        validMoves.add(new Move(newLocation, new Location(4,3)));
        validMoves.add(new Move(newLocation, new Location(5,4)));
        validMoves.add(new Move(newLocation, new Location(6,5)));

        assertEquals(validMoves.size(), moves.size());

        for (Move move : moves) {
            assertTrue(validMoves.contains(move));
        }
    }

}
