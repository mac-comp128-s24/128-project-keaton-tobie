public class Move {
    public Tile start;
    public Tile end; 

    public Move(Tile start, Tile end) {
        this.start = start;
        this.end = end;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Move move = (Move) obj;
        return (start.equals(move.start) && end.equals(move.end));
    }
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (start != null ? start.hashCode() : 0);
        result = 31 * result + (end != null ? end.hashCode() : 0);
        return result;
    }
}

