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

            if(node.getWord().equalsIgnoreCase(word)) {
                return node;
            }

            if(node.getWord().compareToIgnoreCase(word) < 0) {
                node = node.getRightChild();
            }
            else {
                node = node.getLeftChild();
            }
        }

        return node;

    }

    protected T insertNode(T root, T node) {

        T nodeParent = null;
        T searchNode = root;

        while(searchNode != null) {

            nodeParent = searchNode;
            if(node.getWord().compareToIgnoreCase(searchNode.getWord()) > 0) {
                searchNode = searchNode.getRightChild();
            }
            else {
                searchNode = searchNode.getLeftChild();
            }

        }

        node.setParent(nodeParent);

        if(nodeParent == null) {
            root = node;
        }
        else if(node.getWord().compareToIgnoreCase(nodeParent.getWord()) > 0) {
            nodeParent.setRightChild(node);
        }
        else {
            nodeParent.setLeftChild(node);
        }

        return node;

    }

    protected void deleteNode(T root, T node) {

        T temp;

        if(node.getLeftChild() != null) {

            temp = node.getLeftChild();

            while(temp.getRightChild() != null) {
                temp = temp.getRightChild();
            }

            node.setWord(temp.getWord());

            if(temp == temp.getParent().getRightChild()) {
                temp.getParent().setRightChild(temp.getLeftChild());
            }
            else {
                node.setLeftChild(temp.getLeftChild());
            }
            if(temp.getLeftChild() != null) {
                temp.getLeftChild().setParent(temp.getParent());
            }

            temp = null;

        }
        else if(node.getRightChild() != null) {

            node.setWord(node.getRightChild().getWord());
            node.setRightChild(null);

        }
        else if (node == root) {
            root = null;
        }
        else {

            if(node == node.getParent().getRightChild()) {
                node.getParent().setRightChild(null);
            }
            else {
                node.getParent().setLeftChild(null);
            }

        }

    }

    protected void printList(Node node) {

    }

    protected void printList(Node node, String begin, String end) {

    }

}
