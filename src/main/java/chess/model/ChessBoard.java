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
     * The initials of the black pieces is in capital letters, while the
     * initials of the white pieces is in lowercase letters.
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
     */
    public void nextPlayer() {
        currentIndex = (currentIndex + 1) % 2;
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
     * Does not check whether a move is valid or not, only if it is on the board.
     */
    public void movePiece(ChessBoard board, Move move) {
        if (board.isOnGrid(move.source) && board.isOnGrid(move.destination)) {
            board.set(move.destination, board.get(move.source));
            board.set(move.source, new Tile(null));
            board.addMoveToMoveHistory(move);
        }
    }

    /**
     * Iterate over the tiles on the board, and make a list of all potential locations that
     * a piece of the opposite team might move if it was that teams turn.
     */
    public List<Location> tilesUnderAttack(ChessBoard board) {
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

    /**
     * Check whether the king of the current team is in check or not.
     *
     * @return true if check, otherwise false.
     */
    public boolean isCheck(ChessBoard board) {
        List<Location> underAttack = board.tilesUnderAttack(board);
        for (Location loc : underAttack) {
            if (!board.get(loc).isEmpty() && board.get(loc).piece.getPiece() == Type.KING) {
                return true;
            }
        }
        return false;
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

    /**
     * Check the game state of the current board. If the board is either game over or active.
     * If game over it can either be due to checkmate or stalemate.
     *
     * @return the state of the game.
     */
    public GameState getGameState() {
        if (isCheckMate()) {
            return GameState.CHECKMATE;
        } else if (isStaleMate()) {
            return GameState.STALEMATE;
        }
        return GameState.ACTIVE;
    }

    /**
     * Check whether the current player is under check and can't move the king
     * or kill the opponent threat.
     */
    private boolean isCheckMate() {
        if (isCheck(this)) {
            for (Move move : getAllMoves()) {
                ChessBoard copy = this.copy();
                copy.movePiece(this, move);
                if (!isCheck(copy)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Check whether the current player is NOT under check, but still can't move any pieces.
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

}
