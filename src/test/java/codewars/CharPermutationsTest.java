package codewars;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CharPermutationsTest {

    private CharPermutations permutations;

    @Before
    public void setUp() throws Exception {
        this.permutations = new CharPermutations();
    }

    @Test
    public void test123() {
        List<String> output = new ArrayList<>();
        CharPermutations p = new CharPermutations();
        p.permutate("a", output);
        Iterator<String> iterator = output.iterator();
        assertEquals("a", iterator.next());
        String str = "123";
        output = new ArrayList<>();
        p.permutate(str, output);
        assertEquals(6, output.size());
        iterator = output.iterator();
        assertEquals("123", iterator.next());
        assertEquals("132", iterator.next());
    }
}