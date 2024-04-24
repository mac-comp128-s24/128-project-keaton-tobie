import java.util.HashSet;
import java.util.HashMap;
import java.util.Set;
import java.util.Map;

import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.GraphicsObject;

import java.awt.Graphics;
public class Pawn implements Piece {
    private Player player; // Add player field
    private Tile tile;
    private Set<Tile> moves;
    private Set<Tile> captures;
    private Set<Tile> promotions;
    private Map<Tile, Set<Tile>> determinedMoves;
    private Ellipse ellipse;

    public Pawn(Player player) {
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

    Set<Tile> validMoves(Tile t) {
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
        determineMoves();
        return determinedMoves.keySet();
    }

    private Map<Tile, Set<Tile>> determineMoves() {
        // Map<Tile, Set<Tile>> legalMoves = new HashMap<>();
        // Set<Tile> validMoves = validMoves(t);
        // Set<Tile> validCaptures = validCaptures(t);
        // for (Tile move : validMoves) {
        //     legalMoves.put(move, MoveType.MOVE);
        //     if (promotions.contains(move)) {
        //         legalMoves.put(move, MoveType.PROMOTION);
        //     }
        // }
        // for (Tile capture : validCaptures) {
        //     legalMoves.put(capture, MoveType.CAPTURE);
        //     if (promotions.contains(capture)) {
        //         legalMoves.put(capture, MoveType.PROMOTION);
        //     }
        // }
        // determinedMoves = legalMoves;
        return null;
    }

    public GraphicsObject getGraphics() {
        Ellipse e = new Ellipse(0,0,Tile.TILE_SIZE, Tile.TILE_SIZE);
        e.setCenter(tile.getTileCenter());
        e.setFillColor(player.getColor());
        e.setStroked(false);
        ellipse = e;
        return e;
    }

    public Tile getTile() {
        return tile;
    }


    public void setTile(Tile t) {
        tile = t;
        ellipse.setCenter(t.getTileCenter());
    }


    public Set<Tile> move(Tile t) {
        if (!determinedMoves.containsKey(t)) {
            throw new IllegalArgumentException();
        }
        setTile(t);
        return determinedMoves.get(t);
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player p) {
        player = p;
        ellipse.setFillColor(p.getColor());
    }



}

