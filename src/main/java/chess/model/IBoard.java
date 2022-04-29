package chess.model;

import grid.GridLocationIterator;
import grid.Location;

import java.util.List;

public interface IBoard {

    /**
     * @return number of rows on the board.
     */
    int numRows();

    /**
     * @return number of columns on the board.
     */
    int numColumns();

    /**
     * Set the tile at the given location on the board.
     */
    void set(Location loc, Tile tile);

    /**
     * Get the tile on the given location.
     */
    Tile get(Location loc);

    /**
     * Check whether the given location is within the bounds of the board.
     *
     * @return true if the location is within bounds, false otherwise.
     */
    boolean isOnGrid(Location loc);

    /**
     * Iterate over all grid locations
     */
    GridLocationIterator locations();

    /**
     * Add a new move to the list of previous moves.
     */
    void addMoveToMoveHistory(Move move);

    /**
     * @return a list of previous game moves.
     */
    List<Move> getMoveHistory();

    /**
     * @return the team of the current player.
     */
    Team getCurrentPlayer();

    /**
     * Move on to the next player. This is done by increasing the index used
     * to calculate who the current player is.
     */
    void nextPlayer();

    /**
     * Iterate over the tiles on the board, and make a list of all potential locations that
     * a piece of the opposite team poses a threat to.
     */
    List<Location> tilesUnderAttack(IBoard board);

    /**
     * Check whether the king of the current team is in check or not.
     *
     * @return true if check, otherwise false.
     */
    boolean isCheck(IBoard board);

    /**
     * Check whether a given move results in check.
     * @return true if check, otherwise false.
     */
    boolean moveResultsInCheck(Move move);

    /**
     * Check whether the given source tile is valid or not.
     *
     * @return the true if the location contains a piece of the current player, otherwise false.
     */
    boolean isValidSourceTile(Location loc);

    /**
     * Check whether a move is valid or not.
     *
     * @return true if valid, false otherwise.
     */
    boolean isValidMove(Move move);

    /**
     * Move the piece from a given tile to another, and set the source tile
     * equal to an empty new tile.
     * Does not check whether a move is valid or not, only if it is on the board.
     */
    void movePiece(IBoard board, Move move);

    /**
     * Make a castle move on the king side of the board. Move both
     * the king and the castle.
     */
    void castleKingSideMove(Move move);

    /**
     * Make a castle move on the queen side of the board. Move both
     * the king and the castle.
     */
    void castleQueenSideMove(Move move);

    /**
     * Do an en passant move. That includes moving the pawn of the
     * current team one tile diagonally, and removing the neighbouring pawn
     * which got killed en passant.
     */
    void enPassantMove(Move move);

    /**
     * Check the game state of the current board. If the board is either game over or active.
     * If game over it can either be due to checkmate or stalemate.
     *
     * @return the state of the game.
     */
    GameState getGameState();

}
