import java.util.HashSet;
import java.util.Set;

import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.GraphicsObject;

public class Pawn implements Piece {
    private Player player; // Add player field
    private Tile tile;
    private Set<Tile> moves = new HashSet<>();
    private Set<Tile> captures = new HashSet<>();
    // private Set<Tile> promotions;
    private Set<Tile> determinedMoves;
    private Ellipse ellipse;

    public Pawn(Player player) {
        ellipse = new Ellipse(0,0,Tile.TILE_SIZE, Tile.TILE_SIZE);
        ellipse.setFillColor(player.getColor());
        this.player = player;
        moves.add(player.direction(new Tile(1, 0)));
        moves.add(player.direction(new Tile(-1, 0)));
        captures.add(player.direction(new Tile(2, 0),2));
        captures.add(player.direction(new Tile(-2, 0),2));
    }

    private Set<Tile> validMoves(Tile t) {
        Set<Tile> potentialMoves = t.moves(moves);
        Set<Tile> validatedMoves = player.getBoard().validate(potentialMoves);
        Set<Tile> unobstructedMoves = player.getBoard().pruneObstructed(t, validatedMoves);
        return unobstructedMoves;
    }

    private Set<Tile> validCaptures(Tile t) {
        Set<Tile> potentialCaptures = t.moves(captures);
        Set<Tile> validatedCaptures = player.getBoard().validate(potentialCaptures);
        return validatedCaptures;
    }

    public Set<Tile> getMoves() {
        return determinedMoves;
    }

    // public Set<Tile> getListeningTiles() {
    //     for tile : 
    //     return null;
    // }

    public void determineMoves() {
        determinedMoves = validMoves(tile);
        Set<Tile> determinedCaptures = validCaptures(tile);
        for (Tile capture : determinedCaptures) {
            determinedMoves.add(capture);
        }
    }

    public GraphicsObject getGraphics() {
        ellipse.setCenter(tile.getTileCenter());
        ellipse.setFillColor(player.getColor());
        ellipse.setStroked(false);
        return ellipse;
    }

    public Tile getTile() {
        return tile;
    }


    public void setTile(Tile t) {
        tile = t;
        ellipse.setCenter(t.getTileCenter());
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player p) {
        player = p;
        ellipse.setFillColor(p.getColor());
    }
}

