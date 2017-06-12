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

    public void printTree(T node) {
        printTree(node, 0);
    }

    public void printTree(T node, int level) {

        if(node == null) {
            return;
        }

        String space = "";

        for(int x = 0; x < level; x++) {
            space += "    ";
        }

        printNodeDetails(space);

        printTree(node.getLeftChild(), level+1);
        printTree(node.getRightChild(), level+1);

    }

    protected abstract void printNodeDetails(String space);

    public void rotateLeft(T root) {

        if(this.parent == null) {
            root = this.rightChild;
        }
        else if(this == this.parent.leftChild) {
            this.parent.leftChild = this.rightChild;
        }
        else {
            this.parent.rightChild = this.rightChild;
        }

        this.rightChild.parent = this.parent;
        this.parent = this.rightChild;
        this.rightChild = this.parent.leftChild;

        if(this.rightChild != null) {
            this.rightChild.parent = (T) this;
        }

        this.parent.leftChild = (T) this;

    }

    public void rotateRight(T root) {

        if(this.parent == null) {
            root = this.leftChild;
        }
        else if(this == this.parent.leftChild) {
            this.parent.leftChild = this.leftChild;
        }
        else {
            this.parent.rightChild = this.leftChild;
        }

        this.leftChild.parent = this.parent;
        this.parent = this.leftChild;
        this.leftChild = this.parent.rightChild;

        if(this.leftChild != null) {
            this.leftChild.parent = (T) this;
        }

        this.parent.rightChild = (T) this;

    }

    public T getGrandparent() {

        if(this.parent != null && this.parent.parent != null) {
            return this.parent.parent;
        }
        else {
            return null;
        }

    }

    public T getUncle() {

        T grandparent = getGrandparent();

        if(grandparent == null) {
            return null;
        }

        if(this.parent == grandparent.rightChild) {
            return grandparent.leftChild;
        }
        else {
            return grandparent.rightChild;
        }

    }

    public T getSibling() {

        if(this == null || this.parent == null){
            return null;
        }

        if(this == this.parent.leftChild) {
            return this.parent.rightChild;
        }
        else {
            return this.parent.leftChild;
        }

    }

    public T getParent() {
        return this.parent;
    }

    public T getLeftChild() {
        return this.leftChild;
    }

    public T getRightChild() {
        return this.rightChild;
    }

    public void setParent(T newParent) {
        this.parent = newParent;
    }

    public void setLeftChild(T newLeftChild) {
        this.leftChild = newLeftChild;
    }

    public void setRightChild(T newRightChild) {
        this.rightChild = newRightChild;
    }

}
