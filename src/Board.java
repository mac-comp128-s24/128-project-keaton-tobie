import java.awt.Color;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


public class Board {
    private HashMap<Tile, Pawn> tilesToPieces;
    /**
    * a representation of the current board, with static methods to get moves
    */
    public Board() {
        tilesToPieces = new HashMap<>();
    }

    public void initializeBoard() {
        Player zero = new Player(0, Color.white, this);
        Player one = new Player(0, Color.white, this);
        for (int i = 0; i<12; i++) {
            Pawn p = new Pawn();
            zero.addPiece(p);
            int row = i/4 + 1;
            int col = i%4 + row%2;
            Tile t = new Tile(row, col);
            put(t, p);
        }
        for (int i = 0; i<12; i++) {
            Pawn p = new Pawn();
            one.addPiece(p);
            int row = i/4 + 6;
            int col = i%4 + row%2;
            Tile t = new Tile(row, col);
            put(t, p);
        }
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
     * gets the piece at tile (null if there is no piece)
     * @param t Tile to get piece from
     * @return piece, or null
     */
    public Piece getPiece(Tile t) {
        return tilesToPieces.get(t);
    }

    /**
     * gets the set of tiles that have pieces
     * @return set of tiles with non null pieces
     */
    public Set<Tile> getTilesWithPieces() {
        return tilesToPieces.keySet();
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

    public boolean occupied(Tile t) {
        return (tilesToPieces.get(t)!=null);
    }

    public Set<Tile> pruneObstructed(Tile t, Set<Tile> moves) {
        Set<Tile> unobstructedMoves = new HashSet<>();
        for (Tile move : moves) {
            Set<Tile> potentialObstruction = t.movesBetween(move);
            if (unObstructed(potentialObstruction)) {
                unobstructedMoves.add(move);
            }
        }
        return unobstructedMoves;
    }
    public boolean unObstructed(Set<Tile> moves) {
        for (Tile m : moves) {
            if (occupied(m)) {
                return false;
            }
        }
        return true;
    }
}
