import java.awt.Color;
import java.util.HashSet;
import java.util.Set;

public class Player {
    public static final Player PLAYER_ONE = new Player(0, Color.RED); // Example initialization
    public static final Player PLAYER_TWO = new Player(1, Color.BLACK); // Example initialization

    private Set<Piece> pieces;
    private int playerNum;
    private Color color;
    private Board board; // Reference to the board

    public Player(int playerNum, Color color) {
        this.playerNum = playerNum;
        this.color = color;
        this.pieces = new HashSet<>();
    }

    // Constructor with Board parameter
    public Player(int playerNum, Color color, Board board) {
        this.playerNum = playerNum;
        this.color = color;
        this.pieces = new HashSet<>();
        this.board = board;
    }

    // Setter method for Board
    public void setBoard(Board board) {
        this.board = board;
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
