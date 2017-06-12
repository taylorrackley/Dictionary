/**
 * Created by Taylor-PC on 6/11/2017.
 */
public class Node extends BasicNode {

    private Node leftChild;
    private Node rightChild;
    private Node parent;

    Node(String word, String definition) {
        super(word, definition);
    }

    public void printNodeDetails(String space) {
        System.out.println(space + this.word);
    }

}
