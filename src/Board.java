import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Board {
    private boolean check = false;
    private boolean whiteIsPlaying;
    private HashMap<Tile, ChessPiece> playingPieces = new HashMap<>();
    private HashMap<Tile, ChessPiece> waitingPieces = new HashMap<>();

    public Board() {
        this.whiteIsPlaying = true;
        initializeBoard();
    }

    public Board(boolean whiteIsPlaying, HashMap<Tile, ChessPiece> playingPieces, HashMap<Tile, ChessPiece> waitingPieces) {
        this.whiteIsPlaying = whiteIsPlaying;
        this.playingPieces = playingPieces;
        this.waitingPieces = waitingPieces;
    }

    private void initializeBoard() {
        for (int i = 0; i<8; i++) {
            ChessPiece p = new ChessPiece(PieceType.PAWN, false);
            playingPieces.put(new Tile(i, 6), p);
        }

        for (int i = 0; i<8; i++) {
            ChessPiece p = new ChessPiece(PieceType.PAWN, false);
            waitingPieces.put(new Tile(i, 1), p);
        }

        for (int i = 0; i < 4; i++) {
            boolean w = i % 2 ==0;
            ChessPiece p = new ChessPiece(PieceType.ROOK, false);
            if (w) {
                playingPieces.put(new Tile(0 + (i/2)*7,7), p);
            } else {
                waitingPieces.put(new Tile(0 + (i/2)*7,0), p);
            }
        }

        for (int i = 0; i < 4; i++) {
            boolean w = i % 2 ==0;
            ChessPiece p = new ChessPiece(PieceType.KNIGHT, false);
            if (w) {
                playingPieces.put(new Tile(1 + (i/2)*5,7), p);
            } else {
                waitingPieces.put(new Tile(1 + (i/2)*5,0), p);
            }
        }

        for (int i = 0; i < 4; i++) {
            boolean w = i % 2 ==0;
            ChessPiece p = new ChessPiece(PieceType.BISHOP, false);
            if (w) {
                playingPieces.put(new Tile(2 + (i/2)*3,7), p);
            } else {
                waitingPieces.put(new Tile(2 + (i/2)*3,0), p);
            }
        }

        for (int i = 0; i < 2; i++) {
            boolean w = i % 2 ==0;
            ChessPiece p = new ChessPiece(PieceType.QUEEN, false);
            if (w) {
                playingPieces.put(new Tile(3,7), p);
            } else {
                waitingPieces.put(new Tile(3,0), p);
            }
        }

        for (int i = 0; i < 2; i++) {
            boolean w = i % 2 ==0;
            ChessPiece p = new ChessPiece(PieceType.KING, false);
            if (w) {
                playingPieces.put(new Tile(4,7), p);
            } else {
                waitingPieces.put(new Tile(4,0), p);
            }
        } 
    }
    
    public HashMap<Tile, ChessPiece> getWhiteTiles() {
        if (whiteIsPlaying) {
            return playingPieces;
        }
        return waitingPieces;
    }

    public HashMap<Tile, ChessPiece> getBlackTiles() {
        if (!whiteIsPlaying) {
            return playingPieces;
        }
        return waitingPieces;
    }

    public HashMap<Tile, ChessPiece> getPlayingPieces() {
        return playingPieces;
    }

    public HashMap<Tile, ChessPiece> getWaitingPieces() {
        return waitingPieces;
    }

    public boolean getPlaying() {
        return whiteIsPlaying;
    }

    public Board makeMove(Move m) {
        HashMap<Tile, ChessPiece> ppcopy = clonePieces(playingPieces);
        HashMap<Tile, ChessPiece> wpcopy = clonePieces(waitingPieces);
        ChessPiece movedPiece = ppcopy.get(m.start);
        if (!movedPiece.getMoved()) {
            movedPiece = new ChessPiece(movedPiece.getType(),true);
        }
        ppcopy.remove(m.start);
        wpcopy.remove(m.end);
        ppcopy.put(m.end,movedPiece);
        Board b = new Board(!whiteIsPlaying, wpcopy, ppcopy);
        return b;
    }

    public Board makeCheck(Move m) {
        Board b = makeMove(m);
        b.setCheck();
        return b;
    }

    private HashMap<Tile, ChessPiece> clonePieces(HashMap<Tile, ChessPiece> pieces) {
        HashMap<Tile, ChessPiece> np = new HashMap<>();
        for (Tile t : pieces.keySet()) {
            np.put(t, pieces.get(t));
        }
        return np;
    }

    private void setCheck() {
        check = true;
    }

    public boolean getCheck() {
        return check;
    }

    public boolean getFriend(Tile t) {
        return playingPieces.containsKey(t);
    }

    public static boolean validate(Tile t) {
        return !(t.getCol()>8||t.getRow()<1||t.getRow()>8||t.getCol()<1);
    }

    /**
     * returns the tiles from the set that are on the board
     * @param tiles a set of tiles
     * @return a set of tiles that are on the board
     */
    public Set<Tile> validate(Set<Tile> tiles) {
        Set<Tile> validTiles = new HashSet<>();
        for (Tile t : tiles) {
            if (validate(t)) {
                validTiles.add(t);
            }
        }
        return validTiles;
    }
    
}
