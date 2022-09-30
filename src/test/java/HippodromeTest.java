import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HippodromeTest {

    @Test
    void checkNullArgumentHippodrome() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }

    @Test
    void checkIllegalArgumentExceptionMessage() {
        String expectedMessage = "Horses cannot be null.";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new Hippodrome(null));

        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void checkConstructorEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(Collections.emptyList()));
    }

    @Test
    void checkMessageIfEmptyList() {
        String expectedMessage = "Horses cannot be empty.";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new Hippodrome(Collections.emptyList()));

        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void getHorse() {
        List<Horse> expectedList = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            expectedList.add(new Horse("Some_Horse_Name", 1, i));
        }

        Hippodrome hippodrome = new Hippodrome(expectedList);
        List<Horse> testHorseList = hippodrome.getHorses();
        assertEquals(expectedList, testHorseList);
    }

    @Test
    void getMoveHorse() {
        List<Horse> horseList = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            horseList.add(mock(Horse.class));
        }

        Hippodrome hippodrome = new Hippodrome(horseList);
        hippodrome.move();

        for (Horse horse : horseList) {
            verify(horse, atLeastOnce()).move();
        }
    }

}