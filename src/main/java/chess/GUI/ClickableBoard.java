package chess.GUI;

import chess.model.ChessModel;
import chess.model.Tile;
import chess.model.piece.Pieces;
import grid.Grid;
import grid.Location;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class ClickableBoard extends JPanel {

    private MouseAdapter adapter;
    private Grid<TilePanel> clickablePanels;
    private ChessModel board;

    /**
     * Locations of panels which have been selected.
     */
    private List<Location> selectedPanels;

    /**
     * Boolean used to confirm double click of a panel to deselect all panels.
     */
    private boolean confirmMove;

    private Color tileColor;

    public ClickableBoard(ChessModel board) {
        this.board = board;
        adapter = new ClickableBoardListener();

        // create clickable panels
        int rows = board.numRows();
        int columns = board.numColumns();
        clickablePanels = new Grid<>(rows,columns);
        selectedPanels = new ArrayList<>();
        this.setLayout(new GridLayout(rows,columns));

        makeClickablePanels();

        // set borders
        this.setBorder(BorderFactory.createEmptyBorder(20, 20 ,20,20));
    }

    /**
     * Should be called after a click to update the GUI to reflect
     * the current state of the board.
     */
    public void updateGui() {
        for (Location loc : board.locations()) {
            // TODO: is this the best way?
            if (board.get(loc) == null) {
                clickablePanels.get(loc).setPiece(null);
                clickablePanels.get(loc).setPieceColor(null);
            } else {
                Pieces piece = board.get(loc).getPiece();
                String pieceColor = board.get(loc).getPieceColor();
                clickablePanels.get(loc).setPiece(piece);
                clickablePanels.get(loc).setPieceColor(pieceColor);
            }
        }
        validate();
        repaint();
    }

    private void makeClickablePanels() {
        for (Location loc : board.locations()) {
            if ((loc.row + loc.col) % 2 == 0) {
                // beige
                tileColor = new Color(255, 204, 130);
            }
            else {
                // brown
                tileColor = new Color(156, 103, 50);
            }
            TilePanel pan = new TilePanel(adapter, tileColor);
            clickablePanels.set(loc, pan);
            add(pan);
        }
        updateGui();
    }

    /**
     * Sets the given panel as selected in TilePanel class, and adds
     * the panel to the list of selected panels
     * @param panel
     */
    public void setSelected(TilePanel panel) {
        if (panel == null) {
            throw new NullPointerException("TilePanel is null.");
        }
        if (!clickablePanels.contains(panel)) {
            throw new IllegalArgumentException("GamePanel is not part of the grid.");
        }
        panel.setSelected(true);
        Location panelLocation = clickablePanels.locationOf(panel);
        selectedPanels.add(panelLocation);
    }

    /**
     * Iterate through all selected panels and deselect.
     * Clear list of selected panels.
     */
    public void deselectPanels() {
        for (Location loc : selectedPanels) {
            clickablePanels.get(loc).setSelected(false);
        }
        selectedPanels.clear();
    }

    public void movePiece() {
        Location initialLocation = selectedPanels.get(0);
        Location finalLocation = selectedPanels.get(1);
        Tile tile = board.get(initialLocation);
        board.setTile(initialLocation, null);
        board.setTile(finalLocation, tile);

        deselectPanels();
    }

    class ClickableBoardListener extends MouseAdapter {

        // This is what happens when the mouse clicks on one of the squares of the grid.
        @Override
        public void mousePressed(MouseEvent me) {
            if (clickablePanels.contains(me.getSource())) {
                try {
                    TilePanel currentPanel = (TilePanel) me.getSource();
                    Location currentLocation = clickablePanels.locationOf(currentPanel);
                    // check if panel has been previously selected
                    if (selectedPanels.contains(currentLocation)) {
                        confirmMove = true;
                    }
                    setSelected(currentPanel);

                    if (confirmMove) {
                        deselectPanels();
                        confirmMove = false;
                    }

                    // Move the piece
                    if (selectedPanels.size() == 2) {
                        movePiece();
                    }

                    updateGui();

                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            } else {
                System.err.println("Clicked on wrong thing: " + me.getSource());
            }
        }
    }

}
