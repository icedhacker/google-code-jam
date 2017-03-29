package codejam2016.qualification.problemb;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class RevengeOfPancakes {
    Scanner in = new Scanner(getClass().getResourceAsStream(INPUT_FILE_NAME));

    static final String dir = System.getProperty("user.dir") + "/codejam/src/codejam2016/qualification/problemb/";
    static final String FILENAME = "revenge-pancakes-large";
    static final String INPUT_FILE_NAME = FILENAME + ".in";
    static final String OUTPUT_FILE_NAME = dir + FILENAME + ".out";

    public static void main(String args[]) throws Exception {
        new RevengeOfPancakes().run();
    }

    private void run() throws Exception {
        PrintStream out = new PrintStream(new FileOutputStream(OUTPUT_FILE_NAME));
        int T = in.nextInt();
        in.nextLine();
        for (int i = 0; i < T; i++) {
            String pancakes = in.nextLine();
            String s = "Case #" + (i + 1) + ": " + countFlips(pancakes);
            out.println(s);
            System.out.println(s);
        }
        in.close();
        out.close();
    }

    private static int countSubString(String str, String subStr) {
        int lastIndex = 0;
        int count = 0;
        while (lastIndex < str.length() && lastIndex != -1) {
            lastIndex = str.indexOf(subStr, lastIndex);
            if (lastIndex != -1) {
                count++;
                lastIndex = lastIndex + subStr.length();
            }
        }
        return count;
    }

    private static int countFlips(String pancakes) {
        int count = 1 + countSubString(pancakes, "+-") + countSubString(pancakes, "-+");
        if (pancakes.charAt(pancakes.length() - 1) == '-') {
            return count;
        } else {
            return count - 1;
        }
    }
}
