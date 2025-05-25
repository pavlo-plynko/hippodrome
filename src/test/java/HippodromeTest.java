import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HippodromeTest {

    @Test
    public void checkIArgEThrowsInConstructorWithNull() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Hippodrome(null);
                }
        );
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    public void checkIArgEThrowsInConstructorWithEmptyString() {
        List<Horse> listHorse = new ArrayList<>();
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Hippodrome(listHorse);
                }
        );
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    public void checkReturnHorseList() {
        List<Horse> initialHorses = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            initialHorses.add(new Horse("Horse" + i, i * 10, 0));
        }
        Hippodrome hippodrome = new Hippodrome(initialHorses);

        List<Horse> returnedHorses = hippodrome.getHorses();

        assertEquals(initialHorses.size(), returnedHorses.size());

        for (int i = 0; i < initialHorses.size(); i++) {
            assertTrue(initialHorses.get(i) == returnedHorses.get(i));
        }
    }

    @Test
    public void checkCallMoveAllHorses() {
        List<Horse> mockHorses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            mockHorses.add(Mockito.mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(mockHorses);
        hippodrome.move();
        for (Horse horse : mockHorses) {
            Mockito.verify(horse).move();
        }
    }

    @ParameterizedTest
    @ValueSource(ints = { 100, 200, 300 })
    public void checkMaxHorseDistance(double distance) {
        List<Horse> initialHorses = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            initialHorses.add(new Horse("Horse" + i, i * 10, i));
        }
        Hippodrome hippodrome = new Hippodrome(initialHorses);
        Horse winHorse = hippodrome.getWinner();
        assertEquals(winHorse,initialHorses.get(4));
    }
}
