package pl.tanpadeusz.sieve;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SieveTests {
    private static PrimeService service;

    @BeforeAll
    public static void instantiateService() {
        SieveTests.service = new PrimeService();
    }

    @Test
    public void findPrimesWithBoundaryOfNullTest() {

    }

    @Test
    public void findPrimesWithNegativeBoundaryTest() {
        Integer boundary = -10;
        List<Integer> primes = SieveTests.service.findPrimes(boundary);
        assertEquals(0, primes.size());
    }

    @Test
    public void findPrimesWithBoundaryOf2Test() {
        Integer boundary = 2;
        List<Integer> primes = SieveTests.service.findPrimes(boundary);
        assertEquals(0, primes.size());
    }

    @Test
    public void findPrimesWithBoundaryOf10Test() {
        Integer boundary = 10;
        List<Integer> primes = SieveTests.service.findPrimes(boundary);
        assertEquals(4, primes.size());
        assertTrue(primes.contains(2));
        assertTrue(primes.contains(3));
        assertTrue(primes.contains(5));
        assertTrue(primes.contains(7));
    }

    @Test
    public void findPrimesWithBoundaryOf11Test() {
        Integer boundary = 11;
        List<Integer> primes = SieveTests.service.findPrimes(boundary);
        assertEquals(4, primes.size());
        assertTrue(primes.contains(2));
        assertTrue(primes.contains(3));
        assertTrue(primes.contains(5));
        assertTrue(primes.contains(7));
        assertFalse(primes.contains(11));
    }

    @Test
    public void assertAllArePrimesForLargeBoundaryTest() {
        Integer boundary = 1000;
        List<Integer> primes = SieveTests.service.findPrimes(boundary);
        for (int prime : primes) {
            assertTrue(this.isPrime(prime));
        }
    }

    private boolean isPrime(Integer value) {
        if (value < 2) return false;
        for (int divider = 2; divider < value; divider++) {
            if (value % divider == 0)
                return false;
        }
        return true;
    }
}
