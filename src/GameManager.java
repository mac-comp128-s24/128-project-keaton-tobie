import java.util.HashSet;
import java.util.Set;

import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsObject;

public class GameManager {
    private Board board;
    private Player currentPlayer;
    private Piece currentPiece;
    private Set<Tile> moves = new HashSet<>();

    public GameManager() {
        this.board = new Board();
        this.currentPlayer = Player.PLAYER_ONE;
    }
   
    /**
     * returns the piece at Tile t
     * @param t Tile
     * @return piece at t, null if no piece
     */
    public Piece getPieceAt(Tile t) {
        Piece p = board.getPiece(t);
        if (p!=null&&playerControlsPiece(p)) {
            currentPiece = p;
            moves = p.getMoves();
        } else {
            currentPiece = null;
            moves = null;
        }
        return currentPiece;
    }
    
    private boolean playerControlsPiece(Piece p) {
        return p.getPlayer().equals(currentPlayer);
    }

    private boolean currentPlayerHasPieces(Player player) {
        Set<Piece> playerPieces = player.getPieces();
        return !playerPieces.isEmpty();
    }
    
    public boolean canMoveTo(Tile t) {
        return moves.contains(t);
    }

    public void moveTo(Tile t) {
        board.put(t, currentPiece);
    }
            
    private void switchPlayer() {
        isGameOver();
        currentPlayer = (currentPlayer == Player.PLAYER_ONE) ? Player.PLAYER_TWO : Player.PLAYER_ONE;
    }

    /**
     * Generates all possible moves for a given piece on the board
     * @param piece The piece for which to generate moves 
     * @return A set of all possible moves for the piece
     */
    public GraphicsGroup getMoveGraphics(Tile tile) {
        Piece currentpiece = board.getPiece(tile);
        if (currentpiece.getPlayer() == currentPlayer) {
            moves = currentpiece.getMoves();
            return RenderMoveAtTile(moves);
        }
        return null;
    }

    public GraphicsGroup getPieceGraphics() {
        return RenderPieceAtTile(board.getTilesWithPieces());
    }

    private GraphicsGroup RenderPieceAtTile(Set<Tile> tiles) {
        GraphicsGroup group = new GraphicsGroup();
        for (Tile t : tiles) {
            // Render the object at the tile position
            GraphicsObject go = board.getPiece(t).getGraphics();
            go.setCenter(t.getTileCenter());
            group.add(go);
        }   
        return group;
    }

    private GraphicsGroup RenderMoveAtTile(Set<Tile> tiles) {
        GraphicsGroup group = new GraphicsGroup();
        for( Tile t : tiles) {
            // create an new ellipse centered at the tile position
            Ellipse ellipse = new Ellipse(0,0, Tile.TILE_SIZE/3, Tile.TILE_SIZE/3);
            ellipse.setCenter(t.getTileCenter());
            // add the ellipse to the group
            group.add(ellipse);
        }
        return group;
    }

    private boolean isGameOver() {
        switchPlayer();
        // Check if any player has no remaining pieces
        boolean playerOneHasPieces = currentPlayerHasPieces(Player.PLAYER_ONE);
        boolean playerTwoHasPieces = currentPlayerHasPieces(Player.PLAYER_TWO);
    
        if (!playerOneHasPieces || !playerTwoHasPieces) {
            return true;
        }
          // If neither player has won and the current player cannot make any moves, it's a stalemate
        return true;
    }
}

