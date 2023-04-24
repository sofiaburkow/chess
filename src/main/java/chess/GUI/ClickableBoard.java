package chess.GUI;

import chess.model.GameState;
import chess.model.IBoard;
import chess.model.Move;
import grid.Grid;
import grid.Location;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class ClickableBoard extends JPanel {

    private IBoard board;
    private MouseAdapter adapter;
    private Grid<TilePanel> clickablePanels;
    private Color tileColor;

    // Locations of panels which have been selected.
    private List<Location> selectedPanels;
    // Locations of panels which represent valid moves.
    private List<Location> possibleMoves;
    // Boolean used to confirm double click of a panel to deselect all panels.
    private boolean confirmMove;

    public ClickableBoard(IBoard board) {
        this.board = board;
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

    @Override
    protected void paintComponent(Graphics g) {
        paintGameOverScreen(g);
    }

    /**
     * Should be called after a click to update the GUI to reflect
     * the current state of the board.
     */
    public void updateGui() {
        for (Location loc : board.locations()) {
            if (board.get(loc).isEmpty()) {
                clickablePanels.get(loc).setPiece(null);
                clickablePanels.get(loc).setPieceColor(null);
            } else {
                clickablePanels.get(loc).setPiece(board.get(loc).piece.getPiece());
                clickablePanels.get(loc).setPieceColor(board.get(loc).piece.getPieceColor());
            }
        }
        repaint();
    }

    /**
     * Make clickable panels. Each panel represents a tile on the chess board.
     */
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
     * Sets the given panel as a possible move in TilePanel class,
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

    /**
     * Loop through all moves, and display all of those who are valid.
     */
    private void displayValidMoves(TilePanel currentPanel, Location currentLocation) {
        // deselect all currently selected panels
        deselectPanels();
        // show all possible moves
        if (board.isValidSourceTile(currentLocation)) {
            setSelected(currentPanel);
            List<Move> moves = board.get(currentLocation).piece.getValidMoves(board, currentLocation);
            for (Move move : moves) {
                if (board.isValidMove(move)) {
                    setPossibleMove(clickablePanels.get(move.destination));
                }
            }
        }
    }

    /**
     * The method is called when the game is over. In this case it just prints out
     * whether the game ended in checkmate or stalemate.
     */
    private void gameOver() {
        if (board.getGameState() == GameState.CHECKMATE) {
            board.nextPlayer();
            System.out.printf("Checkmate! " + board.getCurrentPlayer() + " wins the game.");
            board.nextPlayer();
        } else if (board.getGameState() == GameState.STALEMATE) {
            System.out.printf("Stalemate! It's a draw.");
        }
    }

    /**
     * When the game is over, display a game over screen.
     */
    private void paintGameOverScreen(Graphics g) {
        if (board.getGameState() != GameState.CHECKMATE && board.getGameState() != GameState.STALEMATE) {
            return;
        }
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
    }

    class ClickableBoardListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent me) {

            if (clickablePanels.contains(me.getSource())) {
                try {
                    if (board.getGameState() == GameState.ACTIVE) {
                        TilePanel currentPanel = (TilePanel) me.getSource();
                        Location currentLocation = clickablePanels.locationOf(currentPanel);

                        // check if panel has been previously selected
                        if (selectedPanels.contains(currentLocation)) {
                            confirmMove = true;
                        }

                        // when no valid source tile has previously been selected
                        if (selectedPanels.size() == 0) {
                            displayValidMoves(currentPanel, currentLocation);
                        }
                        // when a valid source tile has been previously been selected
                        else if (selectedPanels.size() == 1) {
                            Move move = new Move(selectedPanels.get(0), currentLocation);
                            if (board.isValidMove(move)) {
                                if (board.get(currentLocation).isCastleMove()) {
                                    if (currentLocation.col == 6) {
                                        board.castleKingSideMove(move);
                                    } else if (currentLocation.col == 2) {
                                        board.castleQueenSideMove(move);
                                    }
                                } else if (board.get(currentLocation).isEnPassant()) {
                                    board.enPassantMove(move);
                                } else {
                                    board.movePiece(board, move);
                                }
                                deselectPanels();
                                board.get(currentLocation).piece.setHasMovedBefore(true);
                                board.addMoveToMoveHistory(move);
                                board.nextPlayer();
                            }
                            else {
                                displayValidMoves(currentPanel, currentLocation);
                            }
                        }

                        if (confirmMove) {
                            deselectPanels();
                            confirmMove = false;
                        }
                        updateGui();

                        // if it is checkmate or stalemate, end the game
                        if (board.getGameState() != GameState.ACTIVE) {
                            gameOver();
                        }

                    }
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            } else {
                System.err.println("Clicked on wrong thing: " + me.getSource());
            }
        }
    }

}