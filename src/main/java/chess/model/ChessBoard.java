package chess.model;

import chess.model.piece.*;
import grid.Grid;
import grid.Location;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ChessBoard extends Grid<Tile> {

    private List<Move> moveHistory;
    private List<Team> players;
    private int currentIndex;


    public ChessBoard() {
        super(8, 8, new Tile(null));
        this.initializeBoard();

        this.moveHistory = new ArrayList<>();
        players = new ArrayList<>();
        players.add(Team.WHITE);
        players.add(Team.BLACK);
        currentIndex = 0;
    }

    /**
     * Initialize a standard chess board.
     */
    public void initializeBoard() {
        // black pieces
        this.set(new Location(0,0), new Tile(new Rook(Color.BLACK), 'R'));
        this.set(new Location(0,1), new Tile(new Knight(Color.BLACK), 'N'));
        this.set(new Location(0,2), new Tile(new Bishop(Color.BLACK), 'B'));
        this.set(new Location(0,3), new Tile(new Queen(Color.BLACK), 'Q'));
        this.set(new Location(0,4), new Tile(new King(Color.BLACK), 'K'));
        this.set(new Location(0,5), new Tile(new Bishop(Color.BLACK), 'B'));
        this.set(new Location(0,6), new Tile(new Knight(Color.BLACK), 'N'));
        this.set(new Location(0,7), new Tile(new Rook(Color.BLACK), 'R'));

        this.set(new Location(1,0), new Tile(new Pawn(Color.BLACK), 'P'));
        this.set(new Location(1,1), new Tile(new Pawn(Color.BLACK), 'P'));
        this.set(new Location(1,2), new Tile(new Pawn(Color.BLACK), 'P'));
        this.set(new Location(1,3), new Tile(new Pawn(Color.BLACK), 'P'));
        this.set(new Location(1,4), new Tile(new Pawn(Color.BLACK), 'P'));
        this.set(new Location(1,5), new Tile(new Pawn(Color.BLACK), 'P'));
        this.set(new Location(1,6), new Tile(new Pawn(Color.BLACK), 'P'));
        this.set(new Location(1,7), new Tile(new Pawn(Color.BLACK), 'P'));

        // white pieces
        this.set(new Location(6,0), new Tile(new Pawn(Color.WHITE), 'p'));
        this.set(new Location(6,1), new Tile(new Pawn(Color.WHITE), 'p'));
        this.set(new Location(6,2), new Tile(new Pawn(Color.WHITE), 'p'));
        this.set(new Location(6,3), new Tile(new Pawn(Color.WHITE), 'p'));
        this.set(new Location(6,4), new Tile(new Pawn(Color.WHITE), 'p'));
        this.set(new Location(6,5), new Tile(new Pawn(Color.WHITE), 'p'));
        this.set(new Location(6,6), new Tile(new Pawn(Color.WHITE), 'p'));
        this.set(new Location(6,7), new Tile(new Pawn(Color.WHITE), 'p'));

        this.set(new Location(7,0), new Tile(new Rook(Color.WHITE), 'r'));
        this.set(new Location(7,1), new Tile(new Knight(Color.WHITE), 'n'));
        this.set(new Location(7,2), new Tile(new Bishop(Color.WHITE), 'b'));
        this.set(new Location(7,3), new Tile(new Queen(Color.WHITE), 'q'));
        this.set(new Location(7,4), new Tile(new King(Color.WHITE), 'k'));
        this.set(new Location(7,5), new Tile(new Bishop(Color.WHITE), 'b'));
        this.set(new Location(7,6), new Tile(new Knight(Color.WHITE), 'n'));
        this.set(new Location(7,7), new Tile(new Rook(Color.WHITE), 'r'));
    }

    /**
     * @return a list of previous moves.
     */
    public List<Move> getMoveHistory() {
        return moveHistory;
    }

    /**
     * Add a new move to the list of former moves.
     */
    public void addMoveToMoveHistory(Move move) {
        moveHistory.add(move);
    }

    /**
     * @return the team of the current player.
     */
    public Team getCurrentPlayer() {
        return players.get(currentIndex);
    }

    /**
     * Move on to the next player.
     *
     * @return the team of the next player.
     */
    public Team getNextPlayer() {
        currentIndex = (currentIndex + 1) % 2;
        return getCurrentPlayer();
    }

    /**
     * Make a copy of the current chess board. Both the tiles and which teams
     * turn it is, should be the same as with the original board.
     */
    public ChessBoard copy() {
        ChessBoard copy = new ChessBoard();
        for (Location loc : this.locations()) {
            copy.set(loc, this.get(loc));
        }
        copy.currentIndex = currentIndex;
        return copy;
    }

    /**
     * Check whether the given source tile is valid or not.
     *
     * @return the true if the location contains a piece of the current player, otherwise false.
     */
    public boolean isValidSourceTile(Location loc) {
        if (this.get(loc).isEmpty() || this.get(loc).piece.getTeam() != getCurrentPlayer()) {
            return false;
        }
        return true;
    }

    /**
     * Check whether a move is valid or not.
     */
    public boolean isValidMove(Move move) {
        if (this.get(move.source).isEmpty()) {
            return false;
        }
        return this.get(move.source).piece.canMove(this, move);
    }

    /**
     * Move the piece from a given tile to another, and set the source tile
     * equal to a new empty tile.
     */
    public void movePiece(ChessBoard board, Move move) {
        board.set(move.destination, board.get(move.source));
        board.set(move.source, new Tile(null));
    }

    /**
     * Iterate over the tiles on the board, and make a list of all potential locations that
     * a piece of the opposite team might move if it was that teams turn.
     */
    public List<Location> tilesUnderAttack(ChessBoard board) {
        List<Location> underAttack = new ArrayList<>();
        for (Location loc : board.locations()) {
            if (board.isOnGrid(loc) && !board.get(loc).isEmpty()) {
                if (board.get(loc).piece.getTeam() != getCurrentPlayer() && board.get(loc).piece.getPiece() != Type.KING) {
                    List<Move> moves = board.get(loc).piece.getValidMoves(board, loc);
                    for (Move move : moves) {
                        underAttack.add(move.destination);
                    }
                }
            }
        }
        return underAttack;
    }

    /**
     * Check whether a given move results in check.
     * @return true if check, otherwise false.
     */
    public boolean resultsInCheck(Move move) {
        ChessBoard copy = this.copy();
        movePiece(copy, move);
        List<Location> underAttack = tilesUnderAttack(copy);
        for (Location loc : underAttack) {
            if (!copy.get(loc).isEmpty() && copy.get(loc).piece.getPiece() == Type.KING) {
                return true;
            }
        }
        return  false;
    }

    /**
     * Make a castle move on the king side of the board. Move both
     * the king and the castle.
     */
    public void castleKingSideMove(Move move) {
        // move the king
        movePiece(this, move);
        // move the castle itself
        Location castleFrom = new Location(move.source.row, move.source.col+3);
        Location castleTo = new Location(move.source.row, move.source.col+1);
        movePiece(this, new Move(castleFrom, castleTo));
    }

    /**
     * Make a castle move on the queen side of the board. Move both
     * the king and the castle.
     */
    public void castleQueenSideMove(Move move) {
        // move the king
        movePiece(this, move);
        // move the castle itself
        Location castleFrom = new Location(move.source.row, move.source.col-4);
        Location castleTo = new Location(move.source.row, move.source.col-1);
        movePiece(this, new Move(castleFrom, castleTo));
    }

    /**
     * Do an en passant move. That includes moving the pawn of the
     * current team one tile diagonally, and removing the neighbouring pawn
     * which got killed en passant.
     */
    public void enPassantMove(Move move) {
        movePiece(this, move);
        // remove the pawn of the opposite team
        if (move.destination.row == 2) {
            this.set(new Location(move.destination.row+1, move.destination.col), new Tile(null));
        } else {
            this.set(new Location(move.destination.row-1, move.destination.col), new Tile(null));
        }
    }

}
