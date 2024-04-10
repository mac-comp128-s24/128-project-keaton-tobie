public class Tile {
    private Piece piece;
    private int row;
    private int col;

    public Tile(int row, int col) {
        this.row = row;
        this.col = col;
        this.piece = null; // Initialize with no piece on the tile
    }

    public boolean isOccupied() {
        return piece != null;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
