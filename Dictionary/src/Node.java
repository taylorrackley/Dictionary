/**
 * Created by Taylor-PC on 6/7/2017.
 */
public class Node {

    String word;
    String[] definitions;

    private NodeColor color;
    private int level;

    Node leftChild;
    Node rightChild;
    Node parent;

    Node(String word, String definition) {
        this.word = word;
        this.definitions = new String[] {definition};
    }

    public boolean doesDefinitionExist(String definition) {

        // Loop through definitions array, if the array contains the definitions in question return true, otherwise return false.
        for (int x = 0; x < definitions.length; x++) {
            if(definition.equalsIgnoreCase(definitions[x])) {
                return true;
            }
        }

        return false;

    }

    public void addDefinition(String newDefinition) {

        // Create temporary array to store all current definitions
        // Reinitialize definitions array with enough room to add new definition
        String[] tempArray = new String[definitions.length+1];

        int split = 0;

        while(split < definitions.length)
            if(newDefinition.compareToIgnoreCase(definitions[split]) < 0) {
                break;
            }
            else {
                split++;
            }

        for(int x = 0; x < split; x++) {
            tempArray[x] = definitions[x];
        }

        tempArray[split] = newDefinition;

        for(int x = split; x < definitions.length; x++) {
            tempArray[x + 1] = definitions[x];
        }

        definitions = new String[definitions.length + 1];

        for(int x = 0; x < definitions.length; x++) {
            definitions[x] = tempArray[x];
        }

    }

    public void setColor(SearchTree tree, NodeColor color) {

        try {

            if (tree instanceof RedBlackTree) {
                this.color = color;
            } else {
                throw new IncorrectTreeNodePair(tree);
            }

        }
        catch(IncorrectTreeNodePair e) {
            e.printStackTrace();
        }

    }

    public NodeColor getColor(SearchTree tree) {

        try {

            if (tree instanceof RedBlackTree) {
                return color;
            } else {
                throw new IncorrectTreeNodePair(tree);
            }

        }
        catch(IncorrectTreeNodePair e) {
            e.printStackTrace();
        }

        return null;

    }

    public void setLevel(SearchTree tree, int level) {

        try {

            if (tree instanceof AVLTree) {
                this.level = level;
            } else {
                throw new IncorrectTreeNodePair(tree);
            }

        }
        catch(IncorrectTreeNodePair e) {
            e.printStackTrace();
        }

    }

    public int getLevel(SearchTree tree) {

        try {

            if (tree instanceof AVLTree) {
                return level;
            } else {
                throw new IncorrectTreeNodePair(tree);
            }

        }
        catch(IncorrectTreeNodePair e) {
            e.printStackTrace();
        }

        return 0;

    }

}
