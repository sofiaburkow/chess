package chess.view;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GamePanel extends JPanel implements MouseListener {

    Color color;
    private boolean mouseIsHere = false;

    public GamePanel(Color color) {
        this.color = color;
        this.addMouseListener(this);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(this.mouseIsHere ? color.darker() : color);
        g.fillRect(0,0,this.getWidth(), this.getHeight());
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
