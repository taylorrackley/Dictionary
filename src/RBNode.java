/**
 * Created by Taylor-PC on 6/10/2017.
 */
public class RBNode extends BasicNode <RBNode> {

    private NodeColor color;

    private RBNode leftChild;
    private RBNode rightChild;
    private RBNode parent;

    RBNode(String word, String definition) {
        super(word, definition);
    }

    public void printNodeDetails(String space) {

        String color;

        if(this.getColor() == NodeColor.RED) {
            color = "Red";
        }
        else {
            color = "Black";
        }

        System.out.println(space + this.word + " (" + color + ')');

    }

    public void setColor(NodeColor color) {
        this.color = color;
    }

    public NodeColor getColor() {
        return color;
    }

}
