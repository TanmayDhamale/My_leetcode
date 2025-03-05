import java.util.Arrays;

class SieveOfEratosthenes {
    public static void sieve(int n) {
        boolean[] primes = new boolean[n + 1];
        Arrays.fill(primes, true);  // Assume all numbers are prime initially
        primes[0] = primes[1] = false;  // 0 and 1 are not prime

        for (int i = 2; i * i <= n; i++) {  // Check up to sqrt(n)
            if (primes[i]) {  // If i is prime, mark its multiples
                for (int j = i * i; j <= n; j += i) {
                    primes[j] = false;
                }
            }
        }

        // Print all prime numbers
        System.out.println("Prime numbers up to " + n + ":");
        for (int i = 2; i <= n; i++) {
            if (primes[i]) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int n = 50;  // Find primes up to 50
        sieve(n);
    }
}