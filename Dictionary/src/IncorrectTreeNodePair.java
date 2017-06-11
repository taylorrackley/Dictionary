/**
 * Created by Taylor-PC on 6/10/2017.
 */
public class IncorrectTreeNodePair extends Exception {

    IncorrectTreeNodePair(SearchTree tree ) {

        super("Tree attempted to use an aspect of Node that is not enabled for that specific tree - "+tree.getClass());

    }
}
