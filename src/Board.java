import java.awt.Color;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import edu.macalester.graphics.GraphicsGroup;

import java.awt.Graphics;

public class Board {
    private final HashMap<Tile, Pawn> tilesToPieces = new HashMap<>();

    public Board() {
        initializeBoard();
    }

    private void initializeBoard() {
        Player zero = new Player(0, Color.RED);
        Player one = new Player(1, Color.BLACK);
        for (int i = 0; i<12; i++) {
            Pawn p = new Pawn(zero);
            zero.addPiece(p);
            int row = (i % 4)*2;
            int col = i/4;
            Tile t = new Tile(col, row);
            put(t, p);
        }
        // for (int i = 0; i<12; i++) {
        //     Pawn p = new Pawn(one);
        //     one.addPiece(p);
        //     int col = 2*(i%4)+ (i/4)%2;
        //     int row = i/4+6;
        //     Tile t = new Tile(row, col);
        //     put(t, p);
        // }
    }
    
    public void put(Tile t, Pawn p) {
        tilesToPieces.put(t, p);
        p.setTile(t);
    }

    public Pawn getPiece(Tile t) {
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
        Pawn capturing = tilesToPieces.get(from);
        Pawn captured = tilesToPieces.get(to);
        // Check if both capturing and captured are not null, and they belong to different players
        return (capturing != null && captured != null && !capturing.getPlayer().equals(captured.getPlayer()));
    }
    

    public boolean occupied(Tile t) {
        return (tilesToPieces.get(t)!=null);
    }

    public Set<Tile> pruneObstructed(Tile t, Set<Tile> moves) {
        Set<Tile> unobstructedMoves = new HashSet<>();
        for (Tile move : moves) {
            Set<Tile> potentialObstruction = t.movesBetween(move);
            if (unobstructed(potentialObstruction)) {
                unobstructedMoves.add(move);
            }
        }
        return unobstructedMoves;
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
