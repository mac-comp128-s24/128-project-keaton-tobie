import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;

public class MoveValidator {

    public MoveValidator() {

    }

    public List<Move> getLegalMoves(Board b) {
        List<Move> moves = new ArrayList<>();
        Map<Tile,ChessPiece> pp=b.getPlayingPieces();
        // if (b.getCheck()) {
        //     for (Entry<Tile,ChessPiece> p : pp.entrySet()) {
        //         addLegalMovesCheck(p, b, moves);
        //     }
        //     return moves;
        // }
        for (Entry<Tile,ChessPiece> p : pp.entrySet()) {
            System.out.println("adding move for new piece");
            addLegalMoves(p, b, moves);
        }
        return moves;
    }

    public List<Move> getLegalMovesFrom(Tile t, Board b) {
        List<Move> moves = new ArrayList<>();
        for (Entry<Tile,ChessPiece> p : b.getPlayingPieces().entrySet()) {
            if (p.getKey().equals(t)) {
                addLegalMoves(p, b, moves);
            }
        }
        return moves;
    }

    private void addLegalMoves(Entry<Tile,ChessPiece> p, Board b, List<Move> moves) {
        switch (p.getValue().getType()) {
            case PAWN: 
                addPawnMoves(p, b, moves);
                break;
            case ROOK: 
                addRookMoves(p.getKey(), b, moves);
                break;
            case KNIGHT: 
                addKnightMoves(p.getKey(), b, moves);
                break;
            case BISHOP: 
                addBishopMoves(p.getKey(), b, moves);
                break;
            case QUEEN: 
                addQueenMoves(p.getKey(), b, moves);
                break;
            case KING: 
                addKingMoves(p.getKey(), b, moves);
                break;
            case CHECKER: 
                addCheckerMoves(p.getKey(), b, moves);
                break;
        }
    }

    private void addPawnMoves(Entry<Tile,ChessPiece> p, Board b, List<Move> moves) {
        System.out.println("pawn");
        Tile t = p.getKey();
        int direction = 1;
        if (b.getPlaying()) {
            direction = -1;
        }
        Tile fwd = t.move(new Tile(0,direction));
        if (checkTile(fwd, b)==TileType.EMPTY) {
            moves.add(new Move(t,fwd));
            if (!p.getValue().getMoved()) {
                Tile two = t.move(new Tile(0,direction*2));
                if (checkTile(fwd, b)==TileType.EMPTY) {
                    moves.add(new Move(t,two));
                }
            }
        }
        Tile left = t.move(new Tile(-1,direction));
        if (checkTile(left, b)==TileType.ENEMY) {
            moves.add(new Move(t,left));
        }
        Tile right = t.move(new Tile(1,direction));
        if (checkTile(right, b)==TileType.ENEMY) {
            moves.add(new Move(t,right));
        }
    }
    
    private void addRookMoves(Tile t, Board b, List<Move> moves) {
        System.out.println("rook");
        addTillEnemy(t, 0, 1, b, moves);
        addTillEnemy(t, 0, -1, b, moves);
        addTillEnemy(t, 1, 0, b, moves);
        addTillEnemy(t, -1, 0, b, moves);
        
    }

    private void addBishopMoves(Tile t, Board b, List<Move> moves) {
        System.out.println("bishop");
        addTillEnemy(t, 1, 1, b, moves);
        addTillEnemy(t, -1, -1, b, moves);
        addTillEnemy(t, 1, -1, b, moves);
        addTillEnemy(t, -1, 1, b, moves);
    }

    private void addKnightMoves(Tile t, Board b, List<Move> moves) {
        System.out.println("knight");
        Tile fl = new Tile(-1,-2).move(t);
        addIfEnemyOrEmpty(t, fl, b, moves);
        Tile lf = new Tile(-2,-1).move(t);
        addIfEnemyOrEmpty(t, lf, b, moves);
        Tile lb = new Tile(-2,1).move(t);
        addIfEnemyOrEmpty(t, lb, b, moves);
        Tile fb = new Tile(-1,2).move(t);
        addIfEnemyOrEmpty(t, fb, b, moves);
        Tile fr = new Tile(1,2).move(t);
        addIfEnemyOrEmpty(t, fr, b, moves);
        Tile rf = new Tile(2,1).move(t);
        addIfEnemyOrEmpty(t, rf, b, moves);
        Tile rb = new Tile(2,-1).move(t);
        addIfEnemyOrEmpty(t, rb, b, moves);
        Tile br = new Tile(1,-2).move(t);
        addIfEnemyOrEmpty(t, br, b, moves);
    }

    private void addQueenMoves(Tile t, Board b, List<Move> moves) {
        System.out.println("queen");
        addTillEnemy(t, 0, 1, b, moves);
        addTillEnemy(t, 0, -1, b, moves);
        addTillEnemy(t, 1, 0, b, moves);
        addTillEnemy(t, -1, 0, b, moves);
        addTillEnemy(t, 1, 1, b, moves);
        addTillEnemy(t, -1, -1, b, moves);
        addTillEnemy(t, 1, -1, b, moves);
        addTillEnemy(t, -1, 1, b, moves);
    }

    private void addKingMoves(Tile t, Board b, List<Move> moves) {
        System.out.println("king");
        ChessPiece king = b.getPlayingPieces().get(t);
        if (!king.getMoved()) {
            Tile side = new Tile(0, 0);
            if (b.getPlaying()) {
                side = new Tile(0,7); 
            }
            Tile leftRookTile = new Tile(0,0).move(side);
            ChessPiece leftRook = b.getPlayingPieces().get(leftRookTile);
            if (leftRook!=null&&!leftRook.getMoved()) {
                if (checkTile(new Tile(1,0).move(side), b)==TileType.EMPTY&&checkTile(new Tile(2,0).move(side), b)==TileType.EMPTY&&checkTile(new Tile(3,0).move(side), b)==TileType.EMPTY) {
                    moves.add(new Move(t, new Tile(2,0).move(side)));
                } 
            }
            Tile rightRookTile = new Tile(7,0).move(side);
            ChessPiece rightRook = b.getPlayingPieces().get(rightRookTile);
            if (rightRook!=null&&!rightRook.getMoved()) {
                if (checkTile(new Tile(6,0).move(side), b)==TileType.EMPTY&&checkTile(new Tile(5,0).move(side), b)==TileType.EMPTY) {
                    moves.add(new Move(t, new Tile(6,0).move(side)));
                }
            }
        }
        // if (b.getPlayingPieces().get(moves))
        addIfEnemyOrEmpty(t, new Tile(0,-1).move(t), b, moves);
        addIfEnemyOrEmpty(t, new Tile(-1,-1).move(t), b, moves);
        addIfEnemyOrEmpty(t, new Tile(-1,0).move(t), b, moves);
        addIfEnemyOrEmpty(t, new Tile(-1,1).move(t), b, moves);
        addIfEnemyOrEmpty(t, new Tile(0,1).move(t), b, moves);
        addIfEnemyOrEmpty(t, new Tile(1,1).move(t), b, moves);
        addIfEnemyOrEmpty(t, new Tile(1,0).move(t), b, moves);
        addIfEnemyOrEmpty(t, new Tile(1,-1).move(t), b, moves);
    }

    private void addCheckerMoves(Tile t, Board b, List<Move> moves) {
        return;
    }

    private void addIfEnemyOrEmpty(Tile from, Tile to, Board b, List<Move> moves) {
        TileType type = checkTile(to, b);
        if (type==TileType.EMPTY||type==TileType.ENEMY) {
            moves.add(new Move(from, to));
        }
    }

    private void addTillEnemy(Tile from, int col, int row, Board b, List<Move> moves) {
        for (int i = 1; i<8; i++) {
            Tile to = new Tile(i*col,i*row);
            to = to.move(from);
            switch (checkTile(to, b)) {
                case EMPTY: 
                    moves.add(new Move(from, to));
                    break;
                case ENEMY: 
                    moves.add(new Move(from, to));
                    return;
                case FRIENDLY:
                    return;
                case INVALID:
                    return;
            }
        }
    }

    

    private boolean isCheck(Tile t, Board b) {
        boolean check = false;
        Tile fl = new Tile(-1,-2).move(t);
        check = check || isEnemy(fl, b);
        Tile lf = new Tile(-2,-1).move(t);
        check = check || isEnemy(lf, b);
        Tile lb = new Tile(-2,1).move(t);
        check = check || isEnemy(lb, b);
        Tile fb = new Tile(-1,2).move(t);
        check = check || isEnemy(fb, b);
        Tile fr = new Tile(1,2).move(t);
        check = check || isEnemy(fr, b);
        Tile rf = new Tile(2,1).move(t);
        check = check || isEnemy(rf, b);
        Tile rb = new Tile(2,-1).move(t);
        check = check || isEnemy(rb, b);
        Tile br = new Tile(1,-2).move(t);
        check = check || isEnemy(br, b);
        for (int i = 0; i<8; i++) {
            
        }
        return check;
        
    }

    private boolean isEnemy(Tile t, Board b) {
        return (checkTile(t, b).equals(TileType.ENEMY));
    }

    /**
     * gets the tiletype of the tile with respect to the board
     * INVALID - tile not on board
     * FRIENDLY - tile contains a friendly piece
     * ENEMY - tile contains an enemy piece
     * EMPTY - tile is on the board and empty
     * @param t Tile to check
     * @param b Board to check
     * @return Tiletype
     */
    private TileType checkTile(Tile t, Board b) {
        if (t.getCol()<0||t.getRow()<0||t.getRow()>7||t.getCol()>7) {
            System.out.println("invalid");
            return TileType.INVALID;
        }
        if (b.getPlayingPieces().containsKey(t)) {
            System.out.println("friend");
            return TileType.FRIENDLY;
        } 
        if (b.getWaitingPieces().containsKey(t)) {
            System.out.println("enemy");
            return TileType.ENEMY;
        }
        System.out.println("empty");
        return TileType.EMPTY;
    }

    private void addLegalMovesCheck(Entry<Tile,ChessPiece> p, Board b, List<Move> moves) {
        addLegalMoves(p, b, moves);
    }
}
