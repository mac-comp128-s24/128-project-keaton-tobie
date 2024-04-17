import java.util.HashSet;
import java.util.Set;

public class Tile {
    private int row;
    private int col;

    public Tile(int col, int row) {
        this.col = col;
        this.row = row;
    }

    public boolean equals(Tile t) {
        return col==t.getCol()&&row==t.getRow();
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
     * moves the tile by each of amounts, returning a set of moves
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

    public Set<Tile> movesBetween(Tile t) {
        Set<Tile> between = new HashSet<>();
        int dCol = col-t.getCol();
        int dRow = row-t.getRow();
        if (dCol == 0) {
            for (int i = 1; i < Math.abs(dRow); i++) {
                between.add(t.move(new Tile(0, i*(int)Math.signum(dRow))));
            }
        } else if (dRow == 0) {
            for (int i = 1; i < Math.abs(dCol); i++) {
                between.add(t.move(new Tile(i*(int)Math.signum(dCol),0)));
            }
        } else if (Math.abs(dCol) == Math.abs(dRow)) {
            for (int i = 1; i < Math.abs(dRow); i++) {
                between.add(t.move(new Tile(i*(int)Math.signum(dCol), i*(int)Math.signum(dRow))));
            }
        }
        return between;
    }
}
