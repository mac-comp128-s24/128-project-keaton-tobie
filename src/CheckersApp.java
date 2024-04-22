import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CheckersApp extends JPanel {
    private Board board;
    private GameManager gameManager;
    private Player currentPlayer;
    private Tile selectedTile;
    private boolean gameStarted;

    public CheckersApp() {
        this.board = new Board();
        this.gameManager = new GameManager(board);
        this.currentPlayer = Player.PLAYER_ONE; // Assume player one starts the game
        this.selectedTile = null; // Initially no tile is selected
        this.gameStarted = false; // Game starts when player selects a mode
        setPreferredSize(new Dimension(600, 600)); // Set preferred size for the panel
        setBackground(Color.WHITE); // Set background color
        setFocusable(true); // Allow panel to receive keyboard focus
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleMouseClick(e);
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the game board if game has started
        // if (gameStarted) {
            board.draw(g);
        // } else {
            // Draw the menu or mode selection screen
            // You can implement this part as needed
        // }
    }

    private void handleMouseClick(MouseEvent e) {
        if (!gameStarted) {
            // Handle mode selection
            // For example, if the player clicks on a button to select a mode:
            gameStarted = true;
            // currentPlayer = // Set the current player based on the selected mode (e.g., bot or local player)
            board.initializeBoard(); // Set up the game board
        } else {
            // Translate mouse coordinates to board coordinates
            int x = e.getX();
            int y = e.getY();
            int col = x / Tile.TILE_SIZE;
            int row = y / Tile.TILE_SIZE;
            Tile clickedTile = new Tile(row, col);
    
            // Perform game logic here
            // For example:
            // if (selectedTile == null) {
            //     // Handle selecting a piece
            // } else {
            //     // Handle moving the piece
            // }
    
            // Repaint the panel to update the display
            repaint();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGui());
    }

    private static void createAndShowGui() {
        JFrame frame = new JFrame("Checkers Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        CheckersApp app = new CheckersApp();
        frame.add(app);
        frame.pack(); // Pack components within the frame
        frame.setLocationRelativeTo(null); // Center the frame on the screen
        frame.setVisible(true);
    }
}



