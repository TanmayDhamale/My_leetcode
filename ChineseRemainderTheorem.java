public class ChineseRemainderTheorem {

    // Function to find modulo inverse of a under modulo m
    static int modInverse(int a, int m) {
        a = a % m;
        for (int x = 1; x < m; x++) {
            if ((a * x) % m == 1)
                return x;
        }
        return 1; // default (shouldn't reach if input is valid)
    }

    // Function to implement Chinese Remainder Theorem
    static int findMinX(int[] num, int[] rem, int k) {
        int prod = 1; // product of all num[i]
        for (int i = 0; i < k; i++)
            prod *= num[i];

        int result = 0;

        for (int i = 0; i < k; i++) {
            int pp = prod / num[i];
            result += rem[i] * modInverse(pp, num[i]) * pp;
        }

        return result % prod;
    }

    public static void main(String[] args) {
        int[] num = {3, 4, 5};      // moduli (pairwise coprime)
        int[] rem = {2, 3, 1};      // remainders
        int k = num.length;

        System.out.println("x is " + findMinX(num, rem, k));
    }
}