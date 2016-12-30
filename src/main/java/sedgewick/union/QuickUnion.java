package sedgewick.union;

import com.google.common.base.Joiner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.BiConsumer;
import java.util.function.IntFunction;
import java.util.stream.Collectors;

public class QuickUnion implements UnionFind {

    private int[] id;

    public void init(int size) {
        id = new int[size];
        for (int i = 0; i < size; i++) {
            id[i] = i;
        }
    }

    private int findRoot(int n) {
        while (id[n] != n) {
            n = id[n];
        }
        return n;
    }

    @Override
    public void union(int p, int q) {
        int head = findRoot(p);
        int root = findRoot(q);
        id[head] = root;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return findRoot(q) == findRoot(q);
    }

    public static void main(String[] args) {
        QuickUnion quickUnion = new QuickUnion();
        Scanner scanner = quickUnion.scanFile("tinyUF.txt");
        quickUnion.init(scanner.nextInt());
        while (scanner.hasNext()) {
            int p = scanner.nextInt();
            int q = scanner.nextInt();
            if (!quickUnion.isConnected(p, q)) {
                quickUnion.union(p, q);
                System.out.println(p + " " + q);
                System.out.println("testing:" + quickUnion);
            } else {
                System.out.println("skipping...");
            }
        }
        System.out.println("isConnected 7 and 8: " + quickUnion.isConnected(7, 8));
        System.out.println("isConnected 8 and 9: " + quickUnion.isConnected(8, 9));
    }

    public String toString() {
        final List<String> output = new ArrayList<>();
        Arrays.stream(id).mapToObj(String::valueOf).forEach(s -> output.add(s));
        return Joiner.on(" ").join(output);
    }
}
