import java.awt.Color;
import java.util.Set;

public class Player {
    private Set<Pawn> pieces;
    private int playerNum;
    private Color color;
    private Board board;

    Player(int playerNum, Color color, Board board) {
        this.playerNum = playerNum;
        this.color = color;
        this.board = board;
    }

    public int getNum() {
        return playerNum;
    }

    public Board getBoard() {
        return board;
    }

    public boolean Equals(Player p){
        return (playerNum==p.getNum());
    }
}
