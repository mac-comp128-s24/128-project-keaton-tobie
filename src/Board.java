import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Board {
    private Player playerOne = Player.PLAYER_ONE;
    private Player playerTwo = Player.PLAYER_TWO;
    private HashMap<Tile, Piece> tilesToPieces = new HashMap<>();
    // private HashMap<Tile, Piece> tilesListeningPieces = new HashMap<>();

    public Board() {
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i<12; i++) {
            Pawn p = new Pawn(playerOne);
            playerOne.addPiece(p);
            int col = (i % 4)*2 + (i/4 + 1)%2;
            int row = i/4;
            Tile t = new Tile(col, row);
            put(t, p);
        }
        for (int i = 0; i<12; i++) {
            Pawn p = new Pawn(playerTwo);
            playerTwo.addPiece(p);
            int col = (i % 4)*2 + (i/4)%2;
            int row = i/4+5;
            Tile t = new Tile(col, row);
            put(t, p);
        }
    }
    
    public void put(Tile t, Piece p) {
        tilesToPieces.put(t, p);
    }

    public Piece getPiece(Tile t) {
        return tilesToPieces.get(t);
    }

    public Set<Tile> getTilesWithPieces() {
        return tilesToPieces.keySet();
    }

    public void remove(Tile t) {
        tilesToPieces.remove(t);
    }

    public static boolean validate(Tile t) {
        return !(t.getCol()>8||t.getRow()<1||t.getRow()>8||t.getCol()<1);
    }

    /**
     * returns the tiles from the set that are on the board
     * @param tiles a set of tiles
     * @return a set of tiles that are on the board
     */
    public Set<Tile> validate(Set<Tile> tiles) {
        Set<Tile> validTiles = new HashSet<>();
        for (Tile t : tiles) {
            if (validate(t)) {
                validTiles.add(t);
            }
        }
        return validTiles;
    }

    public boolean opposed(Tile from, Tile to) {
        Piece capturing = tilesToPieces.get(from);
        Piece captured = tilesToPieces.get(to);
        // Check if both capturing and captured are not null, and they belong to different players
        return (capturing != null && captured != null && !capturing.getPlayer().equals(captured.getPlayer()));
    }
    

    public boolean occupied(Tile t) {
        return (tilesToPieces.get(t)!=null);
    }

    public Set<Tile> unobstructed(Tile t, Set<Tile> moves) {
        Set<Tile> unobstructedMoves = new HashSet<>();
        for (Tile move : moves) {
            Set<Tile> potentialObstruction = t.movesBetween(move);
            if (unobstructed(potentialObstruction)) {
                unobstructedMoves.add(move);
            }
        }
        return unobstructedMoves;
    }

    public Set<Tile> obstructed(Tile t, Set<Tile> moves) {
        Set<Tile> obstructedMoves = new HashSet<>();
        for (Tile move : moves) {
            Set<Tile> potentialObstruction = t.movesBetween(move);
            if (potentialObstruction.size()==1) {
                for (Tile po : potentialObstruction) {
                    if (opposed(t, po)) {
                        obstructedMoves.add(po);
                    }
                }
            }
        }
        return obstructedMoves;
    }

    public boolean unobstructed(Set<Tile> moves) {
        for (Tile m : moves) {
            if (occupied(m)) {
                return false;
            }
        }
        return true;
    }
    
}
