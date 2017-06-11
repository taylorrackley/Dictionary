/**
 * Created by Taylor-PC on 6/7/2017.
 */
public class AVLNode extends Node {

    int level;

    AVLNode leftChild;
    AVLNode rightChild;
    AVLNode parent;

    AVLNode (String word, String definition) {
        super(word, definition);
    }

}
