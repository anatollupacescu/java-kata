package codewars;

import java.util.List;

public class CharPermutations {

    public void permutate(String input, List<String> acc) {
        if (input.length() <= 1) {
            acc.add(input);
            return;
        }
        doPermutate("", input, acc);
    }

    private void doPermutate(String head, String tail, List<String> acc) {
        int length = tail.length();
        if (length == 2) {
            acc.add(head + tail.charAt(0) + tail.charAt(1));
            acc.add(head + tail.charAt(1) + tail.charAt(0));
        } else {
            for (int i = 0; i < length; i++) {
                String newHead = head + tail.charAt(i);
                String newTail = tail.substring(0, i) + tail.substring(i + 1);
                doPermutate(newHead, newTail, acc);
            }
        }
    }
}
