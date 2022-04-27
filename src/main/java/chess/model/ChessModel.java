package chess.model;

import chess.model.piece.Type;
import grid.GridLocationIterator;
import grid.Location;

import java.util.ArrayList;
import java.util.List;

public class ChessModel {

    private ChessBoard board;
    private ArrayList<Team> players;
    private int currentIndex;
    private List<Move> moveHistory;

    public ChessModel () {
        this.board = new ChessBoard(8,8, new Tile(null));
        this.moveHistory = new ArrayList<>();
        players = new ArrayList<>(2);
        players.add(Team.WHITE);
        players.add(Team.BLACK);
        currentIndex = 0;
        board.initializeBoard();
    }

    public List<Move> getMoveHistory() {
        return this.moveHistory;
    }

    public void addMoveToMoveHistory(Move move) {
        moveHistory.add(move);
    }

    public Tile getTile(Location loc) {
        return board.get(loc);
    }

    public void setTile(Location loc, Tile tile) {
        board.set(loc, tile);
    }

    public int numRows() {
        return board.numRows();
    }

    public int numColumns() {
        return board.numColumns();
    }

    public GridLocationIterator locations() {
        return board.locations();
    }

    public Team getCurrentPlayer() {
        return players.get(currentIndex);
    }

    public Team nextPlayer() {
        currentIndex = (currentIndex+1) % 2;
        return getCurrentPlayer();
    }

    public boolean isOnBoard(Location loc) {
        return board.isOnGrid(loc);
    }

    public boolean validMove(Move move) {
        if (this.getTile(move.source).isEmpty()) {
            return false;
        } else if (resultsInChess(move)) {
            return false;
        }
        return this.getTile(move.source).piece.canMove(this, move);
    }

    public ChessModel copy() {
        ChessModel copy = new ChessModel();
        for (Location loc : this.locations()) {
            copy.setTile(loc, this.getTile(loc));
        }
        copy.currentIndex = currentIndex;
        return copy;
    }

    /**
     * Move the piece from the source tile to the destination tile and empty the source tile.
     */
    public void movePiece(ChessModel chessModel, Move move) {
        chessModel.setTile(move.destination, chessModel.getTile(move.source));
        chessModel.setTile(move.source, new Tile(null));
    }

    public void castleKingSideMove(Move move) {
        movePiece(this, move);
        Location castleFrom = new Location(move.source.row, move.source.col+3);
        Location castleTo = new Location(move.source.row, move.source.col+1);
        movePiece(this, new Move(castleFrom, castleTo));
    }

    public void castleQueenSideMove(Move move) {
        movePiece(this, move);
        Location castleFrom = new Location(move.source.row, move.source.col-4);
        Location castleTo = new Location(move.source.row, move.source.col-1);
        movePiece(this, new Move(castleFrom, castleTo));
    }

    public void enPassantMove(Move move) {
        movePiece(this, move);
        if (move.destination.row == 2) {
            this.setTile(new Location(move.destination.row+1, move.destination.col), new Tile(null));
        } else {
            this.setTile(new Location(move.destination.row-1, move.destination.col), new Tile(null));
        }
    }

    /**
     * @return all tiles under attack from the opposite team.
     */
    public List<Location> tilesUnderAttack(ChessModel chessModel) {
        List<Location> underAttack = new ArrayList<>();
        for (Location loc : board.locations()) {
            if (chessModel.isOnBoard(loc) && !chessModel.getTile(loc).isEmpty()) {
                if (chessModel.getTile(loc).piece.getTeam() != getCurrentPlayer() && chessModel.getTile(loc).piece.getPiece() != Type.KING) {
                    List<Move> moves = chessModel.getTile(loc).piece.getValidMoves(chessModel, loc);
                    for (Move move : moves) {
                        underAttack.add(move.destination);
                    }
                }
            }
        }
        return underAttack;
    }

    public boolean isCheck() {
        for (Location loc : this.tilesUnderAttack(this)) {
            if (!this.getTile(loc).isEmpty() && this.getTile(loc).piece.getPiece() == Type.KING) {
                return true;
            }
        }
        return false;
    }

    public boolean resultsInChess(Move move) {
        ChessModel copy = this.copy();
        movePiece(copy, move);
        List<Location> tilesUnderAttack = tilesUnderAttack(copy);
        for (Location attack : tilesUnderAttack) {
            if (!copy.getTile(attack).isEmpty() && copy.getTile(attack).piece.getPiece() == Type.KING) {
                return true;
            }
        }
        return  false;
    }


}
