package chess.model;

import grid.Location;

public class Move {

    private Location source;
    private Location destination;
    boolean castleMove;
    boolean enPassant;

    public Move(Location source, Location destination, boolean castleMove, boolean enPassant) {
        this.source = source;
        this.destination = destination;
        this.castleMove = castleMove;
        this.enPassant = enPassant;
    }

    public boolean isEnPassant() {
        return enPassant;
    }

    public boolean isCastleMove() {
        return castleMove;
    }
}
