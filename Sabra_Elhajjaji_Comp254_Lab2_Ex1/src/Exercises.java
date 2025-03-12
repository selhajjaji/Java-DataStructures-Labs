public class Exercises {


        /** Returns the sum of the integers in given array. */
        public static int example1(int[] arr) {
            int n = arr.length, total = 0;
            for (int j=0; j < n; j++)       // loop runs n times
                total += arr[j];              // O(1) operation per iteration
            return total;
        }
        // Time Complexity: O(n)
        // Explanation: A single loop iterates over the array of size n, making the runtime linear.

        /** Returns the sum of the integers with even index in given array. */
        public static int example2(int[] arr) {
            int n = arr.length, total = 0;
            for (int j=0; j < n; j += 2)    // loop runs n/2 times
                total += arr[j];              // O(1) operation per iteration
            return total;
        }
        // Time Complexity: O(n)
        // Explanation: The loop increments by 2 each time, so it runs n/2 times.
        // Since constants are ignored in Big-O notation, the complexity remains O(n).

        /** Returns the sum of the prefix sums of given array. */
        public static int example3(int[] arr) {
            int n = arr.length, total = 0;
            for (int j=0; j < n; j++)       // outer loop runs n times
                for (int k=0; k <= j; k++)    // inner loop runs (j+1) times
                    total += arr[j];            // O(1) operation per iteration
            return total;
        }
        // Time Complexity: O(n^2)
        // Explanation: The outer loop runs n times, and for each j, the inner loop runs (j+1) times.
        // This results in a total of 1 + 2 + 3 + ... + n = (n(n+1))/2 operations, which simplifies to O(n^2).

        /** Returns the sum of the prefix sums of given array. */
        public static int example4(int[] arr) {
            int n = arr.length, prefix = 0, total = 0;
            for (int j=0; j < n; j++) {     // loop runs n times
                prefix += arr[j];             // O(1) operation
                total += prefix;              // O(1) operation
            }
            return total;
        }
        // Time Complexity: O(n)
        // Explanation: There is a single loop that iterates n times, making the complexity O(n).

        /** Returns the number of times second array stores sum of prefix sums from first. */
        public static int example5(int[] first, int[] second) { // assume equal-length arrays
            int n = first.length, count = 0;
            for (int i=0; i < n; i++) {     // loop runs n times
                int total = 0;
                for (int j=0; j < n; j++)     // loop runs n times
                    for (int k=0; k <= j; k++)  // loop runs (j+1) times
                        total += first[k];        // O(1) operation per iteration
                if (second[i] == total) count++;
            }
            return count;
        }
        // Time Complexity: O(n^3)
        // Explanation: The outermost loop runs n times, the second loop runs n times,
        // and the innermost loop runs (j+1) times, leading to a total of O(n^3).


}
