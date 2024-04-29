import java.awt.Color;

public class Player {
    public static final Player W = new Player(true, new Color(255,215,0)); // Example initialization
    public static final Player B = new Player(false, new Color(30,211,236)); // Example initialization

    private boolean white;
    private Color color; //picking colors

    public Player(boolean white, Color color) {
        this.white = white;
        this.color = color;
    }

    public boolean isWhite() {
        return white;
    }

    public Color getColor() {
        return color;
    }

    public boolean equals(Player p) {
        return (white == p.isWhite());
    }
}
