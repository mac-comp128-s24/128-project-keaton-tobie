import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class Board {
    private HashMap<Tile, Pawn> tilesToPieces;
    /**
    * a representation of the current board, with static methods to get moves
    */
    public Board() {
        tilesToPieces = new HashMap<>();
    }

    /**
     * puts a Piece p at the Tile t, overwriting the prior piece
     * @param t Tile to put p at
     * @param p Piece to put at t
     */
    public void put(Tile t, Pawn p) {
        tilesToPieces.put(t, p);
    }

    /**
     * removes the Tile at t
     * @param t Tile to remove the piece from
     */
    public void remove(Tile t) {
        tilesToPieces.remove(t);
    }

    /**
     * determines if a tile is on the board
     * @param t Tile to validate
     * @return true if the tile is on the board
     */
    public static boolean validate(Tile t) {
        return !(t.getCol()>8||t.getRow()<1||t.getRow()>8||t.getCol()<1);
    }

    public Set<Tile> validate(Set<Tile> tiles) {
        Set<Tile> validTiles = new HashSet<>();
        for (Tile t : tiles) {
            if (validate(t)) {
                validTiles.add(t);
            }
        }
        return tiles;
    }

    /**
     * determines if from and to have pieces that belong to opposing players
     * @param from the Tile to move from
     * @param to the Tile to move to
     * @return true if both pieces exist and the players are different
     */
    public boolean opposed(Tile from, Tile to) {
        Pawn capturing = tilesToPieces.get(from);
        Pawn captured = tilesToPieces.get(to);
        return (capturing!=null&&captured!=null&&!capturing.getPlayer().equals(captured.getPlayer()));
    }
}
