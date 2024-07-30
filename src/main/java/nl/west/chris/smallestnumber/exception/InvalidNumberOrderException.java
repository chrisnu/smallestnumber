package nl.west.chris.smallestnumber.exception;

public class InvalidNumberOrderException extends RuntimeException {

    public InvalidNumberOrderException(int startNumber, int endNumber) {
        super(String.format("Invalid number order exception. startNumber (%d) should be lower than endNumbe (%d)r: ", startNumber, endNumber));
    }
}
