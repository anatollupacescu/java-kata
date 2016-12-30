package sedgewick.union;

import sedgewick.MyFileReader;

import java.util.Optional;
import java.util.Scanner;

public interface UnionFind {

    void union(int p, int q);

    boolean isConnected(int p, int q);

    default Scanner scanFile(String path) {
        return Optional.of(path).map(MyFileReader::scan).get();
    }
}
