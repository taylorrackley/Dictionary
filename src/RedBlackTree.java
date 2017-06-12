import com.sun.org.apache.regexp.internal.RE;

/**
 * Created by Taylor-PC on 6/7/2017.
 */
public class RedBlackTree extends SearchTree {

    private RBNode root;

    public void insertWord(String[] command) {

        RBNode newNode = (RBNode) findWord(root, command[1]);

        if (root == null) {
            root = new RBNode(command[1],command[2]);
        }

        if(newNode == null) {

            newNode = new RBNode(command[1], command[2].trim());
            insertNode(root, newNode);
            newNode.setColor(NodeColor.RED);

            insert1(newNode);

        } else {

            if (!newNode.doesDefinitionExist(command[2])) {
                newNode.addDefinition(command[2]);
            }

        }

    }

    public void deleteWord(String[] command) {

        RBNode node = (RBNode) findWord(root, command[1]);

        if(node == null) {
            return;
        }
        else {
            delete1(node);
        }

    }

    private void insert1(RBNode node) {

        if (root == null || node == root || node.getParent() == null) {

            root = node;
            root.setColor(NodeColor.BLACK);
            return;

        }
        else {
            insert2(node);
        }

    }

    private void insert2(RBNode node) {



    }

    private void delete1(RBNode node) {

        if(root == node) {

            if(node.getLeftChild() != null) {

                RBNode temp = node.getLeftChild();

                while(temp.getRightChild() != null) {
                    temp = temp.getRightChild();
                }

                node.setWord(temp.getWord());

                if(node.getLeftChild().getColor() == NodeColor.BLACK && node.getLeftChild() == temp) {

                    node.rotateLeft();
                    node.getParent().setColor(NodeColor.BLACK);

                    if(node.getParent().getRightChild() != null) {
                        node.getParent().getRightChild().setColor(NodeColor.BLACK);
                    }
                    else {
                        node.setColor(NodeColor.RED);
                    }

                }

                deleteNode(root, temp);

            }
            else if (node.getRightChild() != null) {

                node.setWord(node.getRightChild().getWord());

                if(node.getRightChild().getColor() == NodeColor.BLACK) {

                    node.rotateRight();
                    node.getParent().setColor(NodeColor.BLACK);

                    if(node.getParent().getLeftChild() != null) {
                        node.getParent().getLeftChild().setColor(NodeColor.BLACK);
                    }
                    else {
                        node.setColor(NodeColor.RED);
                    }

                }

                deleteNode(root, node.getRightChild());
            }
            else {
                deleteNode(root, node);
            }

        }
        else {
            delete2(node);
        }

    }

    private void delete2(RBNode node) {

        if(node.getLeftChild() == null || node.getRightChild() == null) {

            // If node is red both children are null and can be deleted
            if(node.getColor() == NodeColor.RED) {
                deleteNode(root, node);
            }
            else {

                // node is black and has one red child
                if(node.getLeftChild() != null) {

                    node.setWord(node.getLeftChild().getWord());
                    deleteNode(root, node.getLeftChild());

                }
                else if(node.getRightChild() != null) {

                    node.setWord(node.getRightChild().getWord());
                    deleteNode(root, node.getRightChild());

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

    private void delete3(RBNode node) {

        // if red must have two blacks
        if(node.getColor() == NodeColor.RED) {

            RBNode temp = node.getLeftChild();

            while(temp.getRightChild() != null) {
                temp = temp.getRightChild();
            }

            node.setWord(temp.getWord());
            delete1(temp);

        }
        else {
            delete4(node);
        }

    }

    private void delete4(RBNode node) {

        RBNode sibling = node.getSibling();

        // if sibling is black and at least one of its children is red
        if(sibling.getColor() == NodeColor.BLACK && ((sibling.getLeftChild() != null && sibling.getLeftChild().getColor() == NodeColor.RED) || (sibling.getRightChild() != null && sibling.getRightChild().getColor() == NodeColor.RED)) ) {

            // Sibling is left child and is black with a red left child
            if(node.getParent().getLeftChild() == sibling) {

                if(sibling.getLeftChild() == null) {

                    sibling.setColor(NodeColor.RED);
                    sibling.getRightChild().setColor(NodeColor.BLACK);
                    sibling.rotateLeft();

                }

                node.getParent().rotateRight();
                node.getParent().getParent().setColor(NodeColor.RED);
                node.getParent().getParent().getLeftChild().setColor(NodeColor.BLACK);
                node.getParent().getParent().getRightChild().setColor(NodeColor.BLACK);
                deleteNode(root, node);

            }
            else if (node.getParent().getRightChild() == sibling) {

                if(sibling.getRightChild() == null) {

                    sibling.setColor(NodeColor.RED);
                    sibling.getLeftChild().setColor(NodeColor.BLACK);
                    sibling.rotateRight();

                }

                node.getParent().rotateLeft();
                node.getParent().getParent().setColor(NodeColor.RED);
                node.getParent().getParent().getLeftChild().setColor(NodeColor.BLACK);
                node.getParent().getParent().getRightChild().setColor(NodeColor.BLACK);
                deleteNode(root, node);

            }

        }
        else {
            delete5(node);
        }

    }

    private void delete5(RBNode node) {

        RBNode sibling = node.getSibling();

        if(sibling.getColor() == NodeColor.BLACK) {

            // double red is prevented from delete2
            sibling.setColor(NodeColor.RED);
            deleteNode(root, node);

            if(sibling.getParent().getColor() == NodeColor.RED) {
                sibling.getParent().setColor(NodeColor.BLACK);
            }
            else {
                delete1(sibling.getParent());
            }

        }
        else {
            delete6(node);
        }

    }

    private void delete6(RBNode node) {

        RBNode sibling = node.getSibling();
        // sibling must be red and have two black children

        sibling.setColor(NodeColor.BLACK);
        deleteNode(root, node);

        if(sibling.getParent().getRightChild() == sibling) {

            sibling.getLeftChild().setColor(NodeColor.RED);
            sibling.getParent().rotateLeft();

        }
        else {

            sibling.getRightChild().setColor(NodeColor.RED);
            sibling.getParent().rotateRight();

        }

    }

}
