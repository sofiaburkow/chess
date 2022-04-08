package chess;

import chess.model.ChessModel;
import chess.view.ClickableBoard;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ChessMain {

    public static final String WINDOW_TITLE = "Sofia's Chess Game";

    public static void main(String[] args) {

        JFrame frame = new JFrame(WINDOW_TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ChessModel chess = new ChessModel();
        JPanel clickableBoard = new ClickableBoard(chess);
        frame.setContentPane(clickableBoard);

        frame.pack();
        frame.setVisible(true);
    }
}
