import javafx.fxml.FXMLLoader;

/**
 * Created by Taylor-PC on 6/7/2017.
 */
public abstract class BasicNode <T extends BasicNode<T>>{

    private String word;
    private String[] definitions;

    private T leftChild;
    private T rightChild;
    private T parent;

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

    public void rotateLeft() {

        this.getRightChild().setParent(this.getParent());

        if(this.getParent() != null) {

            if(this.getParent().getLeftChild() == this) {
                this.getParent().setLeftChild(this.getRightChild());
            } else {
                this.getParent().setRightChild(this.getRightChild());
            }

        }

        this.setParent(this.getRightChild());
        this.setRightChild(this.getParent().getLeftChild());

        if(this.getRightChild() != null) {
            this.getRightChild().setParent((T) this);
        }

        this.getParent().setLeftChild((T) this);


//        if(this.parent == null) {
//            this = this.rightChild;
//        }
//        else if(this == this.parent.leftChild) {
//            this.parent.leftChild = this.rightChild;
//        }
//        else {
//            this.parent.rightChild = this.rightChild;
//        }
//
//        this.rightChild.parent = this.parent;
//        this.parent = this.rightChild;
//        this.rightChild = this.parent.leftChild;
//
//        if(this.rightChild != null) {
//            this.rightChild.parent = (T) this;
//        }
//
//        this.parent.leftChild = (T) this;

    }

    public void rotateRight() {

        this.getLeftChild().setParent(this.getParent());

        if(this.getParent() != null) {

            if(this.getParent().getRightChild() == this) {
                this.getParent().setRightChild(this.getLeftChild());
            } else {
                this.getParent().setLeftChild(this.getLeftChild());
            }

        }

        this.setParent(this.getLeftChild());
        this.setLeftChild(this.getParent().getRightChild());

        if(this.getLeftChild() != null) {
            this.getLeftChild().setParent((T) this);
        }

        this.getParent().setRightChild((T) this);

    }

    // Word Getter
    public String getWord() {
        return this.word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    // Definitions Getter
    public String[] getDefinitions() {
        return this.definitions;
    }

    // Grandparent Getter
    public T getGrandparent() {

        if(this.getParent() != null && this.getParent().getParent() != null) {
            return this.getParent().getParent();
        }
        else {
            return null;
        }

    }

    // Uncle Getter
    public T getUncle() {

        T grandparent = getGrandparent();

        if(grandparent == null) {
            return null;
        }

        if(this.getParent() == grandparent.getRightChild()) {
            return grandparent.getLeftChild();
        }
        else {
            return grandparent.getRightChild();
        }

    }

    // Sibling Getter
    public T getSibling() {

        if(this == null || this.getParent() == null){
            return null;
        }

        if(this == this.getParent().getLeftChild()) {
            return this.getParent().getRightChild();
        }
        else {
            return this.getParent().getLeftChild();
        }

    }

    // Parent Getter
    public T getParent() {
        return this.parent;
    }

    // Leftchild Getter
    public T getLeftChild() {
        return this.leftChild;
    }

    // Rightchild Getter
    public T getRightChild() {
        return this.rightChild;
    }

    // Parent Setter
    public void setParent(T newParent) {
        this.parent = newParent;
    }

    // Leftchild Setter
    public void setLeftChild(T newLeftChild) {
        this.leftChild = newLeftChild;
    }

    // Rightchild Setter
    public void setRightChild(T newRightChild) {
        this.rightChild = newRightChild;
    }

}
