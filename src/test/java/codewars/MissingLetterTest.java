package codewars;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MissingLetterTest {

    @Test
    public void exampleTests() {
        assertEquals('e', findMissingLetter(new char[] { 'a','b','c','d','f' }));
        assertEquals('P', findMissingLetter(new char[] { 'O','Q','R','S' }));
    }

    public static char findMissingLetter(char[] array)
    {
        int i = (int)array[0];
        for(char c : array) {
            if((int)c != i) return (char)i;
            i ++;
        }
        throw new RuntimeException();
    }
}
