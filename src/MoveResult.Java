public class MoveResult {
    private boolean validMove;
    private boolean gameOver;
    private PlayerColor winner;

    public MoveResult(boolean validMove, boolean gameOver, PlayerColor winner) {
        this.validMove = validMove;
        this.gameOver = gameOver;
        this.winner = winner;
    }

    public boolean isValidMove() {
        return validMove;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public PlayerColor getWinner() {
        return winner;
    }
}
