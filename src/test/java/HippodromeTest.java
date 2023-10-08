import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HippodromeTest {

    @Test
    void constructorNullExceptionTest() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }

    @Test
    void constructorNullMessageTest() {
        try {
            new Hippodrome(null);
        } catch (IllegalArgumentException e) {
            assertEquals("Horses cannot be null.", e.getMessage());
        }
    }

    @Test
    void constructorEmptyExceptionTest() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
    }

    @Test
    void constructorEmptyMessageTest() {
        try {
            new Hippodrome(new ArrayList<>());
        } catch (IllegalArgumentException e) {
            assertEquals("Horses cannot be empty.", e.getMessage());
        }
    }

    @Test
    void getHorsesReturnTest() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            horses.add(new Horse("horse " + i, i));
        }
        assertIterableEquals(horses, new Hippodrome(horses).getHorses());
    }

    @Test
    @ExtendWith(MockitoExtension.class)
    void moveHorsesTest() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            horses.add(Mockito.mock(Horse.class));
        }
        new Hippodrome(horses).move();
        for (Horse horse : horses) {
            Mockito.verify(horse, Mockito.only()).move();
        }
        assertIterableEquals(horses, new Hippodrome(horses).getHorses());
    }

    @Test
    void getWinnerTest() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            horses.add(new Horse("horse " + i, i % 10));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        for (int i = 0; i < 10; i++) {
            hippodrome.move();
        }
        assertEquals(Collections.max(horses, (Horse h1, Horse h2) -> (int) (h1.getDistance() - h2.getDistance())), hippodrome.getWinner());
    }

}
