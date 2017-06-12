/**
 * Created by Taylor-PC on 6/7/2017.
 */
public abstract class SearchTree <T extends BasicNode<T>> {

    SearchTreeType treeType;

//    SearchTree(SearchTreeType treeType) {
//        this.treeType = treeType;
//
//        if(treeType == SearchTreeType.RB) {
//           root = new RedBlackNode("test", "def");
//        }
//    }

    public abstract void insertWord(String[] command);

    public abstract void deleteWord(String[] command);

    public void list(String[] command) {



    }

    public T findWord(T node, String word) {

        while(node != null) {

            if(node.word.equalsIgnoreCase(word)) {
                return node;
            }

            if(node.word.compareToIgnoreCase(word) < 0) {
                node = node.rightChild;
            }
            else {
                node = node.leftChild;
            }
        }

        return node;

    }

    protected T insertNode(T root, T node) {

        T nodeParent = null;
        T searchNode = root;

        while(searchNode != null) {

            nodeParent = searchNode;
            if(node.word.compareToIgnoreCase(searchNode.word) > 0) {
                searchNode = searchNode.rightChild;
            }
            else {
                searchNode = searchNode.leftChild;
            }

        }

        node.parent = nodeParent;

        if(nodeParent == null) {
            root = node;
        }
        else if(node.word.compareToIgnoreCase(nodeParent.word) > 0) {
            nodeParent.rightChild = node;
        }
        else {
            nodeParent.leftChild = node;
        }

        return node;

    }

    protected void deleteNode(T node) {

        T temp;

        if(node.getLeftChild() != null) {

            temp = node.getLeftChild();

            while(temp.rightChild != null) {
                temp = temp.rightChild;
            }

            node.word = temp.word;

            if(temp == temp.parent.rightChild) {
                temp.parent.rightChild = temp.leftChild;
            }
            else {
                node.leftChild = temp.leftChild;
            }
            if(temp.leftChild != null) {
                temp.leftChild.parent = temp.parent;
            }

            temp = null;

        }
        else if(node.rightChild != null) {

            node.word = node.rightChild.word;
            node.rightChild = null;

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

        }

    }

    protected void printList(Node node) {

    }

    protected void printList(Node node, String begin, String end) {

    }

}
