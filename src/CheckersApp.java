import java.awt.Color;
import java.awt.geom.Point2D;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Point;
import edu.macalester.graphics.Rectangle;

public class CheckersApp {
    private Piece piece;
    private Board board;
    private GameManager gameManager;
    private Player currentPlayer;
    private Tile selectedTile;
    private boolean gameStarted;
    private CanvasWindow canvas;
    private static final int SCREEN_SIZE = 800;
    public static final int TILE_SIZE = SCREEN_SIZE/8;

    public CheckersApp() {
        this.board = new Board();
        this.gameManager = new GameManager(board);
        this.currentPlayer = Player.PLAYER_ONE; // Assume player one starts the game
        this.selectedTile = null; // Initially no tile is selected
        this.gameStarted = false; // Game starts when player selects a mode
        canvas = new CanvasWindow("checkers", SCREEN_SIZE, SCREEN_SIZE);
        canvas.setBackground(new Color(.9f,.8f,.85f));
        canvas.onClick(event -> {
            handleClick(event.getPosition());
        });
        for (int i = 0; i<32; i++) {
            int col = 2*(i%4)+ (i/4)%2;
            int row = i/4;
            row*=TILE_SIZE;
            col*=TILE_SIZE;
            Rectangle rect = new Rectangle(col,row,TILE_SIZE,TILE_SIZE);
            rect.setFilled(true);
            rect.setFillColor(new Color(.3f,.3f,.3f));
            canvas.add(rect);
        }
        canvas.add(gameManager.RenderPieceatTile(board.getTilesWithPieces()));
        canvas.draw();
        
    }
    
    // Method to handle click events
    private void handleClick(Point point) {
        // Calculate the row and column from clickPosition
        int col = (int)(point.getX() / TILE_SIZE);
        int row = (int)(point.getY() / TILE_SIZE);
        
        // Get the Tile at the clicked position`
        Tile clickedTile = piece.getTile();
        
        // Check if the tile has a piece, and it belongs to the current player
        if (clickedTile.hasPiece() && clickedTile.getPiece().getPlayer() == currentPlayer) {
            selectedTile = clickedTile;
            // Highlight the selected tile
            clickedTile.getRectangle().setFillColor(new Color(1,0,0,0.5f));
        } else if (selectedTile != null) {
            // Remove highlight from previously selected tile
            selectedTile.getRectangle().setFillColor(null);
            
            // Check if the move is valid
            Set<Tile> validMoves = gameManager.getAllLegalMovesForTile(selectedTile);
            if (validMoves.contains(clickedTile)) {
                // Move the piece to the new tile
                // This requires a method to actually move the piece in your board or gameManager class
                board.movePiece(selectedTile, clickedTile);
                
                // Switch player
                gameManager.switchPlayer();
                currentPlayer = gameManager.getCurrentPlayer();
                
                // Update the game state and redraw the board
                selectedTile = null;
                canvas.removeAll();
                drawBoardAndPieces();
                canvas.draw();
            }
        }
        
        // Redraw the board
        canvas.draw();
    }

    
    // Method to draw the board and pieces
    private void drawBoardAndPieces() {
        // Draw the checkers board and pieces similar to what's in your constructor
        // ...
    }



    public static void main(String[] args) {
        CheckersApp CheckersApp = new CheckersApp();
    }

}



