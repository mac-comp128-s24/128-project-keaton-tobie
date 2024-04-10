import java.util.Scanner;

public class CheckersApp {
    private Board board;
    private GameManager gameManager;
    private Scanner scanner;

    public CheckersApp() {
        this.board = new Board();
        this.gameManager = new GameManager(board);
        this.scanner = new Scanner(System.in);
    }

    public void startGame() {
        System.out.println("Welcome to Checkers!");
        board.initializeBoard(); // Initialize the game board

        while (!gameManager.isGameOver()) {
            board.displayBoard(); // Display the current state of the board

            // Get input from the current player
            Move move = getPlayerMove();

            // Make the move
            MoveResult result = gameManager.makeMove(move);

            // Handle the result of the move
            handleMoveResult(result);
        }

        // Display the final state of the board
        board.displayBoard();

        // Display the winner or a draw
        System.out.println("Game over. " + gameManager.getWinner() + " wins!");
    }

    private Move getPlayerMove() {
        // Implement logic to get move input from the player
        // Return a Move object representing the player's move
    }

    private void handleMoveResult(MoveResult result) {
        // Implement logic to handle the result of the move
        // This can include displaying messages to the user based on the result
    }

    public static void main(String[] args) {
        CheckersApp app = new CheckersApp();
        app.startGame();
    }
}
