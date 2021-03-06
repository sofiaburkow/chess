package chess.model;

import chess.model.piece.*;
import grid.Grid;
import grid.Location;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ChessBoard extends Grid<Tile> implements IBoard {

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
     * Convert the current chess board to an array of chars.
     *
     * @return an array of chars.
     */
    public char[][] fromBoardToCharArray() {
        char[][] charArray = new char[this.numRows()][this.numColumns()];
        for (Location loc : this.locations()) {
            charArray[loc.row][loc.col] = this.get(loc).initial;
        }
        return charArray;
    }

    /**
     * Convert a two-dimensional array of chars to a string with a new line
     * character separating the rows.
     *
     * @return string with chars.
     */
    public String fromCharArrayToString(char[][] charArray) {
        String charString = "";
        for (int row = 0; row < this.numRows(); row++) {
            for (int col = 0; col < this.numColumns(); col++) {
                charString += charArray[row][col];
            }
            charString += "\n";
        }
        return charString;
    }

    /**
     * Initialize a standard chess board.
     * The initials of the black pieces are in capital letters, while the
     * initials of the white pieces are in lowercase letters.
     */
    private void initializeBoard() {
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

    public void addMoveToMoveHistory(Move move) {
        moveHistory.add(move);
    }

    public List<Move> getMoveHistory() {
        return moveHistory;
    }

    public Team getCurrentPlayer() {
        return players.get(currentIndex);
    }

    public void nextPlayer() {
        currentIndex = (currentIndex + 1) % 2;
    }

    public List<Location> tilesUnderAttack(IBoard board) {
        List<Location> underAttack = new ArrayList<>();
        for (Location loc : board.locations()) {
            if (board.isOnGrid(loc) && !board.get(loc).isEmpty()) {
                if (board.get(loc).piece.getTeam() != getCurrentPlayer()) {
                    if (board.get(loc).piece.getPiece() != Type.KING) {
                        List<Move> moves = board.get(loc).piece.getValidMoves(board, loc);
                        for (Move move : moves) {
                            if (!move.isNotCaptureMove()) {
                                underAttack.add(move.destination);
                            }
                        }
                    }
                }
            }
        }
        return underAttack;
    }

    public boolean isCheck(IBoard board) {
        List<Location> underAttack = board.tilesUnderAttack(board);
        for (Location loc : underAttack) {
            if (!board.get(loc).isEmpty() && board.get(loc).piece.getPiece() == Type.KING) {
                return true;
            }
        }
        return false;
    }

    public boolean moveResultsInCheck(Move move) {
        IBoard copy = this.copy();
        movePiece(copy, move);
        List<Location> underAttack = tilesUnderAttack(copy);
        for (Location loc : underAttack) {
            if (!copy.get(loc).isEmpty() && copy.get(loc).piece.getPiece() == Type.KING) {
                return true;
            }
        }
        return  false;
    }

    public boolean isValidSourceTile(Location loc) {
        if (this.get(loc).isEmpty() || this.get(loc).piece.getTeam() != getCurrentPlayer()) {
            return false;
        }
        return true;
    }

    public boolean isValidMove(Move move) {
        if (this.get(move.source).isEmpty()) {
            return false;
        }
        return this.get(move.source).piece.canMove(this, move);
    }

    public void movePiece(IBoard board, Move move) {
        if (board.isOnGrid(move.source) && board.isOnGrid(move.destination)) {
            board.set(move.destination, board.get(move.source));
            board.set(move.source, new Tile(null));
        }
    }

    public void castleKingSideMove(Move move) {
        // move the king
        movePiece(this, move);
        // move the castle itself
        Location castleFrom = new Location(move.source.row, move.source.col+3);
        Location castleTo = new Location(move.source.row, move.source.col+1);
        movePiece(this, new Move(castleFrom, castleTo));
    }

    public void castleQueenSideMove(Move move) {
        // move the king
        movePiece(this, move);
        // move the castle itself
        Location castleFrom = new Location(move.source.row, move.source.col-4);
        Location castleTo = new Location(move.source.row, move.source.col-1);
        movePiece(this, new Move(castleFrom, castleTo));
    }

    public void enPassantMove(Move move) {
        movePiece(this, move);
        // remove the pawn of the opposite team
        if (move.destination.row == 2) {
            this.set(new Location(move.destination.row+1, move.destination.col), new Tile(null));
        } else {
            this.set(new Location(move.destination.row-1, move.destination.col), new Tile(null));
        }
    }

    public GameState getGameState() {
        if (isCheckMate()) {
            return GameState.CHECKMATE;
        } else if (isStaleMate()) {
            return GameState.STALEMATE;
        }
        return GameState.ACTIVE;
    }

    /**
     * Check whether it is checkmate. This is the case if the current player
     * is under check and can't move the king or kill the piece threatening the king.
     */
    private boolean isCheckMate() {
        if (isCheck(this)) {
            List<Move> moves = getAllMoves();
            for (Move move : moves) {
                IBoard copy = this.copy();
                copy.movePiece(copy, move);
                if (!isCheck(copy)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Check whether the current player can't move any of its peaces. The king is also not in check.
     */
    private boolean isStaleMate() {
        if (!isCheck(this)) {
            if (getAllMoves().size() == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method to get all valid moves of the current team.
     */
    private List<Move> getAllMoves() {
        List<Move> possibleMoves = new ArrayList<>();
        for (Location loc : this.locations()) {
            if (!this.get(loc).isEmpty() && this.get(loc).piece.getTeam() == getCurrentPlayer()) {
                List<Move> moves = this.get(loc).piece.getValidMoves(this, loc);
                for (Move move : moves) {
                    possibleMoves.add(move);
                }
            }
        }
        return possibleMoves;
    }

    /**
     * Make a copy of the current chess board. Both the tiles and which teams
     * turn it is, should be the same as with the original board.
     *
     * Overrides the method in grid.
     */
    @Override
    public ChessBoard copy() {
        ChessBoard copy = new ChessBoard();
        for (Location loc : this.locations()) {
            copy.set(loc, this.get(loc));
        }
        copy.currentIndex = currentIndex;
        return copy;
    }

}
