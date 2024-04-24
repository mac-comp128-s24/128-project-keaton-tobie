public class Move {
    private static Piece piece; // The piece being moved
    private Tile start; // The starting position of the move
    private Tile end; // The ending position of the move

    // Constructor
    public Move(Piece piece, Tile start, Tile end) {
        this.piece = piece;
        this.start = start;
        this.end = end;
    }

    // Getters and setters
    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        Move.piece = piece;
    }

    public Tile getStart() {
        return start;
    }

    public void setStart(Tile start) {
        this.start = start;
    }

    public Tile getEnd() {
        return end;
    }

    public void setEnd(Tile end) {
        this.end = end;
    }
}

