import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Taylor-PC on 6/7/2017.
 */
public class Dictionary {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        SearchTree tree;

        if(args.length < 1) {
            System.out.println("A search tree must be specified when starting this program.\nAvailable options: BST, RB, Splay, AVL");
            return;
        }

        switch(args[0].toLowerCase()) {
            case "bst":
                tree = new BinarySearchTree();
                System.out.println("Binary Search Tree created");
                break;
            case "rb":
                tree = new RedBlackTree();
                System.out.println("Red Black Tree created");
                break;
            case "splay":
                tree = new SplayTree();
                System.out.println("Splay tree created");
                break;
            case "avl":
                tree = new AVLTree();
                System.out.println("AVL tree created");
                break;
            default:
                System.out.println("Invalid tree given.");
                return;
        }

        for(int x = 1; x < args.length; x++)
            loadCommandFileIntoTree(tree, args[x]);

        System.out.println("Enter \"Help\" for list of available commands.");

        while(true)
            runCommandOnTree(tree, input.nextLine());

    }

    private static void loadCommandFileIntoTree(SearchTree tree, String fileName) {

        Scanner fileReader;

        try {
            fileReader = new Scanner(new File(fileName));

            while(fileReader.hasNextLine())
                runCommandOnTree(tree, fileReader.nextLine());
            fileReader.close();
        } catch(FileNotFoundException e) {
            System.out.print(e.getMessage());
            System.out.println("\nEnter file name again:");
        }

    }

    private static void runCommandOnTree(SearchTree tree, String input) {

        if(input.equals(""))
            return;

        input = input.trim();

        if(input.toLowerCase().equals("quit")) {
            System.out.println("Closing Program...");
            System.exit(0);
        }

        String[] command = input.split(" ", 3);

        for(int x = 0; x < command.length; x++)
            command[x] = command[x].trim();

        // If the second element is empty then needless whitespace was entered
        // the first word of the third element is moved to the second element
        if(command.length == 3 && command[1].equals("")) {
            String[] temp = command[2].trim().split(" ", 2);
            if(temp.length == 2) {
                temp[1] = temp[1].trim();
                command[1] = temp[0];
                command[2] = temp[1];
                temp = null;
            } else
                command = new String[]{command[0], temp[0]};
        }

        switch(command[0].toLowerCase()) {

            case "add":
                if(command.length == 3 && !command[1].equals(""))
                    tree.insertWord(command);
                else
                    System.out.println("Incorrect syntax used.\nCorrect syntax: Add <word> <definition>");
                break;

            case "find":
                if(command.length == 2 ) {
                    Node node = tree.findWord(command[1]);
                    if(node != null) {
                        System.out.println('"'+command[1]+"\" definitions:");
                        for(int x = 0; x < node.definitions.length; x++)
                            System.out.println("    "+node.definitions[x]);
                    } else
                        System.out.println('"'+command[1]+'"'+" does not exist in this dictionary.");
                } else
                    System.out.println("Incorrect syntax used.\nCorrect syntax: Find <word>");
                break;

            case "tree":
                if(command.length == 1)
                    tree.printTree(tree.root, 0);
                else
                    System.out.println("Incorrect syntax used.\nCorrect syntax: Tree");
                break;

            case "delete":
                if(command.length == 2)
                    tree.deleteWord(command);
                else
                    System.out.println("Incorrect syntax used.\nCorrect syntax: Delete <word>");
                break;

            case "list":
                if(command.length != 2)
                    tree.list(command);
                else
                    System.out.println("Incorrect syntax used.\nCorrect syntax: List\nCorrect syntax: List <begin word> <end word>");
                break;

            case "load":
                loadCommandFileIntoTree(tree, command[1]);
                break;
            case "help":
                System.out.println("To add a word to the dictionary:\nAdd <word> <defintion>\n\n"
                        + "To find a word in the dictionary:\nFind <word>\n\n"
                        + "To list the dictionary in alphabetical order:\nList\n\n"
                        + "To list only a section of the dictionary:\nList <begin word> <end word>\n\n"
                        + "To load a command file:\nLoad <file name>\n\n"
                        + "To exit the program:\nQuit\n\n"
                        + "To print entire tree:\nTree\n\n"
                        + "To delete a word and it's defintions from the dictionary:\nDelete <word>\n\n");
                break;

            default:
                System.out.println("Incorrect command given: \"" + input+ '"');
                System.out.println("Enter \"Help\" for list of available commands.");
                break;

        }

    }

}
