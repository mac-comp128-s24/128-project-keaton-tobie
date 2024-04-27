import java.awt.Color;
import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Point;
import edu.macalester.graphics.Rectangle;

public class CheckersApp {
    private GameManager gameManager;
    private CanvasWindow canvas;
    private GraphicsGroup piecesGroup;
    private GraphicsGroup movesGroup;
    private Tile selectedTile;
    private Piece selectedPiece;
    private Rectangle selectedRect;
    private static final int SCREEN_SIZE = 800;
    public static final int TILE_SIZE = SCREEN_SIZE/8;

    public CheckersApp() {
        this.gameManager = new GameManager();
        renderInitial();
        setupListeners();
    }
    
    private void handleClick(Point point) {
        int col = (int)(point.getX() / TILE_SIZE);
        int row = (int)(point.getY() / TILE_SIZE);
        selectedTile = new Tile(col, row);
        renderSelectedTile();
        if (gameManager.canMoveTo(selectedTile)) {
            gameManager.moveTo(selectedTile); 
            renderPieces();
        } else {
            selectedPiece = gameManager.getPieceAt(selectedTile);
            renderMoves();
        }
        canvas.draw();
    }

    private void renderPieces() {
        canvas.remove(movesGroup);
        movesGroup = null;
        canvas.remove(piecesGroup);
        piecesGroup = gameManager.getPieceGraphics();
        canvas.add(piecesGroup);
    }

    private void renderMoves() {
        if (selectedPiece==null) {
            if (movesGroup!=null) {
                canvas.remove(movesGroup);
            }
            return;
        }
        canvas.remove(movesGroup);
        movesGroup = gameManager.getMoveGraphics(selectedTile);
        canvas.add(movesGroup);
    }

    private void renderSelectedTile() {
        if (selectedRect==null) {
            selectedRect = new Rectangle(0,0,TILE_SIZE,TILE_SIZE);
            selectedRect.setFilled(false);
            selectedRect.setStrokeColor(new Color(255,82,191));
            canvas.add(selectedRect);
        }
        selectedRect.setCenter(selectedTile.getTileCenter());
    }

    private void setupListeners() {
        canvas.onMouseDown(event -> {
            handleClick(event.getPosition());
        });
    }

    private void renderInitial() {
        canvas = new CanvasWindow("checkers", SCREEN_SIZE, SCREEN_SIZE);
        canvas.setBackground(new Color(179,184,189));
        for (int i = 0; i<32; i++) {
            int col = 2*(i%4)+ (i/4)%2;
            int row = i/4;
            row*=TILE_SIZE;
            col*=TILE_SIZE;
            Rectangle rect = new Rectangle(col,row,TILE_SIZE,TILE_SIZE);
            rect.setFilled(true);
            rect.setFillColor(new Color(39,33,46));
            canvas.add(rect);
        }
        piecesGroup = gameManager.getPieceGraphics();
        canvas.add(piecesGroup);
        canvas.draw();
    }

    public static void main(String[] args) {
        CheckersApp CheckersApp = new CheckersApp();
    }

}



