package nl.west.chris.smallestnumber.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class SmallestNumberServiceTest {

    @InjectMocks
    SmallestNumberService smallestNumberService;

    @Test
    void getSmallestNumber() {
        assertEquals("2520", String.valueOf(smallestNumberService.getSmallestNumber(1, 10)));
        assertEquals("360360", String.valueOf(smallestNumberService.getSmallestNumber(1, 15)));
        assertEquals("26771144400", String.valueOf(smallestNumberService.getSmallestNumber(1, 25)));
        assertEquals("26771144400", String.valueOf(smallestNumberService.getSmallestNumber(10, 25)));
    }
}