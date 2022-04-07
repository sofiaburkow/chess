package chess.view;

import chess.model.piece.Pieces;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

public class GamePanel extends JPanel implements MouseListener {

    Color color;
    private boolean mouseIsHere = false;
    private Pieces piece;
    private String pieceColor;

    // beginning of path for img
    private String defaultPieceImagePath = "art/pieces/";

    public GamePanel(Color color, Pieces piece, String pieceColor) {
        this.color = color;
        this.piece = piece;
        this.pieceColor = pieceColor;
        this.addMouseListener(this);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(this.mouseIsHere ? color.darker() : color);
        g.fillRect(0,0,this.getWidth(), this.getHeight());

        if (piece != null) {
            try {
                Image image = ImageIO.read(new File(defaultPieceImagePath+pieceColor+piece+".gif"));
                g.drawImage(image, 20, 20, this);
            } catch (IOException e) {
                e.printStackTrace();
            }
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
