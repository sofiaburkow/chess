package chess.model;

import grid.Location;

import java.util.Objects;

public class Move {

    public final Location source;
    public final Location destination;

    public Move(Location source, Location destination) {
        this.source = source;
        this.destination = destination;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Move)) return false;
        Move move = (Move) o;
        return Objects.equals(source, move.source) && Objects.equals(destination, move.destination);
    }

}
