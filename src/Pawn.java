import java.util.HashSet;
import java.util.Set;
import java.awt.Graphics;
public class Pawn extends Piece {
    private Player player; // Add player field

    public Pawn(Player player) {
        super(player); // Call the constructor of the superclass (Piece) with the player argument
        this.player = player; // Initialize the player field
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

//     @Override
// protected Set<Tile> validCaptures(Tile t) {
//     Set<Tile> potentialCaptures = t.moves(captures);
//     Set<Tile> validatedCaptures = super.getPlayer().getBoard().validate(potentialCaptures);
//     return validatedCaptures; // Return validated captures
// }
public void draw(Graphics g, Tile tile, int tileSize) {
    // Example: Draw a simple circle representing the pawn at the tile's position
    g.setColor(player.getColor());
    g.fillOval(tile.getCol() * tileSize, tile.getRow() * tileSize, tileSize, tileSize);
}

}

