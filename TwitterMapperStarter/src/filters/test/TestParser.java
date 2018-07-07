package filters.test;

import filters.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test the parser.
 */
public class TestParser {
    @Test
    public void testBasic() throws SyntaxError {
        Filter f = new Parser("trump").parse();
        assertTrue(f instanceof BasicFilter);
        assertTrue(((BasicFilter)f).getWord().equals("trump"));
    }

    @Test
    public void testAnd() throws SyntaxError {
        Filter f = new Parser("trump and obama").parse();
        assertTrue(f instanceof AndFilter);
        assertTrue(((AndFilter)f).toString().equals("(trump and obama)"));
        Filter g = new Parser("trump and obama and bush").parse();
        assertTrue(g instanceof AndFilter);
        assertTrue(((AndFilter)g).toString().equals("((trump and obama) and bush)"));
    }

    @Test
    public void testOr() throws SyntaxError {
        Filter f = new Parser("trump or obama").parse();
        assertTrue(f instanceof OrFilter);
        assertTrue(((OrFilter)f).toString().equals("(trump or obama)"));
        Filter g = new Parser("trump or obama or bush").parse();
        assertTrue(g instanceof OrFilter);
        assertTrue(((OrFilter)g).toString().equals("((trump or obama) or bush)"));
    }

    @Test
    public void testAll() throws SyntaxError {
        Filter f = new Parser("blue or green and not red or yellow and purple").parse();
        assertTrue(f instanceof OrFilter);
        assertTrue(((OrFilter)f).toString().equals("((blue or (green and not red)) or (yellow and purple))"));
    }

    @Test
    public void testHairy() throws SyntaxError {
        Filter x = new Parser("trump and (evil or blue) and red or green and not not purple").parse();
        assertTrue(x.toString().equals("(((trump and (evil or blue)) and red) or (green and not not purple))"));
    }
}
