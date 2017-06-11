/**
 * Created by Taylor-PC on 6/7/2017.
 */
public class AVLTree extends SearchTree {

//    public AVLTree() {
//        super(SearchTreeType.AVL);
//    }

    public void insertWord(String[] command) {

        AVLNode newNode = (AVLNode) findWord(command[1]);

        if(newNode == null) {

            newNode = new AVLNode(command[1], command[2].trim());
            insertNode(newNode);
            newNode.level = 1;

            fixTree(newNode);

        } else {

            if (!newNode.doesDefinitionExist(command[2])) {
                newNode.addDefinition(command[2]);
            }

        }

    }

    public void deleteWord(String[] command) {

        AVLNode node = (AVLNode) findWord(command[0]);

        if(node == null) {
            return;
        }

        AVLNode temp = null;

        if(node.leftChild != null) {

            temp = node.leftChild;

            while(temp.rightChild != null) {
                temp = temp.rightChild;
            }

            node.word = temp.word;

            deleteNode(temp);

        }
        else if(node.rightChild != null) {

            node.word = node.rightChild.word;
            node.rightChild = null;
            temp = node;

        }
        else if (node == root) {
            root = null;
        }
        else {

            if(node == node.parent.rightChild) {
                node.parent.rightChild = null;
            }
            else {
                node.parent.leftChild = null;
            }
            temp = node;

        }

        if(temp != null) {
            fixTree(temp);
        }

    }

    private void fixTree(AVLNode node) {

        if(root == node && node.leftChild == null && node.rightChild == null) {
            node.level = 1;
        }

    }

}
