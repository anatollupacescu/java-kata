package sedgewick.union;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class QuickFindUF implements UnionFind {

    private int[] id;

    public void init(int n) {
        this.id = new int[n];
        for (int i = 0; i < n; i++) {
           id[i]  = i;
        }
    }

    public void union(int p, int q) {
        if(!isConnected(p, q)) {
            int match = id[p];
            int overwrite = id[q];
            for (int i = 0; i < id.length; i++) {
                if(id[i] == match) {
                    id[i] = overwrite;
                }
            }
        }
    }

    public boolean isConnected(int p, int q) {
        return id[p] == id[q];
    }

    public static void main(String args[]) {
        QuickFindUF uf = new QuickFindUF();
        Scanner in = uf.scanFile("tinyUF.txt");
        int size = in.nextInt();
        uf.init(size);
        while(in.hasNext()) {
            int p = in.nextInt();
            int q = in.nextInt();
            if(!uf.isConnected(p, q)) {
                uf.union(p, q);
                System.out.println(p + " " + q);
            } else {
                System.out.println("skipping...");
            }
        }
        System.out.println("testing:" + uf);
        System.out.println("isConnected 7 and 8: " + uf.isConnected(7,8));
        System.out.println("isConnected 8 and 9: " + uf.isConnected(8,9));
    }

    public String toString() {
        final List<String> output = new ArrayList<>();
        Arrays.stream(id).boxed().forEach(s -> output.add(String.valueOf(s)));
        return output.toString();
    }
}
