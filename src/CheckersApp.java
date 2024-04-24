import java.awt.Color;
import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Rectangle;

public class CheckersApp {
    private Board board;
    private GameManager gameManager;
    private Player currentPlayer;
    private Tile selectedTile;
    private boolean gameStarted;
    private CanvasWindow canvas;
    private final int SCREEN_SIZE = 600;
    private final int TILE_SIZE = SCREEN_SIZE/8;

    public CheckersApp() {
        this.board = new Board();
        this.gameManager = new GameManager(board);
        this.currentPlayer = Player.PLAYER_ONE; // Assume player one starts the game
        this.selectedTile = null; // Initially no tile is selected
        this.gameStarted = false; // Game starts when player selects a mode
        canvas = new CanvasWindow("checkers", SCREEN_SIZE, SCREEN_SIZE);
        canvas.setBackground(Color.white);
        for (int i = 0; i<16; i++) {
            int row = i/4 + 1;
            int col = i%4 + row%2;
            row*=TILE_SIZE;
            col*=TILE_SIZE;
            Rectangle rect = new Rectangle(row,col,TILE_SIZE,TILE_SIZE);
            rect.setFilled(true);
            canvas.add(rect);
        }
        for (int i = 0; i<16; i++) {
            int row = i/4 + 4;
            int col = i%4 + row%2;
            row*=TILE_SIZE;
            col*=TILE_SIZE;
            Rectangle rect = new Rectangle(row,col,TILE_SIZE,TILE_SIZE);
            rect.setFilled(true);
            canvas.add(rect);
        }
        canvas.draw();
        
        // setBackground(Color.WHITE); // Set background color
        // setFocusable(true); // Allow panel to receive keyboard focus
        // addMouseListener(new MouseAdapter() {
        //     @Override
        //     public void mouseClicked(MouseEvent e) {
        //         handleMouseClick(e);
        //     }
        // });
    }

    // @Override
    // protected void paintComponent(Graphics g) {
    //     super.paintComponent(g);
    //     // Draw the game board if game has started
    //     // if (gameStarted) {
    //         board.draw(g);
    //     // } else {
    //         // Draw the menu or mode selection screen
    //         // You can implement this part as needed
    //     // }
    // }

    // private void handleMouseClick(MouseEvent e) {
    //     if (!gameStarted) {
    //         // Handle mode selection
    //         // For example, if the player clicks on a button to select a mode:
    //         gameStarted = true;
    //         // currentPlayer = // Set the current player based on the selected mode (e.g., bot or local player)
    //         board.initializeBoard(); // Set up the game board
    //     } else {
    //         // Translate mouse coordinates to board coordinates
    //         int x = e.getX();
    //         int y = e.getY();
    //         int col = x / Tile.TILE_SIZE;
    //         int row = y / Tile.TILE_SIZE;
    //         Tile clickedTile = new Tile(row, col);
    
    //         // Perform game logic here
    //         // For example:
    //         // if (selectedTile == null) {
    //         //     // Handle selecting a piece
    //         // } else {
    //         //     // Handle moving the piece
    //         // }
    
    //         // Repaint the panel to update the display
    //         repaint();
    //     }
    // }

    

    // private static void createAndShowGui() {
    //     JFrame frame = new JFrame("Checkers Game");
    //     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //     CheckersApp app = new CheckersApp();
    //     frame.add(app);
    //     frame.pack(); // Pack components within the frame
    //     frame.setLocationRelativeTo(null); // Center the frame on the screen
    //     frame.setVisible(true);
    // }
    public static void main(String[] args) {
        CheckersApp CheckersApp = new CheckersApp();
    }

}



