package codewars;

import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.assertEquals;

public class ScrambleTest {

    private static class Scramblies {

        public static boolean scramble(String str1, String str2) {
            if(str2.length() > str1.length()) {
                return false;
            }
            LinkedList<Character> list = new LinkedList<>();
            for (char c : str1.toCharArray()) {
                list.add(c);
            }
            for (char c : str2.toCharArray()) {
                if (!list.remove((Character)c)) {
                    return false;
                }
            }
            return true;
        }
    }

    @Test
    public void test() {
        System.out.println("Fixed Tests scramble");
        testing(Scramblies.scramble("rkqodlw","world"), true);
        testing(Scramblies.scramble("cedewaraaossoqqyt","codewars"),true);
        testing(Scramblies.scramble("katas","steak"),false);
        testing(Scramblies.scramble("scriptjav","javascript"),false);
        testing(Scramblies.scramble("scriptingjava","javascript"),true);
        testing(Scramblies.scramble("scriptsjava","javascripts"),true);
        testing(Scramblies.scramble("javscripts","javascript"),false);
        testing(Scramblies.scramble("aabbcamaomsccdd","commas"),true);
        testing(Scramblies.scramble("commas","commas"),true);
        testing(Scramblies.scramble("sammoc","commas"),true);
    }
    private static void testing(boolean actual, boolean expected) {
        assertEquals(expected, actual);
    }
}
