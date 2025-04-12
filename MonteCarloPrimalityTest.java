import java.math.BigInteger;
import java.util.Random;

public class MonteCarloPrimalityTest {
    public static boolean isProbablyPrime(int n, int k) {
        if (n <= 1) return false;
        if (n <= 3) return true;

        // Using Java's BigInteger for large number handling
        BigInteger bigN = BigInteger.valueOf(n);
        return bigN.isProbablePrime(k); // k: higher value → more accurate
    }

    public static void main(String[] args) {
        int number = 97;
        int k = 5; // number of iterations

        boolean result = isProbablyPrime(number, k);
        System.out.println(number + " is " + (result ? "probably prime" : "not prime"));
    }
}