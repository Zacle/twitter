package filters;


import twitter4j.Status;

import java.util.ArrayList;
import java.util.List;

/**
 * A filter that represents the logical and of its children filters
 */
public class AndFilter implements Filter{
    private final Filter leftChild;
    private final Filter rightChild;

    public AndFilter(Filter leftChild, Filter rightChild) {
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    /**
     * A and filter matches when both of its children match
     * @param s     the tweet to check
     * @return      whether or not they match
     */
    @Override
    public boolean matches(Status s) {
        return leftChild.matches(s) && rightChild.matches(s);
    }

    @Override
    public List<String> terms() {
        List<String> t = new ArrayList<>(leftChild.terms());
        t.addAll(rightChild.terms());
        return t;
    }

    @Override
    public String toString() {
        return "(" + leftChild.toString() + " and " + rightChild.toString() + ")";
    }
}
