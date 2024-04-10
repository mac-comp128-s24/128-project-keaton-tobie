public class Piece {
    private PieceType type;
    private PlayerColor color;
    private boolean isKing;
    private int row;
    private int col;

    public Piece(PieceType type, PlayerColor color, int row, int col) {
        this.type = type;
        this.color = color;
        this.row = row;
        this.col = col;
        this.isKing = false;
    }

    // Getters and setters for type, color, isKing, row, and col

    public void makeKing() {
        isKing = true;
    }

    public boolean isKing() {
        return isKing;
    }

    // Additional methods as needed for piece functionality
}
