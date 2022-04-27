package chess;

import chess.model.ChessBoard;
import chess.GUI.ClickableBoard;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ChessMain {

    public static final String WINDOW_TITLE = "Sofia's Chess Game";

    public static void main(String[] args) {

        JFrame frame = new JFrame(WINDOW_TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ChessBoard chess = new ChessBoard();
        JPanel clickableBoard = new ClickableBoard(chess);
        frame.setContentPane(clickableBoard);

        frame.pack();
        frame.setVisible(true);

        // Centers the window on the screen
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
