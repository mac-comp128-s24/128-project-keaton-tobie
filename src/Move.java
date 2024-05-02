public class Move implements Comparable{
    public Tile start;
    public Tile end;
    public double score = 0;

    public Move(Tile start, Tile end) {
        this.start = start;
        this.end = end;
    }

    public Move(Tile start, Tile end, double score) {
        this.start = start;
        this.end = end;
        this.score = score;
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
    @Override
    public int compareTo(Object o) {
        return Double.compare(this.score, ((Move)o).score);
    }

}

