package chess.view;

import chess.model.piece.Piece;
import chess.model.piece.Pieces;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GamePanel extends JPanel implements MouseListener {

    Color color;
    private boolean mouseIsHere = false;
    private Pieces piece;

    // beginning of path for img
    private String defaultPieceImagePath = "art/pieces/";

    public GamePanel(Color color, Pieces piece) {
        this.color = color;
        this.piece = piece;
        this.addMouseListener(this);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(this.mouseIsHere ? color.darker() : color);
        g.fillRect(0,0,this.getWidth(), this.getHeight());

        if (piece != null) {
            System.out.println("jada");
        }

    }

    // TODO: why deprecated method
    @Override
    public Dimension preferredSize() {
        return new Dimension(100, 100);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        this.mouseIsHere = true;
        this.repaint();

    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.mouseIsHere = false;
        this.repaint();
    }
}
