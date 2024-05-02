import java.awt.Color;
import java.util.Set;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsObject;
import edu.macalester.graphics.Point;
import edu.macalester.graphics.Rectangle;
import edu.macalester.graphics.events.Key;
import edu.macalester.graphics.events.ModifierKey;

public class CheckersApp {
    private GameManager gameManager;
    private CanvasWindow canvas;
    private GraphicsGroup piecesGroup;
    private GraphicsGroup movesGroup;
    private GraphicsObject selectedTileGraphic;
    private Tile selectedTile;
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
        Tile newTile = new Tile(col, row);
        Move selectedMove = null;
        if (selectedTile!=null) {
            System.out.println("trying a move");
            selectedMove = new Move(selectedTile, newTile);
        }
        selectedTile = newTile;
        renderTile();
        if (gameManager.canMakeMove(selectedMove)) {
            gameManager.makeMove(selectedMove);
            selectedTile = null;
            unrenderTile();
            renderPieces();
        } else {
            renderMoves();
        }
        canvas.draw();
    }

    private void handleKeyPress(Key key, Set<ModifierKey> modifiers) {
        if (key.equals(Key.Z) && modifiers.contains(ModifierKey.CONTROL)) {
            if (modifiers.contains(ModifierKey.SHIFT)) {
                gameManager.redoMove();
            } else {
                gameManager.undoMove();
            }
            renderPieces();
            canvas.draw();
        }
    }

    private void renderTile() {
        unrenderTile();
        selectedTileGraphic = gameManager.renderTile(selectedTile);
        canvas.add(selectedTileGraphic);
    }

    private void unrenderTile() {
        if (selectedTileGraphic != null) {
            canvas.remove(selectedTileGraphic);
        }
        selectedTileGraphic = null;
    }

    private void renderPieces() {
        unrenderMoves();
        unrenderPieces();
        piecesGroup = gameManager.renderPieces();
        canvas.add(piecesGroup);
    }

    private void unrenderPieces() {
        if (piecesGroup!=null) {
            canvas.remove(piecesGroup);
        }
        piecesGroup = null;
    }    

    private void unrenderMoves() {
        if (movesGroup != null) {
            canvas.remove(movesGroup);
        }
        movesGroup = null;
    }

    private void renderMoves() {
        unrenderMoves();
        movesGroup = gameManager.getLegalMovesFrom(selectedTile);
        canvas.add(movesGroup);
    }

    private void setupListeners() {
        canvas.onMouseDown(event -> {
            handleClick(event.getPosition());
        });
        canvas.onKeyDown(event -> {
            handleKeyPress(event.getKey(), event.getModifiers());
        });
    }

    private void renderInitial() {
        canvas = new CanvasWindow("checkers", SCREEN_SIZE, SCREEN_SIZE);
        canvas.setBackground(new Color(179,184,189));
        for (int i = 0; i<32; i++) {
            int col = 2*(i%4)+ (1 + i/4)%2;
            int row = i/4;
            row*=TILE_SIZE;
            col*=TILE_SIZE;
            Rectangle rect = new Rectangle(col,row,TILE_SIZE,TILE_SIZE);
            rect.setFilled(true);
            rect.setFillColor(new Color(39,33,46));
            canvas.add(rect);
        }
        renderPieces();
        canvas.draw();
    }

    public static void main(String[] args) {
        CheckersApp CheckersApp = new CheckersApp();
    }

}



