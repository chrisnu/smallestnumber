package nl.west.chris.smallestnumber.service;

import nl.west.chris.smallestnumber.exception.InvalidNumberOrderException;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class SmallestNumberService {
    /**
     * Using mathematical LCM formula: |a| * ( |b| / gcd (a,b) ), and iterates from 2 to end against the LCM formula
     *
     * @param startNumber Start number
     * @param endNumber End number
     * @return Smallest multiple for a specific range in BigInteger
     */
    public BigInteger getSmallestNumber(int startNumber, int endNumber) {
        if (endNumber < startNumber) {
            throw new InvalidNumberOrderException(startNumber, endNumber);
        }

        BigInteger result = BigInteger.ONE;
        for (int i = startNumber; i <= endNumber; i++) {
            result = lcm(result, BigInteger.valueOf(i));
        }
        return result;
    }

    /**
     * Recursive method to compute the Greatest Common Divisor (GCD) using Euclid's algorithm
     * @param a BigInteger a
     * @param b BigInteger b
     * @return GCD in BigInteger
     */
    private BigInteger gcd(BigInteger a, BigInteger b) {
        return b.equals(BigInteger.ZERO) ? a : gcd(b, a.mod(b));
    }

    /**
     * Method to compute the Least Common Multiple (LCM) of two numbers, following  |a| * ( |b| / gcd (a,b) ) formula
     * @param a BigInteger a
     * @param b BigInteger b
     * @return LCM in BigInteger
     */
    private BigInteger lcm(BigInteger a, BigInteger b) {
        return a.multiply(b).divide(gcd(a, b));
    }

}
