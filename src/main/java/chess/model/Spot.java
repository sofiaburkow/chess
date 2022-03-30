package chess.model;

/**
 * A class meant to represent a cell on the chess board, and which piece occupies it.
 */
public class Spot {

    private String piece; // Piece piece
    private int row;
    private int column;

    public Spot(String piece, int row, int column) {
        this.piece = piece;
        this.row = row;
        this.column = column;
    }


    public String getPiece() {
        return piece;
    }


    public void setPiece(String newPiece) {
        // limit
        this.piece = newPiece;
    }


    public int getRow() {
        return row;
    }


    public void setRow(int newRow) {
        if (!isOnBoard(newRow)) {
            throw new IndexOutOfBoundsException("Row is out of bounds.");

        }
        row = newRow;
    }


    public int getColumn() {
        return column;
    }


    public void setColumn(int newColumn) {
        if (!isOnBoard(newColumn)) {
            throw new IndexOutOfBoundsException("Column is out of bounds.");

        }
        column = newColumn;
    }

    /**
     * Help method to evaluate whether a row number or column number is within bounds.
     *
     * @param index which is either a row number or a column number.
     * @return true if the index is within bounds, otherwise falls.
     */
    private boolean isOnBoard(int index) {
        if (index >= 0 && index < 8) {
            return true;
        }
        return false;
    }



}
