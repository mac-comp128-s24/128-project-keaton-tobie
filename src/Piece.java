import java.util.Set;

import edu.macalester.graphics.GraphicsObject;

public interface Piece {
    public GraphicsObject getGraphics();
    public Set<Tile> getMoves();
    public Set<Tile> getCaptures();
    public void setLegalMoves(Set<Tile> legalMoves);
    public Set<Tile> getLegalMoves();
    public boolean isMove(Tile t); 
    public Player getPlayer();
    public void setPlayer(Player p);
    public boolean isPawn();
}
