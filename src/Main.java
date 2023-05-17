import java.util.*;

public class Main {

    static int[] nimResults;

    public static void main(String[] args) {

        //Number of iterations
        int numberOfIterations = 100000;

        //Create an array for results
        nimResults = new int[numberOfIterations];

        //Iterate over given number of iterations to fill nim result array.
        for (int i = 0; i < numberOfIterations; i++) {
            nimResults[i] = calculateMex(calculateNim(i));
        }

        //Print calculated results
        printNimResults();
    }

    static List<Integer> calculateNim(int index) {
        List<Integer> mexList = new ArrayList<>();

        // Rule 1- remove 1: you can remove 1 when only no pile left after removal.
        // So when number is 1, 1-1 -> mex{0} should be added.
        if (index == 1) {
            mexList.add(nimResults[index - 1]);
        }

        // Rule 2- remove 2: you can remove 2 when only a pile left after removal.
        // So, it should add to mexList the mex{index-2}
        if (index > 2) {
            mexList.add(nimResults[index - 2]);
        }

        // Rule 3- remove 3: you can remove 3 when a pile, two piles or three piles left.
        if (index >= 3) {
            mexList.add(nimResults[index - 3]);

            int divided = (index-3) / 2;
            int maxOfI = Math.min(divided, (index-3) - divided);

            for (int i = 1; i <= maxOfI; i++) {
                int complement = (index-3) - i;
                mexList.add(nimResults[i] ^ nimResults[complement]);
            }
        }
        return mexList;
    }

    static int calculateMex(List<Integer> mexList) {
        // Collections.sort(mexList);
        // a boolean array is created to mark used values
        boolean[] used = new boolean[mexList.size() + 1];
        for (int number : mexList) {
            if (number < used.length) {
                used[number] = true;
            }
        }

        // Find the first unused value on used array, it will be our mex value.
        int mex = 0;
        while (mex < used.length && used[mex]) {
            mex++;
        }

        return mex;
    }

    static void printNimResults() {
        for (int i = 0; i < nimResults.length; i++) {
            System.out.println(i + " = " + nimResults[i]);
        }
    }
}
