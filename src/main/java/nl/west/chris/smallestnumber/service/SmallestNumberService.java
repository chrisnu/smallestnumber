package nl.west.chris.smallestnumber.service;

import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class SmallestNumberService {
    public BigInteger getSmallestNumber(int startNumber, int endNumber) {
        return bruteforce(startNumber, endNumber);
    }

    private BigInteger bruteforce(int start, int end) {
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
                    currentNumber = currentNumber.add(BigInteger.ONE);
                    break;
                }
                divider = divider.add(BigInteger.ONE);
            }
        }

        return currentNumber;
    }
}
