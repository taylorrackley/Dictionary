/**
 * Created by Taylor-PC on 6/7/2017.
 */
public abstract class SearchTree {

    private Node root;
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

    public void printTree(Node node, int level) {

        if(root == null) {
            System.out.println("Tree is empty.");
            return;
        } if(node == null)
            return;

        String space = "";

        for(int x = 0; x < level; x++)
            space += "    ";

        if(treeType.equals("RB")) {
            String color;
            if(node.color)
                color = "Red";
            else
                color = "Black";
            System.out.println(space + node.word + " (" + color + ')');
        } else if(treeType.equals("AVL"))
            System.out.println(space + node.word + " (" + node.level + ')');
        else
            System.out.println(space + node.word);

        printTree(node.leftChild, level+1);
        printTree(node.rightChild, level+1);

    }

    public void list(String[] command) {



    }

    public Node findWord(String word) {

        Node node = root;

        while(node != null) {
            if(node.word.equalsIgnoreCase(word))
                return node;

            if(node.word.compareToIgnoreCase(word) < 0)
                node = node.rightChild;
            else
                node = node.leftChild;
        }

        return node;

    }

    protected Node insertNode(Node node) {

        Node nodeParent = null;
        Node searchNode = root;

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

    protected void deleteNode(Node node) {

        Node temp;

        if(node.leftChild != null) {

            temp = node.leftChild;

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
