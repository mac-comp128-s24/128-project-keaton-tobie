import java.util.HashSet;
import java.util.Set;

import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.GraphicsObject;

public class Pawn implements Piece {
    private Player player;
    private Set<Tile> moves = new HashSet<>();
    private Set<Tile> captures = new HashSet<>();
    // private Set<Tile> promotions;
    private Set<Tile> legalMoves;
    private Ellipse ellipse;

    public Pawn(Player player) {
        ellipse = new Ellipse(0,0,.9*Tile.TILE_SIZE, .9*Tile.TILE_SIZE);
        ellipse.setFillColor(player.getColor());
        ellipse.setStroked(false);
        this.player = player;
        moves.add(player.direction(new Tile(1, 0)));
        moves.add(player.direction(new Tile(-1, 0)));
        captures.add(player.direction(new Tile(2, 0),2));
        captures.add(player.direction(new Tile(-2, 0),2));
    }

    public void setLegalMoves(Set<Tile> legalMoves) {
        this.legalMoves=legalMoves;
    }

    public Set<Tile> getLegalMoves() {
        return legalMoves;
    }

    public boolean isMove(Tile t) {
        return legalMoves.contains(t);
    }

    public boolean isPawn() {
        return true;
    }

    public Set<Tile> getMoves() {
        return moves;
    }

    public Set<Tile> getCaptures() {
        return captures;
    }

    // public Set<Tile> getListeningTiles() {
    //     for tile : 
    //     return null;
    // }

    public GraphicsObject getGraphics() {
        return ellipse;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player p) {
        player = p;
        ellipse.setFillColor(p.getColor());
    }
}

