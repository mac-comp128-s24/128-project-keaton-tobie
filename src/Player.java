import java.awt.Color;
import java.util.Set;

public class Player {
    Set<Piece> pieces;
    int playerNum;
    Color color;
    Player(int playerNum) {
        this.playerNum = playerNum;
        if (playerNum == 0) {
            color = color.WHITE;
        }
        if (playerNum ==1) {
            color = color.BLACK;
        }
    }
}
