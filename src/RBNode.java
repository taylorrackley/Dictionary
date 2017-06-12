/**
 * Created by Taylor-PC on 6/10/2017.
 */
public class RBNode extends BasicNode {

    private NodeColor color;

    private RBNode leftChild;
    private RBNode rightChild;
    private RBNode parent;

    RBNode(String word, String definition) {
        super(word, definition);
    }

    public void setColor(SearchTree tree, NodeColor color) {

        try {

            if (tree instanceof RedBlackTree) {
                this.color = color;
            } else {
                throw new IncorrectTreeNodePair(tree);
            }

        }
        catch(IncorrectTreeNodePair e) {
            e.printStackTrace();
        }

    }

    public NodeColor getColor(SearchTree tree) {

        try {

            if (tree instanceof RedBlackTree) {
                return color;
            } else {
                throw new IncorrectTreeNodePair(tree);
            }

        }
        catch(IncorrectTreeNodePair e) {
            e.printStackTrace();
        }

        return null;

    }

}
