package chess.GUI;

import chess.model.piece.IPiece;
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
        // draw the tile itself
        g.setColor(this.isSelected ? this.tileColor.darker() : this.tileColor);
        g.fillRect(0,0, this.getWidth(), this.getHeight());
        // draw image of piece
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
        // draw dots and circles according to where the piece can move
        if (this.isPossibleMove) {
            g.setColor(new Color(142, 176, 119));
            if (piece == null) {
                g.fillOval(getWidth()/2-getWidth()/16, getHeight()/2-getHeight()/16, getWidth()/8, getHeight()/8);
            } else {
                g.drawOval(5, 5, getWidth()-10, getHeight()-10);
            }
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
