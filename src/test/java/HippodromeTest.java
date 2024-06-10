import org.apache.logging.log4j.core.tools.picocli.CommandLine;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.*;

public class HippodromeTest {
    @Test
    public void testConstructor_checkNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Hippodrome(null);
        });
        String expectedValue = "Horses cannot be null.";
        String actualValue = exception.getMessage();
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testConstructor_checkEmpty() {
        List<Horse> emptyList = Collections.emptyList();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Hippodrome(emptyList);
        });
        String expectedValue = "Horses cannot be empty.";
        String actualValue = exception.getMessage();
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testConstructor_getHorses() {
        List<Horse> expectedHorseList = List.of(
                new Horse("Bucephalus", 2.4),
                new Horse("Ace of Spades", 2.5),
                new Horse("Zephyr", 2.6),
                new Horse("Blaze", 2.7),
                new Horse("Lobster", 2.8),
                new Horse("Pegasus", 2.9),
                new Horse("Cherry", 3),
                new Horse("Bucephalus1", 2.4),
                new Horse("Ace of Spades1", 2.5),
                new Horse("Zephyr1", 2.6),
                new Horse("Blaze1", 2.7),
                new Horse("Lobster1", 2.8),
                new Horse("Pegasus1", 2.9),
                new Horse("Cherry1", 3),
                new Horse("Bucephalus2", 2.4),
                new Horse("Ace of Spades2", 2.5),
                new Horse("Zephyr2", 2.6),
                new Horse("Blaze2", 2.7),
                new Horse("Lobster2", 2.8),
                new Horse("Pegasus2", 2.9),
                new Horse("Cherry2", 3),
                new Horse("Bucephalus3", 2.4),
                new Horse("Ace of Spades3", 2.5),
                new Horse("Zephyr3", 2.6),
                new Horse("Blaze3", 2.7),
                new Horse("Lobster3", 2.8),
                new Horse("Pegasus3", 2.9),
                new Horse("Cherry3", 3),
                new Horse("Bucephalus4", 2.4),
                new Horse("Ace of Spades4", 2.5)
        );
        Hippodrome hippodrome = new Hippodrome(expectedHorseList);
        List<Horse> actualHorseList = hippodrome.getHorses();
        assertEquals(expectedHorseList, actualHorseList);
    }

    @Test
    public void testConstructor_testMove() {
        List<Horse> expectedHorseList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            expectedHorseList.add(mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(expectedHorseList);
        hippodrome.move();

        for (Horse horse : expectedHorseList) {
            verify(horse).move();
        }
    }

    @Test
    public void testConstructor_testWinner() {
        List<Horse> horseList = new ArrayList<>();
        Horse horse = new Horse("Horse", 10, 20);
        Horse expectedWinner = new Horse("Horse1", 5, 40);
        Horse horse2 = new Horse("Horse2", 6, 30);
        horseList.add(horse);
        horseList.add(expectedWinner);
        horseList.add(horse2);
        Hippodrome hippodrome = new Hippodrome(horseList);
        var actualWinner = hippodrome.getWinner();
        assertSame(expectedWinner, actualWinner);
    }

}


