package nl.west.chris.smallestnumber.service;

import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class SmallestNumberService {
    public BigInteger getSmallestNumber(int startNumber, int endNumber) {
        return mathematicalSolution(startNumber, endNumber);
    }

    /**
     * Iterate every multiplication of end number and check if it's divisible
     * @param start Start number
     * @param end End number
     * @return Least common multiple (LCM) in BigInteger
     */
    private BigInteger bruteforceSolution(int start, int end) {
        boolean found = false;
        BigInteger currentNumber = BigInteger.valueOf(end);
        BigInteger bigStart = BigInteger.valueOf(start);
        BigInteger bigEnd = BigInteger.valueOf(end);

        while (!found) {
            found = true;

            BigInteger divider = bigStart;
            while (!divider.equals(bigEnd)) {
                if (!currentNumber.mod(divider).equals(BigInteger.ZERO)) {
                    found = false;
                    currentNumber = currentNumber.add(bigEnd);
                    break;
                }
                divider = divider.add(BigInteger.ONE);
            }
        }

        return currentNumber;
    }

    /**
     * Using mathematical LCM formula: |a| * ( |b| / gcd (a,b) ), and iterates from 2 to end against the LCM formula
     *
     * @param start Start number
     * @param end End number
     * @return Smallest multiple for a specific range in BigInteger
     */
    private BigInteger mathematicalSolution(int start, int end) {
        BigInteger result = BigInteger.ONE;
        for (int i = start; i <= end; i++) {
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
    public BigInteger gcd(BigInteger a, BigInteger b) {
        return b.equals(BigInteger.ZERO) ? a : gcd(b, a.mod(b));
    }

    /**
     * Method to compute the Least Common Multiple (LCM) of two numbers, following  |a| * ( |b| / gcd (a,b) ) formula
     * @param a BigInteger a
     * @param b BigInteger b
     * @return LCM in BigInteger
     */
    public BigInteger lcm(BigInteger a, BigInteger b) {
        return a.multiply(b).divide(gcd(a, b));
    }

}
