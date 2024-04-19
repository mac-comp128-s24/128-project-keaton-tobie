import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Tile {
    private int row;
    private int col;
    public static final int TILE_SIZE = 50; // Define the size of each tile (in pixels), adjust as needed

    public Tile(int col, int row) {
        this.col = col;
        this.row = row;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    /**
     * Moves the Tile by Amount, and returning a new tile
     * @param amount Amount to move by
     * @return Moved tile
     */
    public Tile move(Tile amount) {
        int movedCol = col + amount.getCol();
        int movedRow = row + amount.getRow();
        return new Tile(movedCol, movedRow);
    }

    /**
     * Moves the tile by each of amounts, returning a set of moves
     * @param amounts A set of tiles to move by
     * @return A set of moved tiles
     */
    public Set<Tile> moves(Set<Tile> amounts) {
        Set<Tile> moves = new HashSet<>();
        for (Tile by : amounts) {
            Tile moved = move(by);
            moves.add(moved);
        }
        return moves;
    }

    /**
     * Gets the tiles between this tile and another tile
     * @param t The other tile
     * @return A set of tiles between this tile and the other tile
     */
    public Set<Tile> movesBetween(Tile t) {
        Set<Tile> between = new HashSet<>();
        int dCol = col - t.getCol();
        int dRow = row - t.getRow();
        if (dCol == 0) {
            for (int i = 1; i < Math.abs(dRow); i++) {
                between.add(new Tile(col, t.getRow() + i * Integer.signum(dRow)));
            }
        } else if (dRow == 0) {
            for (int i = 1; i < Math.abs(dCol); i++) {
                between.add(new Tile(t.getCol() + i * Integer.signum(dCol), row));
            }
        } else if (Math.abs(dCol) == Math.abs(dRow)) {
            for (int i = 1; i < Math.abs(dRow); i++) {
                between.add(new Tile(t.getCol() + i * Integer.signum(dCol), t.getRow() + i * Integer.signum(dRow)));
            }
        }
        return between;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Tile tile = (Tile) obj;
        return row == tile.row && col == tile.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }
}

