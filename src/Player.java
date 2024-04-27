import java.awt.Color;
import java.util.HashSet;
import java.util.Set;

public class Player {
    public static final Player PLAYER_ONE = new Player(0, new Color(255,215,0)); // Example initialization
    public static final Player PLAYER_TWO = new Player(1, new Color(30,211,236)); // Example initialization

    private Set<Piece> pieces;
    private int playerNum;
    private Tile direction;
    private Color color;
    private Board board; // Reference to the board

    public Player(int playerNum, Color color) {
        this.playerNum = playerNum;
        if (playerNum==0) {
            direction = new Tile(0,1);
        } else {
            direction = new Tile(0,-1);
        }
        this.color = color;
        this.pieces = new HashSet<>();
    }
    

    // Setter method for Board
    public void setBoard(Board board) {
        this.board = board;
    }

    public Tile direction(Tile t) {
        return t.move(direction);
    }

    public Tile direction(Tile t, int i) {
        for (int j = 0; j < i; j++) {
            t = t.move(direction);
        }
        return t;
    }

    public int getNum() {
        return playerNum;
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

    // You can now use the board reference here as needed
    public Board getBoard() {
        return board;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Player player = (Player) obj;
        return playerNum == player.playerNum;
    }

    @Override
    public int hashCode() {
        return playerNum;
    }
}
