import javafx.fxml.FXMLLoader;

/**
 * Created by Taylor-PC on 6/7/2017.
 */
public abstract class BasicNode <T extends BasicNode<T>>{

    public String word;
    public String[] definitions;

    public T leftChild;
    public T rightChild;
    public T parent;

//    private NodeColor color;
//    private int level;

//    BasicNode leftChild;
//    BasicNode rightChild;
//    BasicNode parent;

    BasicNode(String word, String definition) {
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

    public abstract void deleteWord(String word);

    protected void rotateLeft(Node node) {

    }

    protected void rotateRight(Node node) {

    }

    protected T getGrandparent(T node) {

        if(node.parent != null && node.parent.parent != null)
            return node.parent.parent;
        else
            return null;

    }

    protected T getUncle(T node) {

        T grandparent = getGrandparent(node);

        if(grandparent == null) {
            return null;
        }

        if(node.parent == grandparent.rightChild) {
            return grandparent.leftChild;
        }
        else {
            return grandparent.rightChild;
        }

    }

    protected T getSibling(T node) {

        if(node == null || node.parent == null){
            return null;
        }

        if(node == node.parent.leftChild) {
            return node.parent.rightChild;
        }
        else {
            return node.parent.leftChild;
        }

    }

}
