import java.awt.Color;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.awt.Graphics;

public class Board {
    private final HashMap<Tile, Pawn> tilesToPieces = new HashMap<>();
    private final int tileSize = GameManager.getTileSize();

    public Board() {
    }

    public void initializeBoard() {
        Player zero = new Player(0, Color.RED);
        Player one = new Player(1, Color.BLACK);
        for (int i = 0; i<12; i++) {
            Pawn p = new Pawn(zero);
            zero.addPiece(p);
            int row = i/4 + 1;
            int col = i%4 + row%2;
            Tile t = new Tile(row, col);
            put(t, p);
        }
        for (int i = 0; i<12; i++) {
            Pawn p = new Pawn(one);
            one.addPiece(p);
            int row = i/4 + 6;
            int col = i%4 + row%2;
            Tile t = new Tile(row, col);
            put(t, p);
        }
    }
    
    public void put(Tile t, Pawn p) {
        tilesToPieces.put(t, p);
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

    public void draw(Graphics g) {
        // Draw the board grid
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                // Draw each tile of the board
                if ((row + col) % 2 == 0) {
                    g.setColor(Color.WHITE);
                } else {
                    g.setColor(Color.BLACK);
                }
                g.fillRect(col * tileSize, row * tileSize, tileSize, tileSize);
            }
        }
    
        // Draw pieces on the board
        for (Tile tile : getTilesWithPieces()) {
            int row = tile.getRow();
            int col = tile.getCol();
            if ((row + col) % 2 != 0) {
                Pawn piece = getPiece(tile);
                if (piece != null) {
                    if (piece.getPlayer().equals(Player.PLAYER_ONE)) {
                        g.setColor(Color.RED);
                    } else {
                        g.setColor(Color.BLACK);
                    }
                    // Adjust positioning to center the piece within the tile
                    int x = col * tileSize + tileSize / 4; // Adjusted x coordinate
                    int y = row * tileSize + tileSize / 4; // Adjusted y coordinate
                    g.fillOval(x, y, tileSize / 2, tileSize / 2); // Draw the piece
                }
            }
        }
    }
    
}
