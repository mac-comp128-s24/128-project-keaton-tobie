import java.awt.Color;
import java.util.Set;

public class Player {
    public static final Player PLAYER_TWO = null;
    public static final Player PLAYER_ONE = null;
    private Set<Piece> pieces;
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

    public Color getColor() {
        return color;
    }

    public Set<Piece> getPieces() {
        return pieces;
    }

    public void addPiece(Piece p) {
        p.setPlayer(this);
        pieces.add(p);
    }

    public void removePiece(Piece p) {
        pieces.remove(p);
    }

    public boolean Equals(Player p) {
        return (playerNum==p.getNum());
    }
}
