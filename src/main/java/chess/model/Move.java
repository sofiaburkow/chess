package chess.model;

import grid.Location;

import java.util.Objects;

public class Move {

    public final Location source;
    public final Location destination;
    // when a pawn advances on the board, but is not a threat
    private boolean notCaptureMove;

    public Move(Location source, Location destination) {
        this.source = source;
        this.destination = destination;
        notCaptureMove = false;
    }

    /**
     * Whether the move is a capture move or not.
     *
     * @return true if not a capture move, otherwise false.
     */
    public boolean isNotCaptureMove() {
        return notCaptureMove;
    }

    /**
     * Set the boolean value of the field variable notCaptureMove to true or false.
     */
    public void setNotCaptureMove(boolean notCaptureMove) {
        this.notCaptureMove = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Move)) return false;
        Move move = (Move) o;
        return Objects.equals(source, move.source) && Objects.equals(destination, move.destination);
    }

}
