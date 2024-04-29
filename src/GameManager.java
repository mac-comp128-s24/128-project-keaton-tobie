import java.awt.Color;
import java.util.Set;
import java.util.Map.Entry;

import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsObject;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.Point;
import edu.macalester.graphics.Rectangle;

public class GameManager {
    private Board board;
    private MoveValidator mv = new MoveValidator();
    private Set<Move> legalMoves;

    public GameManager() {
        this.board = new Board();
        getLegalMoves();

    }

    public GraphicsGroup renderPieces() {
        GraphicsGroup g = new GraphicsGroup();
        for (Entry<Tile,ChessPiece> e : board.getWhiteTiles().entrySet()) {
            GraphicsObject go = renderPiece(e.getValue(), true);
            go.setCenter(getTileCenter(e.getKey()));
            g.add(go);
        }
        for (Entry<Tile,ChessPiece> e : board.getBlackTiles().entrySet()) {
            GraphicsObject go = renderPiece(e.getValue(), false);
            go.setCenter(getTileCenter(e.getKey()));
            g.add(go);
        }
        return g;
    }

    public GraphicsObject renderTile(Tile t) {
        if (t == null) {
            return null;
        }
        Rectangle r = new Rectangle(0,0,CheckersApp.TILE_SIZE,CheckersApp.TILE_SIZE);
        r.setFilled(false);
        r.setStrokeColor(new Color(255,82,191));
        r.setCenter(getTileCenter(t));
        return r;
    }

    private void getLegalMoves() {
        legalMoves = mv.getLegalMoves(board);
    }

    public GraphicsGroup getLegalMovesFrom(Tile t) {
        Set<Move> lmf = mv.getLegalMovesFrom(t, board);
        return renderMoves(lmf);
    }

    private GraphicsGroup renderMoves(Set<Move> moves) {
        GraphicsGroup group = new GraphicsGroup();
        for (Move m : moves) {
            // create an new ellipse centered at the tile position
            Ellipse ellipse = new Ellipse(0,0, Tile.TILE_SIZE/3, Tile.TILE_SIZE/3);
            ellipse.setCenter(getTileCenter(m.end));
            // add the ellipse to the group
            group.add(ellipse);
        }
        return group;
    }

    private Point getTileCenter(Tile t) {
        int col = t.getCol();
        int row = t.getRow();
        return new Point((col+.5)*CheckersApp.TILE_SIZE , (row+.5)*CheckersApp.TILE_SIZE);
    }

    private GraphicsObject renderPiece(ChessPiece p, boolean white) {
        GraphicsText t = new GraphicsText();
        String c;
        if (white) {
            c = "w";
        } else {
            c = "b";
        }
        switch (p.getType()) {
            case PAWN: 
                t = new GraphicsText(c + "p");
                break;
            case ROOK: 
                t = new GraphicsText(c + "R");
                break;
            case KNIGHT: 
                t = new GraphicsText(c + "N");
                break;
            case BISHOP: 
                t = new GraphicsText(c + "B");
                break;
            case QUEEN: 
                t = new GraphicsText(c + "Q");
                break;
            case KING: 
                t = new GraphicsText(c + "K");
                break;
            case CHECKER: 
                t = new GraphicsText(c + "aaaaaaa");
                break;
        }
        return t;
    }
    
    public boolean canMakeMove(Move m) {
        if (m==null) {
            System.out.println("move was null");
            return false;
        }
        if (legalMoves.contains(m)) {
            System.out.println("legal moves contains move");
        } else {
            System.out.println(legalMoves.size());
        }
        return legalMoves.contains(m);
    }

    /**
     * Only call this if we know the current piece can move to tile. 
     * Moves the current piece to tile, then updates other pieces moves
     * @param tile Tile to move currently selected piece to 
     */
    public void makeMove(Move move) {
        System.out.println("trying to move");
        board = board.makeMove(move);
        getLegalMoves();
    }

}

