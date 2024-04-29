
public class ChessPiece {
    private PieceType type;
    private boolean moved;

    public ChessPiece(PieceType type, boolean moved) {
        this.type = type;
        this.moved = moved;
    }

    public PieceType getType() {
        return type;
    }

    public boolean getMoved() {
        return moved;
    }
}
