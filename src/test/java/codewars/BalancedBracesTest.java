package codewars;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

import static org.junit.Assert.assertArrayEquals;

public class BalancedBracesTest {

    static String[] braces(String[] values) {
        String[] result = new String[values.length];
        int i = 0;
        for(String value : values) {
            result[i++] = checkBalance(value);
        }
        return result;
    }

    private static String checkBalance(String value) {
        LinkedList<String> braces = new LinkedList<>();
        for(char c : value.toCharArray()) {
            String peek = braces.peek();
            if(peek != null) {
                String closingBrace = invert(peek);
                if (String.valueOf(c).equals(closingBrace)) {
                    braces.poll();
                } else {
                    braces.addFirst(new String(new char[]{c}));
                }
            } else {
                braces.add(new String(new char[]{c}));
            }
        }
        return braces.size() == 0 ? "YES" : "NO";
    }

    private static String invert(String peek) {
        switch (peek) {
            case "{": return "}";
            case "(": return ")";
            case "[": return "]";
        }
        return null;
    }

    @Test
    public void test6() {
        assertArrayEquals(new String[] { "YES"}, braces(new String[] { "{}()[]" }));
        assertArrayEquals(new String[] { "NO"},  braces(new String[] { "{}[(])" }));
        assertArrayEquals(new String[] { "YES"}, braces(new String[] { "{[()]}" }));
    }

}
