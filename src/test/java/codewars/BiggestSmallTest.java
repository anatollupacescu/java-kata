package codewars;

import com.google.common.base.Joiner;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class BiggestSmallTest {

//    int[] arr = {1, 2, 3, 4};
//    for(int i = 0; i < arr.length; i++) {

    @Test
    public void wip() {
        int[] arr = {1, 2, 3};
        int groups = 3;
        List<String> output = new ArrayList<>();
        output.add("()()(1 2 3)");
        output.add("()(1)(2 3)");
        output.add("()(1 2)(3)");
        output.add("()(1 2 3)()");
        output.add("(1)()(2 3)");
        output.add("(1)(2)(3)");
        output.add("(1)(2 3)()");
        output.add("(1 2)()(3)");
        output.add("(1 2)(3)()");
        output.add("(1 2 3)()()");
        Assert.assertEquals(output, getMinimalLargeSum(arr, groups));
    }

    @Test
    public void wip2Groups() {
        int[] arr = {1, 2, 3};
        int groups = 2;
        List<String> output = new ArrayList<>();
        output.add("()(1 2 3)");
        output.add("(1)(2 3)");
        output.add("(1 2)(3)");
        output.add("(1 2 3)()");
        Assert.assertEquals(output, getMinimalLargeSum(arr, groups));
    }

    private List<String> getMinimalLargeSum(int[] arr, int groups) {
        return getMinimalLargeSum(arr, groups, 0, "", new ArrayList<>());
    }

    private List<String> getMinimalLargeSum(int[] arr, int groups, int start, String head, List<String> strings) {
        if(groups == 2) {
            for (int i = start; i <= arr.length; i++) {
                String st = head;
                st += commaSeparated(arr, start, i);
                st += commaSeparated(arr, i);
                strings.add(st);
            }
        } else {
            groups--;
            for (int i = start; i <= arr.length; i++) {
                String nHead = commaSeparated(arr, start, i);
                getMinimalLargeSum(arr, groups, i, nHead, strings);
            }
        }
        return strings;
    }

    private String commaSeparated(int[] arr, int i) {
        return commaSeparated(arr, i, arr.length);
    }

    private String commaSeparated(int[] arr, int start, int i) {
        int len = i - start;
        if (len < 1) return "()";
        String[] parts = new String[len];
        for (int j = 0; j < len; j++) {
            parts[j] = String.valueOf(arr[start + j]);
        }
        return "(" + Joiner.on(" ").join(parts) + ")";
    }
}
