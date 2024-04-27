import java.util.Set;

import edu.macalester.graphics.GraphicsObject;

public interface Piece {
    public GraphicsObject getGraphics();
    public Tile getTile();
    public void setTile(Tile t);
    public Set<Tile> getMoves();
    public Player getPlayer();
    public void setPlayer(Player p);
}
