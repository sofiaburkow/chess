package chess.model.piece;

import static org.junit.jupiter.api.Assertions.assertEquals;

import chess.model.ChessBoard;
import chess.model.Move;
import chess.model.Tile;
import grid.Location;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PawnTest {

    @Test
    void getPieceTest() {
        Pawn pawn = new Pawn(Color.BLACK);
        assertEquals(Type.PAWN, pawn.getPiece());
    }

    @Test
    void pawnAtStartingPositionMoves() {
        ChessBoard board = new ChessBoard();
        Location pawnLocation = new Location(1,3);
        List<Move> moves = board.get(pawnLocation).piece.getValidMoves(board,pawnLocation);

        List<Location> trueMoves = new ArrayList<>();
        trueMoves.add(new Location(2,3));
        trueMoves.add(new Location(3,3));

        for (int i = 0; i < trueMoves.size(); i++) {
            assertEquals(trueMoves.get(i), moves.get(i));
        }
    }

    @Test
    void pawnNotAtStartingPositionMoves() {
        ChessBoard board = new ChessBoard();
        Location oldPawnLocation = new Location(1,3);
        Location newPawnLocation = new Location(2,3);
        Tile pawnTile = board.get(oldPawnLocation);
        board.set(oldPawnLocation, null);
        board.set(newPawnLocation, pawnTile);

        List<Move> moves = board.get(newPawnLocation).piece.getValidMoves(board,newPawnLocation);

        List<Location> trueMoves = new ArrayList<>();
        trueMoves.add(new Location(3,3));

        for (int i = 0; i < trueMoves.size(); i++) {
            assertEquals(trueMoves.get(i), moves.get(i));
        }
    }

}
