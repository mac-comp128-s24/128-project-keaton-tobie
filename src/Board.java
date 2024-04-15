public class Board {
    public Board() {

    }
    public static Tile moveTopLeft(Tile t) {
        Tile moved = move(t, new Tile(-1,1));
        if (validate(moved)) {
            return moved;
        }
        return null;
    }

    private static boolean validate(Tile t) {
        return !(t.getCol()>8||t.getRow()<1||t.getRow()>8||t.getCol()<1);
    }

    public static Tile move(Tile t, Tile by) {
        int col = t.getCol() + by.getCol();
        int row = t.getRow() + by.getRow();
        Tile moved = new Tile(col,row);
        return moved;
    }
}
