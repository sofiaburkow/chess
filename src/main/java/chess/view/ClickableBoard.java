package chess.view;

import chess.model.ChessBoard;
import chess.model.piece.Piece;
import chess.model.piece.Pieces;
import grid.Grid;
import grid.Location;

import javax.swing.*;
import java.awt.*;

public class ClickableBoard extends JPanel {

    private Grid<GamePanel> clickablePanels;
    private ChessBoard board;

    private Color color;
    private Pieces piece;

    public ClickableBoard() {
        this.board = new ChessBoard(8,8,null);

        int rows = board.numRows();
        int columns = board.numColumns();
        //clickablePanels = new Grid<>(rows,columns);
        this.setLayout(new GridLayout(rows,columns));
        this.setBorder(BorderFactory.createEmptyBorder(50,  350,50,350));
        makeClickablePanels();
    }

    private void makeClickablePanels() {
        for (Location loc : board.locations()) {
            if ((loc.row + loc.col) % 2 == 0) {
                // beige
                color = new Color(255, 204, 130);
            }
            else {
                // brown
                color = new Color(156, 103, 50);
            }

            if (board.get(loc) == null) {
                Pieces piece = null;
            }
            else {
                Pieces piece = board.get(loc).piece.getPiece();
            }
            GamePanel pan = new GamePanel(color, piece);
            this.add(pan);

            //clickablePanels.set(loc, pan);
        }
        // update GUI();
    }

    private void placePiecesOnBoard() {



    }


}
