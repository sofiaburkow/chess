package chess.GUI;

import chess.model.piece.Pieces;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

public class TilePanel extends JPanel {

    private Color tileColor;
    private boolean isSelected;
    private Pieces piece; // TODO: do i need to write null?
    private String pieceColor;

    // beginning of path of image
    private String defaultPieceImagePath = "art/pieces/";

    public TilePanel(MouseListener listener, Color tileColor) {
        this.tileColor = tileColor;
        this.piece = null;
        //setEnabled(true); //??
        //this.pieceColor = pieceColor;

        // add mouse listener which calls click method
        addMouseListener(listener);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(this.isSelected ? this.tileColor.darker() : this.tileColor);
        g.fillRect(0,0, this.getWidth(), this.getHeight());

        if (piece != null) {
            try {
                Image image = ImageIO.read(new File(defaultPieceImagePath+pieceColor+piece+".gif"));
                g.drawImage(image, 20, 20, this); // TODO: more general dimensions
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        /*
        if (isSelected) {
            g.setColor(this.tileColor);
            g.fillRect(0,0, this.getWidth(), this.getHeight());
        }
         */
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(100, 100);
    }

    /**
     * Sets the color of this panel.
     * When the updateUI function is called the
     * color will be changed to this color.
     */
    public void setPiece(Pieces piece, String pieceColor) {
        this.piece = piece;
        this.pieceColor = pieceColor;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
