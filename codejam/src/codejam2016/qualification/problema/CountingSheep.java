package codejam2016.qualification.problema;

import java.io.*;
import java.util.*;

public class CountingSheep {
    static final String FILENAME = "C:\\Users\\sanath\\Downloads\\codejam\\2016\\qualifications\\counting-sheep-large";
    static final String INPUT_FILE_NAME = FILENAME + ".in";
    static final String OUTPUT_FILE_NAME = FILENAME + ".out";

    public static void main(String args[]) throws Exception {
        Scanner in = new Scanner(new BufferedReader(new FileReader(INPUT_FILE_NAME)));
        PrintStream out = new PrintStream(new FileOutputStream(OUTPUT_FILE_NAME));

        int T = in.nextInt();
        for (int i = 0; i < T; i++) {
            int N = in.nextInt();
            String s = "Case #" + (i + 1) + ": " + countSheep(N);
            out.println(s);
            System.out.println(s);
        }
        out.close();
    }

    private static Set<Integer> findAllDigits(int N) {
        Set<Integer> digitSet = new HashSet<>();
        while (N > 0) {
            digitSet.add(N % 10);
            N = N / 10;
        }
        return digitSet;
    }

    private static int findNoOfDigits(int N) {
        int count = 0;
        while (N > 0) {
            count++;
            N = N / 10;
        }
        return count;
    }

    private static String countSheep(int N) {
        if (N == 0) {
            return "INSOMNIA";
        }
        Set<Integer> digitSet = findAllDigits(N);
        int newVal = N;

        /**
         * Consider the next highest power of 10 to N is P. If N = 1692, then P = 10000
         * Once the newVal crosses P, the first digit in the number will have all 9 values till 9P.
         * So, we consider the high limit for INSOMNIA case to be 9P.
         */
        int noOfDigits = findNoOfDigits(N);
        int highLimit = 9 * (int)Math.pow(10, noOfDigits);
        while (digitSet.size() < 10 && newVal <= highLimit) {
            newVal = newVal + N;
            digitSet.addAll(findAllDigits(newVal));
        }
        return String.valueOf(newVal);
    }
}
