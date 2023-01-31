import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;

public class HippodromeTest {
    @Test
    void checkExceptionIfParameterIsNullInConstructor() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }

    @Test
    void checkExceptionMessageIfParameterIsNullInConstructor() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    void checkExceptionIfListIsVoid() {
        List<Horse> horses = new ArrayList<>();
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(horses));
    }

    @Test
    void checkExceptionMessageIfListIsVoid() {
        List<Horse> horses = new ArrayList<>();
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(horses));
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    void checkGetHorsesReturnHorses() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("HorseName" + i,
                    ThreadLocalRandom.current().nextDouble(100),
                    ThreadLocalRandom.current().nextDouble(100)));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses, hippodrome.getHorses());
    }

    @Test
    void checkRunMoveForEachHorse() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(Mockito.mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();
        for(Horse horse : horses) {
            Mockito.verify(horse).move();
        }
    }

    @Test
    void checkGetWinnerReturnHorseWithMaxDistance() {
        List<Horse> horses = new ArrayList<>();
        Horse horse1 = new Horse("Barsik", 50, 10.00);
        Horse horse2 = new Horse("Barsik", 50, 100.99);
        Horse horse3 = new Horse("Barsik", 50, 75.43);
        horses.add(horse1);
        horses.add(horse2);
        horses.add(horse3);
        Hippodrome hippodrome = new Hippodrome(horses);
        assertSame(horse2, hippodrome.getWinner());
    }
}
