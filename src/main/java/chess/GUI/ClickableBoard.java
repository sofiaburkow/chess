package chess.GUI;

import chess.model.ChessModel;
import chess.model.Move;
import chess.model.Team;
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
    private Team currentPlayer;
    private Color tileColor;

    /**
     * Locations of panels which have been selected.
     */
    private List<Location> selectedPanels;

    /**
     * Locations of panels which represents valid moves.
     */
    private List<Location> possibleMoves;

    /**
     * Boolean used to confirm double click of a panel to deselect all panels.
     */
    private boolean confirmMove;

    public ClickableBoard(ChessModel board) {
        this.board = board;
        this.currentPlayer = board.getCurrentPlayer();
        adapter = new ClickableBoardListener();

        // create clickable panels
        int rows = board.numRows();
        int columns = board.numColumns();
        clickablePanels = new Grid<>(rows,columns);
        selectedPanels = new ArrayList<>();
        possibleMoves = new ArrayList<>();
        this.setLayout(new GridLayout(rows,columns));
        this.setBorder(BorderFactory.createEmptyBorder(20, 20 ,20,20));
        makeClickablePanels();
    }

    /**
     * Should be called after a click to update the GUI to reflect
     * the current state of the board.
     */
    public void updateGui() {
        for (Location loc : board.locations()) {
            if (board.getTile(loc).isEmpty()) {
                clickablePanels.get(loc).setPiece(null);
                clickablePanels.get(loc).setPieceColor(null);
            } else {
                clickablePanels.get(loc).setPiece(board.getTile(loc).piece.getPiece());
                clickablePanels.get(loc).setPieceColor(board.getTile(loc).piece.getPieceColor());
            }
        }
        validate(); //TODO: why?
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
     * Sets the given panel as sa possible move in TilePanel class,
     * and adds the panel to the list of possible moves
     */
    public void setPossibleMove(TilePanel panel) {
        if (panel == null) {
            throw new NullPointerException("TilePanel is null.");
        }
        if (!clickablePanels.contains(panel)) {
            throw new IllegalArgumentException("GamePanel is not part of the grid.");
        }
        panel.setPossibleMove(true);
        Location panelLocation = clickablePanels.locationOf(panel);
        possibleMoves.add(panelLocation);
    }

    /**
     * Iterate through all selected panels and panels of possible moves and deselect.
     * Clear list of selected panels and possible moves.
     */
    public void deselectPanels() {
        for (Location loc : selectedPanels) {
            clickablePanels.get(loc).setSelected(false);
        }
        selectedPanels.clear();
        for (Location loc : possibleMoves) {
            clickablePanels.get(loc).setPossibleMove(false);
        }
        possibleMoves.clear();
    }

    public boolean validSourceTile(Location loc) {
        if (board.getTile(loc).isEmpty() || board.getTile(loc).piece.getTeam() != currentPlayer) {
            return false;
        }
        return true;
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
                    if (selectedPanels.size() == 0) {
                        if (validSourceTile(currentLocation)) {
                            setSelected(currentPanel);
                            // display valid moves
                            List<Move> moves = board.getTile(currentLocation).piece.getValidMoves(board,currentLocation);
                            for (Move move : moves) {
                                if (board.validMove(move)) {
                                    setPossibleMove(clickablePanels.get(move.destination));
                                }
                            }
                        }
                    } else if (selectedPanels.size() == 1) {
                        Move move = new Move(selectedPanels.get(0), currentLocation);
                        if (board.validMove(move)) {
                            if (board.getTile(currentLocation).isCastleMove()) {
                                if (currentLocation.col == 6) {
                                    board.castleKingSideMove(move);
                                } else if (currentLocation.col == 2) {
                                    board.castleQueenSideMove(move);
                                }
                            } else if (board.getTile(currentLocation).isEnPassant()) {
                                board.enPassantMove(move);
                            } else {
                                board.movePiece(board, move);
                            }
                            board.getTile(currentLocation).piece.setHasMovedBefore(true);
                            currentPlayer = board.nextPlayer();
                            deselectPanels();
                            board.addMoveToMoveHistory(move);
                        }

                    }
                    if (confirmMove) {
                        deselectPanels();
                        confirmMove = false;
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
