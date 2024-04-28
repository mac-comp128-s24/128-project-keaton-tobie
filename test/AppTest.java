import org.junit.jupiter.api.Test;

public class AppTest {
    @Test
    void displayBoardTest() {
        CheckersApp app = new CheckersApp();
    }

    @Test
    void movePieceTest() {
        GameManager gm = new GameManager();
        gm.getPieceAt(new Tile(3, 3));
    }
}