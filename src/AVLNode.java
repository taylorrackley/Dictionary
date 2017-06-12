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

    public void printNodeDetails(String space) {

        System.out.println(space + this.word + " (" + this.getLevel() + ')');

    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

}
