import java.util.Set;

import edu.macalester.graphics.GraphicsObject;

public interface Piece {
    public GraphicsObject getGraphics();
    public Tile getTile();
    public void setTile(Tile t);
    public Set<Tile> getMoves();
    public Set<Tile> move(Tile t); // returns a set of captured tiles, empty if no captures
    public Player getPlayer();
    public void setPlayer(Player p);
}
