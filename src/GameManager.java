import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GameManager {
    private Board board;
    private Player currentPlayer;

    public GameManager(Board board) {
        this.board = board;
        this.currentPlayer = Player.PLAYER_ONE; // Assume player one starts the game
    }

    public static int getTileSize() {
        return Tile.TILE_SIZE;
    }

    public void drawBoard() {
        
    }
    
   

    private boolean isGameOver() {
        // Check if any player has no remaining pieces
        boolean playerOneHasPieces = currentPlayerHasPieces(Player.PLAYER_ONE);
        boolean playerTwoHasPieces = currentPlayerHasPieces(Player.PLAYER_TWO);
    
        if (!playerOneHasPieces || !playerTwoHasPieces) {
            return true;
        }
          // If neither player has won and the current player cannot make any moves, it's a stalemate
        return true;
    }
    
    private boolean currentPlayerHasPieces(Player player) {
        Set<Piece> playerPieces = player.getPieces();
        return !playerPieces.isEmpty();
    }
    
            
    private void switchPlayer() {
        // Switch to the next player's turn
        currentPlayer = (currentPlayer == Player.PLAYER_ONE) ? Player.PLAYER_TWO : Player.PLAYER_ONE;
    }

    // Other methods as needed for game management

    /**
     * Generates all possible moves for a given piece on the board
     * @param piece The piece for which to generate moves
     * @return A set of all possible moves for the piece
     */
    public Set<Tile> getAllLegalMovesForTile(Tile tile) {
        // Get the current position of the piece
        Piece currentpiece = board.getPiece(tile);
        if(currentpiece.getPlayer() ==currentPlayer){
            return currentpiece.getMoves();
        }
        // Use the movesFrom method of the piece to get all legal moves from the current position
        return null;
      
    }
}

