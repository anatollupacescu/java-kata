package sedgewick;

import java.io.File;
import java.util.Optional;
import java.util.Scanner;

public class MyFileReader {

    public final Scanner in;

    public MyFileReader(String path) {
        try {
            in = new Scanner(new File(path));
        } catch (Exception e) {
            throw new RuntimeException("Could not create scanner for the file: " + path, e);
        }
    }

    public static Scanner scan(String path) {
        MyFileReader reader = new MyFileReader(path);
        return reader.in;
    }

    public static void main(String[] args) {
        String path = "tinyUF.txt";
        Optional.of(path).map(MyFileReader::scan).ifPresent(scanner -> {
            String ten = scanner.nextLine();
            System.out.println(ten);
            int i = scanner.nextInt();
            System.out.println(i);
        });
    }
}
