import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HippodromeTest {
    @Test
    @DisplayName("Конструктор має кидати IllegalArgumentException, якщо список == null")
    void constructorShouldThrowExceptionWhenHorsesIsNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    @DisplayName("Конструктор має кидати IllegalArgumentException, якщо список порожній")
    void constructorShouldThrowExceptionWhenHorsesIsEmpty() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(Collections.emptyList()));
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    void getHorsesShouldReturnSameList() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            horses.add(new Horse("Horse" + i, i, i));
        }
        Hippodrome hippodrome = new Hippodrome(horses);

        assertEquals(horses, hippodrome.getHorses());
    }

    @Test
    void moveShouldCallMoveOnAllHorses() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Horse mockHorse = mock(Horse.class);
            horses.add(mockHorse);
        }

        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();

        for (Horse horse : horses) {
            verify(horse).move();
        }
    }

    @Test
    void getWinnerShouldReturnHorseWithMaxDistance() {
        Horse h1 = new Horse("A", 1.0, 10.0);
        Horse h2 = new Horse("B", 1.0, 20.0);
        Horse h3 = new Horse("C", 1.0, 15.0);

        Hippodrome hippodrome = new Hippodrome(List.of(h1, h2, h3));

        assertEquals(h2, hippodrome.getWinner());
    }
}
