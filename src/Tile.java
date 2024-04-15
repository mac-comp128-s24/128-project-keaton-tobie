import java.util.HashSet;
import java.util.Set;

public class Tile {
    private int row;
    private int col;
    private Board board;

    public Tile(int col, int row) {
        this.col = col;
        this.row = row;
        board = new Board();
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    /**
     * Moves the Tile by Amount, and returning a new tile
     * @param by Amount to move by
     * @return tile
     */
    public Tile move(Tile amount) {
        int movedCol = col + amount.getCol();
        int movedRow = row + amount.getRow();
        Tile moved = new Tile(movedCol,movedRow);
        return moved;
    }

    
    /**
     * moves the by each of amounts, returning a set of moves
     * @param amounts A set of tiles to move by
     * @return A set of moves from tile
     */
    public Set<Tile> moves(Set<Tile> amounts) {
        Set<Tile> moves = new HashSet<>();
        for (Tile by : amounts) {
            Tile moved = move(by);
            moves.add(moved);
        }
        return moves;
    }
}
