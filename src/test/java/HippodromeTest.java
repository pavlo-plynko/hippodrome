import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


public class HippodromeTest {

    @Test
    public void testIsNull() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {new Hippodrome(null);});
        Assertions.assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    public void testEmptyList() {
        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new Hippodrome(Collections.emptyList())
        );
        Assertions.assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    public void testGetHorses() {
        List<Horse> originalHorses = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            originalHorses.add(new Horse("Horse " + i, i, i * 10));
        }
        Hippodrome hippodrome = new Hippodrome(originalHorses);
        List<Horse> horsesFromGetter = hippodrome.getHorses();
        Assertions.assertEquals(originalHorses.size(), horsesFromGetter.size(), "The size of the lists does not match");
        for (int i = 0; i < originalHorses.size(); i++) {
            Assertions.assertSame(originalHorses.get(i), horsesFromGetter.get(i),
                    "Element in position " + i + " different");
        }
    }
    @Test
    public void testMove() {
        List<Horse> horseMocks = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horseMocks.add(mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(horseMocks);
        hippodrome.move();
        for (Horse horse : horseMocks) {
            verify(horse).move();
        }
    }

    @Test
    public void testGetWinner() {
        Horse horse1 = new Horse("Test1", 3.0, 100.0);
        Horse horse2 = new Horse("Test2", 3.5, 150.0);
        Horse horse3 = new Horse("Test3", 4.0, 200.0);
        Horse horse4 = new Horse("Test4", 3.2, 180.0);
        List<Horse> horses = List.of(horse1, horse2, horse3, horse4);
        Hippodrome hippodrome = new Hippodrome(horses);
        Horse winner = hippodrome.getWinner();
        Assertions.assertEquals(horse3, winner);
    }
}
