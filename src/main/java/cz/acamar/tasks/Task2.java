package cz.acamar.tasks;

import static java.lang.Math.abs;

public class Task2 {

    /**
     * Task 2. Squares of a sorted array.
     * <p>
     * Given an integer array sorted in non-decreasing order,
     * return an array of the squares of each number sorted in non-decreasing order.
     * <p>
     * Example input: [-4,-1,0,3,10]
     * Expected output: [0,1,9,16,100]
     * <p>
     * Explanation: After squaring, the array becomes [16,1,0,9,100].
     * After sorting, it becomes [0,1,9,16,100].
     *
     * @param input - an integer array sorted in non-decreasing order
     * @return - an array of the squares of each number sorted in non-decreasing order
     */
    public int[] squaresOfSortedArray(int[] input) {
        int indexOfLastNegative = 0;
        for (int i = 0; i < input.length; i++) {
            if (input[i] < 0) indexOfLastNegative = i;
            else break;
        }
        int[] negatives = new int[indexOfLastNegative + 1];
        System.arraycopy(input, 0, negatives, 0, indexOfLastNegative + 1);
        int[] reversedNegatives = reverse(negatives);
        int[] positives = new int[input.length - negatives.length];
        System.arraycopy(input, negatives.length, positives, 0, input.length - negatives.length);
        int pos = 0;
        int[] result = new int[input.length];
        while ((positives.length > 0) && (reversedNegatives.length > 0)) {
            if (abs(reversedNegatives[0]) <= positives[0]) {
                result[pos] = reversedNegatives[0] * reversedNegatives[0];
                reversedNegatives = removeElement(reversedNegatives, 0);
                pos++;
                if (reversedNegatives.length == 0) {
                    System.arraycopy(positives, 0, result, pos, input.length - pos);
                    for (int i = pos; i < result.length; i++) {
                        result[i] = result[i] * result[i];
                    }
                }
            } else {
                result[pos] = positives[0] * positives[0];
                positives = removeElement(positives, 0);
                pos++;
                if (positives.length == 0) {
                    System.arraycopy(reversedNegatives, 0, result, pos, input.length - pos);
                    for (int i = pos; i < result.length; i++) {
                        result[i] = result[i] * result[i];
                    }
                }
            }
        }
        return result;
    }

    private int[] removeElement(int[] in, int pos) {
        if (pos < 0 || pos >= in.length) {
            throw new ArrayIndexOutOfBoundsException(pos);
        }
        int[] res = new int[in.length - 1];
        System.arraycopy(in, 0, res, 0, pos);
        if (pos < in.length - 1) {
            System.arraycopy(in, pos + 1, res, pos, in.length - pos - 1);
        }
        return res;
    }

    private int[] reverse(int[] in) {
        for (int i = 0; i < in.length / 2; i++) {
            int temp = in[i];
            in[i] = in[in.length - i - 1];
            in[in.length - i - 1] = temp;
        }
        return in;
    }
}
