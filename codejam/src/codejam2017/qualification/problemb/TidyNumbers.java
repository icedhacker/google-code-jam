package codejam2017.qualification.problemb;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class TidyNumbers {
    Scanner in = new Scanner(getClass().getResourceAsStream(INPUT_FILE_NAME));

    static final String dir = System.getProperty("user.dir") + "/codejam/src/codejam2017/qualification/problemb/";
    static final String FILENAME = "tidy-numbers-large";
    static final String INPUT_FILE_NAME = FILENAME + ".in";
    static final String OUTPUT_FILE_NAME = dir + FILENAME + ".out";

    public static void main(String args[]) throws Exception {
        new TidyNumbers().run();
    }

    private void run() throws FileNotFoundException {
        PrintStream out = new PrintStream(new FileOutputStream(OUTPUT_FILE_NAME));
        int T = in.nextInt();
        for (int i = 0; i < T; i++) {
            long N = in.nextLong();
            String s = "Case #" + (i + 1) + ": " + getLastTidy(N);
            out.println(s);
            System.out.println(s);
        }
        in.close();
        out.close();
    }

    private static long getLastTidy(long N) {
        if (N / 10 == 0) {
            return N;
        }
        long initialVal = N;
        int prevRemainder = (int)(N % 10);
        N = N / 10;
        int currRemainder =  (int)(N % 10);
        int digitCount = 1;
        int maxDigitCount = 1;
        boolean isDone = true;
        long lastRight = N;
        while (N > 0) {
            if (prevRemainder < currRemainder || (!isDone && (prevRemainder == currRemainder) && (prevRemainder == 1))) {
                isDone = false;
                lastRight = N;
                if (digitCount > maxDigitCount) {
                    maxDigitCount = digitCount;
                }
            }
            prevRemainder = currRemainder;
            N = N / 10;
            currRemainder = (int)(N % 10);
            digitCount++;
        }
        if (isDone) {
            return initialVal;
        }
        lastRight--;
        for (int i = 0; i < maxDigitCount; i++) {
            lastRight = lastRight * 10 + 9;
        }
        return lastRight;
    }
}
