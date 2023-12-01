package pl.tanpadeusz.sieve;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PrimeService {
    public List<Integer> findPrimes(Integer boundary) {
        List<Integer> primes = new ArrayList<>();
        if (boundary <= 2) return primes;
        List<PrimeState> sieve = this.createSieve(boundary);
        int boundaryRoot = (int) Math.sqrt(boundary);
        for (int index = 0; index <= boundaryRoot - 2; index++)
        {
            if (sieve.get(index) != PrimeState.UNKNOWN)
                continue;
            sieve.set(index, PrimeState.IS_PRIME);
            int prime = index + 2;
            primes.add(prime);
            this.multiplicityToNotPrime(sieve, prime);
        }
        for (int index = boundaryRoot - 1; index < boundary - 2; index++) {
            if (sieve.get(index) != PrimeState.UNKNOWN)
                continue;
            sieve.set(index, PrimeState.IS_PRIME);
            primes.add(index + 2);
        }
        return primes;
    }

    private List<PrimeState> createSieve(Integer boundary) {
        List<PrimeState> sieve = new ArrayList<>();
        for (int index = 0; index < boundary - 2; index++) {
            sieve.add(PrimeState.UNKNOWN);
        }
        return sieve;
    }

    private void multiplicityToNotPrime(List<PrimeState> sieve, int prime) {
        for (int index = prime * prime - 2; index < sieve.size(); index += prime) {
            sieve.set(index, PrimeState.IS_NOT_PRIME);
        }
    }
}