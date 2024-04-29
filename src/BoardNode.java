public class BoardNode {
    private Board board;
    private double evaluation;
    private int depth;

    public BoardNode(Board board, int depth) {
        this.board = board;
        this.depth = depth;
    }
}
