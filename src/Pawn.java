import java.util.HashSet;
import java.util.Set;

public class Pawn extends Piece {
    
    public Pawn(Player player) {
        super(player); // Call the constructor of the superclass (Piece) with the player argument
        int playerNum = player.getNum();
        moves = new HashSet<>();
        captures = new HashSet<>();
        if (playerNum == 0) {
            moves.add(new Tile(1, -1));
            moves.add(new Tile(1, 1));
            captures.add(new Tile(2, -2));
            captures.add(new Tile(2, 2));
        } else if (playerNum == 1) {
            moves.add(new Tile(-1, -1));
            moves.add(new Tile(-1, 1));
            captures.add(new Tile(-2, -2));
            captures.add(new Tile(-2, 2));
        }
    }
    
    @Override
    protected Set<Tile> validCaptures(Tile t) {
        Set<Tile> potentialCaptures = t.moves(captures);
        Set<Tile> validatedCaptures = player.getBoard().validate(potentialCaptures);
        return validatedCaptures; // Return validated captures
    }
}
