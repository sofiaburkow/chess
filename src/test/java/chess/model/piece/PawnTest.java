package chess.model.piece;

import static org.junit.jupiter.api.Assertions.assertEquals;

import chess.model.ChessBoard;
import chess.model.ChessModel;
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
        ChessModel board = new ChessModel();
        Location pawnLocation = new Location(1,3);
        List<Location> moves = board.getTile(pawnLocation).piece.getPossibleMoves(board,pawnLocation);

        List<Location> trueMoves = new ArrayList<>();
        trueMoves.add(new Location(2,3));
        trueMoves.add(new Location(3,3));

        for (int i = 0; i < trueMoves.size(); i++) {
            assertEquals(trueMoves.get(i), moves.get(i));
        }
    }

    @Test
    void pawnNotAtStartingPositionMoves() {
        ChessModel board = new ChessModel();
        Location oldPawnLocation = new Location(1,3);
        Location newPawnLocation = new Location(2,3);
        Tile pawnTile = board.getTile(oldPawnLocation);
        board.setTile(oldPawnLocation, null);
        board.setTile(newPawnLocation, pawnTile);

        List<Location> moves = board.getTile(newPawnLocation).piece.getPossibleMoves(board,newPawnLocation);

        List<Location> trueMoves = new ArrayList<>();
        trueMoves.add(new Location(3,3));

        for (int i = 0; i < trueMoves.size(); i++) {
            assertEquals(trueMoves.get(i), moves.get(i));
        }
    }

}
