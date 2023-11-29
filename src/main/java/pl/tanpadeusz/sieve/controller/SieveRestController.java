package pl.tanpadeusz.sieve.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import pl.tanpadeusz.sieve.PrimeService;
import pl.tanpadeusz.sieve.exception.SieveException;

import java.util.List;

@Component
@RestController
@RequestMapping("/sieve")
public class SieveRestController {
    @Autowired
    private PrimeService service;

    @GetMapping
    public List<Integer> getPrimes(@RequestParam Integer boundary) throws SieveException {
        if (boundary == null)
            throw new SieveException("Given boundary is empty!");
        return this.service.findPrimes(boundary);
    }
}