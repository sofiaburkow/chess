package chess.model;

import grid.Location;

import java.util.Objects;

public class Move {

    public final Location source;
    public final Location destination;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Move)) return false;
        Move move = (Move) o;
        return castleMove == move.castleMove && enPassant == move.enPassant && Objects.equals(source, move.source) && Objects.equals(destination, move.destination);
    }

}
