import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

class HippodromeTest {

    @Test
    void constructorThroughNull() {
        List<Horse> horses = null;
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> new Hippodrome(horses));

        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    void constructorThroughEmptyList() {
        List<Horse> horses = new ArrayList<>();
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> new Hippodrome(horses));
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    void constructorListHorsesTrue() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("Name" + i, 2.0));
        }
        Hippodrome test = new Hippodrome(horses);
        assertEquals(horses, test.getHorses());
    }

    @Test
    void moveCallAllHorses() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(Mockito.mock(Horse.class));
        }
        Hippodrome test = new Hippodrome(horses);
        test.move();
        for (Horse horse : horses) {
            verify(horse).move();
        }
    }

    @Test
    void getWinner() {
        List<Horse> horses = new ArrayList<>();
        horses.add(new Horse("Name1", 2.0, 10));
        horses.add(new Horse("Name1", 2.0, 50));
        horses.add(new Horse("Name1", 2.0, 20));

        Hippodrome test = new Hippodrome(horses);

        assertSame(horses.get(1), test.getWinner());
    }
}