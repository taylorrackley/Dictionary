/**
 * Created by Taylor-PC on 6/7/2017.
 */
public class AVLNode extends BasicNode {

    private int level;

    private AVLNode leftChild;
    private AVLNode rightChild;
    private AVLNode parent;

    AVLNode (String word, String definition) {
        super(word, definition);
    }

    public void deleteWord(String word) {

    }

    public void setLevel(SearchTree tree, int level) {

        try {

            if (tree instanceof AVLTree) {
                this.level = level;
            } else {
                throw new IncorrectTreeNodePair(tree);
            }

        }
        catch(IncorrectTreeNodePair e) {
            e.printStackTrace();
        }

    }

    public int getLevel(SearchTree tree) {

        try {

            if (tree instanceof AVLTree) {
                return level;
            } else {
                throw new IncorrectTreeNodePair(tree);
            }

        }
        catch(IncorrectTreeNodePair e) {
            e.printStackTrace();
        }

        return 0;

    }

}
