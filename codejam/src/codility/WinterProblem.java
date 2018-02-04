package codility;

/**
 * input : [5, -2, 3, 6, 8]
 * output : 3
 */

class WinterProblem {
    public static int solution(int[] T) {
        // write your code in Java SE 8
        // write your code in Java SE 8
        int leftMax[] = new int[T.length];
        leftMax[0] = T[0];

        System.out.print(leftMax[0] + ", ");
        for (int i = 1; i < T.length; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], T[i]);
            System.out.print(leftMax[i] + ", ");
        }

        System.out.println();
        int rightMin[] = new int[T.length];
        rightMin[T.length - 1] = T[T.length - 1];

        System.out.print(rightMin[T.length - 1] + ", ");
        for (int i = T.length - 2; i >= 0; i--) {
            rightMin[i] = Math.min(rightMin[i + 1], T[i]);
            System.out.print(rightMin[i] + ", ");
        }
        System.out.println();

        for (int i = 0; i < T.length - 1; i++) {
            if (leftMax[i] < rightMin[i+1])
                return T[i];
        }

        return -1;
    }


    public static void main(String[] args) {
        int T[] = {5, -2, 3, 8, 6};
        System.out.println(solution(T));
    }
}