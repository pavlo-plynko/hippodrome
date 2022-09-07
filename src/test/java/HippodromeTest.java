import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HippodromeTest {

    @Test
    public void createHippodromeWithNullHorses() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }

    @Test
    public void createHippodromeWithNullHorsesAndCheckExceptionMessage() {
        try {
            new Hippodrome(null);
        } catch (Exception e) {
            Assertions.assertEquals("Horses cannot be null.", e.getMessage());
        }
    }

    @Test
    public void createHippodromeWithEmptyHorses() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Hippodrome(Collections.emptyList()));
    }

    @Test
    public void createHippodromeWithEmptyHorsesAndCheckExceptionMessage() {
        try {
            new Hippodrome(Collections.emptyList());
        } catch (Exception e) {
            Assertions.assertEquals("Horses cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void checkHippodromeGetHorses() {
        List<Horse> horseList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horseList.add(new Horse(String.valueOf(i), 2.0));
        }
        Hippodrome hippodrome = new Hippodrome(new ArrayList<>(horseList));
        List<Horse> expected = hippodrome.getHorses();
        Assertions.assertEquals(expected, horseList);
    }

    @Test
    public void checkHippodromeMoveHorses() {
        List<Horse> horseList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horseList.add(Mockito.mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(horseList);
        hippodrome.move();
        for (Horse horse : horseList) {
            Mockito.verify(horse, Mockito.times(1)).move();
        }
    }

    @Test
    public void checkGetWinnerHorse() {
        List<Horse> horseList = List.of(
                new Horse("1", 1.0, 1.0),
                new Horse("2", 1.0, 10.0),
                new Horse("3", 1.0, 3.0));
        Hippodrome hippodrome = new Hippodrome(horseList);
        Horse winner = hippodrome.getWinner();
        Assertions.assertEquals(horseList.get(1), winner);
    }
}
