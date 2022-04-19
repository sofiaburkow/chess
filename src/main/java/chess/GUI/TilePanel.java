package chess.GUI;

import chess.model.piece.Type;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

public class TilePanel extends JPanel {

    private Color tileColor;
    private Type piece;
    private String pieceColor;
    private boolean isSelected;
    private boolean isPossibleMove;

    // path to image
    private String pieceImagePath = "art/pieces/";

    public TilePanel(MouseListener listener, Color tileColor) {
        this.tileColor = tileColor;
        this.piece = null;
        this.pieceColor = null;
        setPreferredSize(new Dimension(100, 100));
        // add mouse listener which calls click method
        addMouseListener(listener);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(this.isSelected ? this.tileColor.darker() : this.tileColor);
        g.fillRect(0,0, this.getWidth(), this.getHeight());
        //g.setColor(this.isPossibleMove ? this.tileColor.darker() : this.tileColor);

        if (piece != null) {
            try {
                Image image = ImageIO.read(new File(pieceImagePath+pieceColor+piece+".gif"));
                int width = this.getWidth()/2-image.getWidth(this)/2;
                int height = this.getHeight()/2-image.getHeight(this)/2;
                g.drawImage(image, width, height, this);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (this.isPossibleMove) {
            if (piece == null) {
                g.setColor(Color.WHITE.darker());

            } else {
                g.setColor(Color.RED.darker());
                //g.drawOval(10, 10, getWidth()-20, getHeight()-20);
            }
            g.fillOval(getWidth() / 2 - getWidth() / 14, getHeight() / 2 - getHeight() / 14, getWidth() / 7, getHeight() / 7);
        }
    }

    /**
     * Sets the piece of this panel.
     * The piece will change when the updateGUI function is called.
     */
    public void setPiece(Type piece) {
        this.piece = piece;
    }

    /**
     * Sets the color og the piece of this panel.
     * The color will change when the updateGUI function is called.
     */
    public void setPieceColor(String pieceColor) {
        this.pieceColor = pieceColor;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public void setPossibleMove(boolean possibleMove) {
        isPossibleMove = possibleMove;
    }

}
