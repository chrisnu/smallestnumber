package nl.west.chris.smallestnumber.service;

import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

@Service
public class SmallestNumberService {
    public BigInteger getSmallestNumber(int startNumber, int endNumber) {
        return mathematicalSolution(startNumber, endNumber);
    }

    /**
     * Solution 1: work for all unit tests, but slow performance
     * Iterate every multiplication of end number and check if it's divisible
     * @param start Start number
     * @param end End number
     * @return Least common multiple (LCM) in BigInteger
     */
    private BigInteger bruteforceSolution(int start, int end, BigInteger incremental) {
        boolean found = false;
        BigInteger currentNumber = incremental;
        BigInteger bigStart = BigInteger.valueOf(start);
        BigInteger bigEnd = BigInteger.valueOf(end);

        while (!found) {
            found = true;

            BigInteger divider = bigStart;
            while (!divider.equals(bigEnd)) {
                if (!currentNumber.mod(divider).equals(BigInteger.ZERO)) {
                    found = false;
                    currentNumber = currentNumber.add(incremental);
                    break;
                }
                divider = divider.add(BigInteger.ONE);
            }
        }

        return currentNumber;
    }

    /**
     * Solution 2: Works for all unit tests and good performance
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

    /**
     * Solution 3: Doesn't work on edge cases, pretty good performance.
     * Gather all integers from end to start, and divide lower dividable integers on the run.
     * After that multiply all gathered integers
     * @param start Start number
     * @param end End number
     * @return Smallest multiple for a specific range in BigInteger
     */
    private BigInteger reverseIterateAndBlacklistSolution(int start, int end) {
        Set<BigInteger> multiplyingIntegers = new HashSet<>();
        Set<Integer> dividingIntegers = new HashSet<>();
        int currentNumber = end;
        while ((multiplyingIntegers.size() + dividingIntegers.size()) < (end - start + 1)) {
            if (dividingIntegers.contains(currentNumber)) {
                continue;
            }

            // Collect any blacklisted numbers
            for (int i = start; i < currentNumber; i++) {
                if (currentNumber % i == 0) {
                    dividingIntegers.add(i);
                }
            }

            multiplyingIntegers.add(BigInteger.valueOf(currentNumber));
            currentNumber--;
        }

        BigInteger reducedTopMultiplication = multiplyingIntegers.stream()
                .reduce(BigInteger.ONE, BigInteger::multiply);
        BigInteger reducedBottomMultiplcation = dividingIntegers.stream()
                .map(BigInteger::valueOf)
                .reduce(BigInteger.ONE, BigInteger::multiply);

        BigInteger divide = reducedTopMultiplication.divide(reducedBottomMultiplcation);
        return bruteforceSolution(start, end, divide);
    }

}
