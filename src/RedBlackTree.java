import com.sun.org.apache.regexp.internal.RE;

/**
 * Created by Taylor-PC on 6/7/2017.
 */
public class RedBlackTree extends SearchTree {

    private RBNode root;

//    public RedBlackTree() {
//        super(SearchTreeType.RB);
//        root = RedBlackNode;
//    }

    public void insertWord(String[] command) {

        Node newNode =  findWord(command[1]);

        if (root == null) {
            root = new RBNode(command[1],command[2]);
        }

        if(newNode == null) {

            newNode = new Node(command[1], command[2].trim());
            insertNode(newNode);
            newNode.setColor(this, NodeColor.RED);

            insert1(newNode);

        } else {

            if (!newNode.doesDefinitionExist(command[2])) {
                newNode.addDefinition(command[2]);
            }

        }

    }

    public void deleteWord(String[] command) {

        Node node = findWord(command[1]);

        if(node == null) {
            return;
        }
        else {
            delete1(node);
        }

    }

    private void insert1(Node node) {

        if (root == null || node == root || node.parent == null) {

            root = node;
            root.setColor(this, NodeColor.BLACK);
            return;

        }
        else {
            insert2(node);
        }

    }

    private void insert2(Node node) {



    }

    private void delete1(Node node) {

        if(root == node) {

            if(node.leftChild != null) {

                Node temp = node.leftChild;

                while(temp.rightChild != null) {
                    temp = temp.rightChild;
                }

                node.word = temp.word;

                if(node.leftChild.getColor(this) == NodeColor.BLACK && node.leftChild == temp) {

                    rotateLeft(node);
                    node.parent.setColor(this, NodeColor.BLACK);

                    if(node.parent.rightChild != null) {
                        node.parent.rightChild.setColor(this, NodeColor.BLACK);
                    }
                    else {
                        node.setColor(this, NodeColor.RED);
                    }

                }

                deleteNode(temp);

            }
            else if (node.rightChild != null) {

                node.word = node.rightChild.word;

                if(node.rightChild.getColor(this) == NodeColor.BLACK) {

                    rotateRight(node);
                    node.parent.setColor(this, NodeColor.BLACK);

                    if(node.parent.leftChild != null) {
                        node.parent.leftChild.setColor(this, NodeColor.BLACK);
                    }
                    else {
                        node.setColor(this, NodeColor.RED);
                    }

                }

                deleteNode(node.rightChild);
            }
            else {
                deleteNode(node);
            }

        }
        else {
            delete2(node);
        }

    }

    private void delete2(Node node) {

        if(node.leftChild == null || node.rightChild == null) {

            // If node is red both children are null and can be deleted
            if(node.getColor(this) == NodeColor.RED) {
                deleteNode(node);
            }
            else {

                // node is black and has one red child
                if(node.leftChild != null) {

                    node.word = node.leftChild.word;
                    deleteNode(node.leftChild);

                }
                else if(node.rightChild != null) {

                    node.word = node.rightChild.word;
                    deleteNode(node.rightChild);

                }
                // if black node has no children
                else {
                    delete3(node);
                }

            }

        }
        else {
            delete3(node);
        }

    }

    private void delete3(Node node) {

        // if red must have two blacks
        if(node.getColor(this) == NodeColor.RED) {

            Node temp = node.leftChild;

            while(temp.rightChild != null) {
                temp = temp.rightChild;
            }

            node.word = temp.word;
            delete1(temp);

        }
        else {
            delete4(node);
        }

    }

    private void delete4(Node node) {

        Node sibling = getSibling(node);

        // if sibling is black and at least one of its children is red
        if(sibling.getColor(this) == NodeColor.BLACK && ((sibling.leftChild != null && sibling.leftChild.getColor(this) == NodeColor.RED) || (sibling.rightChild != null && sibling.rightChild.getColor(this) == NodeColor.RED)) ) {

            // Sibling is left child and is black with a red left child
            if(node.parent.leftChild == sibling) {

                if(sibling.leftChild == null) {

                    sibling.setColor(this, NodeColor.RED);
                    sibling.rightChild.setColor(this, NodeColor.BLACK);
                    rotateLeft(sibling);

                }

                rotateRight(node.parent);
                node.parent.parent.setColor(this, NodeColor.RED);
                node.parent.parent.leftChild.setColor(this, NodeColor.BLACK);
                node.parent.parent.rightChild.setColor(this, NodeColor.BLACK);
                deleteNode(node);

            }
            else if (node.parent.rightChild == sibling) {

                if(sibling.rightChild == null) {

                    sibling.setColor(this, NodeColor.RED);
                    sibling.leftChild.setColor(this, NodeColor.BLACK);
                    rotateRight(sibling);

                }

                rotateLeft(node.parent);
                node.parent.parent.setColor(this, NodeColor.RED);
                node.parent.parent.leftChild.setColor(this, NodeColor.BLACK);
                node.parent.parent.rightChild.setColor(this, NodeColor.BLACK);
                deleteNode(node);

            }

        }
        else {
            delete5(node);
        }

    }

    private void delete5(Node node) {

        Node sibling = getSibling(node);

        if(sibling.getColor(this) == NodeColor.BLACK) {

            // double red is prevented from delete2
            sibling.setColor(this, NodeColor.RED);
            deleteNode(node);

            if(sibling.parent.getColor(this) == NodeColor.RED) {
                sibling.parent.setColor(this, NodeColor.BLACK);
            }
            else {
                delete1(sibling.parent);
            }

        }
        else {
            delete6(node);
        }

    }

    private void delete6(Node node) {

        Node sibling = getSibling(node);
        // sibling must be red and have two black children

        sibling.setColor(this, NodeColor.BLACK);
        deleteNode(node);

        if(sibling.parent.rightChild == sibling) {

            sibling.leftChild.setColor(this, NodeColor.RED);
            rotateLeft(sibling.parent);

        }
        else {

            sibling.rightChild.setColor(this, NodeColor.RED);
            rotateRight(sibling.parent);

        }

    }

}
