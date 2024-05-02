import java.util.List;

public class BoardNode {
    private Board board;
    private List<BoardNode> children;
    private double evaluation;
    private int depth;

    public BoardNode(Board board, int depth) {
        this.board = board;
        this.depth = depth;
    }

    public void setChildren(List<BoardNode> children) {
        this.children = children;
    }

    public void evaluate() {
        if (depth==0) {
            this.evaluation = 0;
        }
    }
}
