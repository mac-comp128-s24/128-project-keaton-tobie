import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class Piece {
    protected Player player;
    protected Tile position; // Add a field to represent the current position

    protected Set<Tile> moves;
    protected Set<Tile> captures;
    protected Set<Tile> promotions;

    // Constructor that accepts a Player argument
    public Piece(Player player) {
        this.player = player;
    }

    public void setPlayer(Player p) {
        player = p;
    }

    // Method to set the position of the piece
    public void setPosition(Tile position) {
        this.position = position;
    }

    // Method to get the position of the piece
    public Tile getPosition() {
        return position;
    }

    protected Set<Tile> validMoves(Tile t) {
        Set<Tile> potentialMoves = t.moves(moves);
        Set<Tile> validatedMoves = player.getBoard().validate(potentialMoves);
        Set<Tile> unobstructedMoves = player.getBoard().pruneObstructed(t, validatedMoves);
        return unobstructedMoves;
    }

    protected Set<Tile> validCaptures(Tile t) {
        Set<Tile> potentialCaptures = t.moves(captures);
        Set<Tile> validatedCaptures = player.getBoard().validate(potentialCaptures);
        return validatedCaptures;
    }

    public Map<Tile, MoveType> movesFrom(Tile t) {
        Map<Tile, MoveType> legalMoves = new HashMap<>();
        Set<Tile> validMoves = validMoves(t);
        Set<Tile> validCaptures = validCaptures(t);
        for (Tile move : validMoves) {
            legalMoves.put(move, MoveType.MOVE);
            if (promotions.contains(move)) {
                legalMoves.put(move, MoveType.PROMOTION);
            }
        }
        for (Tile capture : validCaptures) {
            legalMoves.put(capture, MoveType.CAPTURE);
            if (promotions.contains(capture)) {
                legalMoves.put(capture, MoveType.PROMOTION);
            }
        }
        return legalMoves;
    }

    public Player getPlayer() {
        return this.player;
    }
}
