import java.util.HashSet;
import java.util.Set;

public class Pawn extends Piece {
    
    public Pawn() {
        super();
        int playerNum = getPlayer().getNum();
        moves = new HashSet<>();
        captures = new HashSet<>();
        if (playerNum==0) {
            moves.add(new Tile(1, -1));
            moves.add(new Tile(1,1));
            captures.add(new Tile(2, -2));
            captures.add(new Tile(2,2));
        }
        else if (playerNum==1) {
            moves.add(new Tile(-1, -1));
            moves.add(new Tile(-1,1));
            captures.add(new Tile(-2, -2));
            captures.add(new Tile(-2,2));
        }
    }
    @Override
    protected Set<Tile> validCaptures(Tile t) {
        Set<Tile> potentialCaptures = t.moves(captures);
        Set<Tile> validatedCaptures = player.getBoard().validate(potentialCaptures);
        return null;
    }
}

