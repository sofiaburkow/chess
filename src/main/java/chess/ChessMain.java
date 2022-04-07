package chess;

import chess.view.ClickableBoard;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ChessMain {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Sofia's Chess Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel clickableBoard = new ClickableBoard();
        frame.setContentPane(clickableBoard);

        frame.pack();
        frame.setVisible(true);
    }
}
