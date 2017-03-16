package codewars;

import org.junit.Test;

import java.util.Scanner;

public class ScannerTest {

    public static void main(String [] ags) {
        Scanner consoleScanner = new Scanner(System.in);
        while(consoleScanner.hasNext()) {
            String line = consoleScanner.nextLine();
            String[] args = line.split(" ");
            int n = Integer.valueOf(args[0]);
            int p = Integer.valueOf(args[1]);
        }

    }

    @Test
    public void scanNumbers() {
    }
}
