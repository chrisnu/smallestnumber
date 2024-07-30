package nl.west.chris.smallestnumber.service;

import org.springframework.stereotype.Service;

@Service
public class SmallestNumberService {
    public Integer getSmallestNumber(int startNumber, int endNumber) {
        return bruteforce(startNumber, endNumber);
    }

    private int bruteforce(int start, int end) {
        boolean found = false;
        int currentNumber = end;
        while (!found) {
            found = true;
            for (int i = start; i <= end; i++) {
                if (currentNumber % i != 0) {
                    found = false;
                    currentNumber++;
                    break;
                }
            }
        }

        return currentNumber;
    }
}
