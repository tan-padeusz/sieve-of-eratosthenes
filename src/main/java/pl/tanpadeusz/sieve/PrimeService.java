package pl.tanpadeusz.sieve;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PrimeService {
    public List<Integer> findPrimes(Integer boundary) {
        List<Integer> primes = new ArrayList<>();
        if (boundary <= 2) return primes;
        List<Boolean> sieve = this.createSieve(boundary);
        int boundaryRoot = (int) Math.sqrt(boundary);
        for (int index = 0; index <= boundaryRoot - 2; index++)
        {
            if (sieve.get(index) != null)
                continue;
            sieve.set(index, true);
            int prime = index + 2;
            primes.add(prime);
            this.multiplicityToFalse(sieve, prime);
        }
        for (int index = boundaryRoot - 1; index < boundary - 2; index++) {
            if (sieve.get(index) != null)
                continue;
            sieve.set(index, true);
            primes.add(index + 2);
        }
        return primes;
    }

    private List<Boolean> createSieve(Integer boundary) {
        List<Boolean> sieve = new ArrayList<>();
        for (int index = 0; index < boundary - 2; index++) {
            sieve.add(null);
        }
        return sieve;
    }

    private void multiplicityToFalse(List<Boolean> sieve, int prime) {
        for (int index = prime * prime - 2; index < sieve.size(); index += prime) {
            sieve.set(index, false);
        }
    }
}