import java.util.Set;

public interface Movable {
    public Set<Tile> validMoves();
    public Set<Tile> validCaptures();
}
