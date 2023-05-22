import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HippodromeTest {

    @Test
    public void testConstructorListHorsesNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Hippodrome(null);
        });
    }

    @Test
    public void testConstructorWithListHorsesNullCheckMessage() {
        try {
            new Hippodrome(null);
        } catch (IllegalArgumentException e) {
            assertEquals("Horses cannot be null.", e.getMessage());
        }
    }

    @Test
    public void testConstructorWithListHorsesEmptyList() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Hippodrome(new ArrayList<>());
        });
    }

    @Test
    public void testConstructorWithListHorsesEmptyListCheckMessage() {
        try {
            new Hippodrome(new ArrayList<>());
        } catch (IllegalArgumentException e) {
            assertEquals("Horses cannot be empty.", e.getMessage());
        }
    }

    @Test
    void testGetHorses() {
        List<Horse> horses = new ArrayList<>();
        List<String> expectedHorseName = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse(String.valueOf(i + 1), 25, 10));
            expectedHorseName.add(String.valueOf(i + 1));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        List<Horse> actualListHorse = hippodrome.getHorses();
        List<String> actualHorseName = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            actualHorseName.add(actualListHorse.get(i).getName());
        }
        assertEquals(expectedHorseName, actualHorseName);
    }

    @Test
    void testMove() {
        List<Horse> horseList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horseList.add(Mockito.mock(Horse.class));
        }

        Hippodrome hippodrome = new Hippodrome(horseList);
        hippodrome.move();
        for (int i = 0; i < 50; i++) {
            Mockito.verify(horseList.get(i)).move();
        }
    }

    @Test
    void testGetWinner() {
        List<Horse> horses = new ArrayList<>();
        horses.add(new Horse("1", 10, 20));
        horses.add(new Horse("2", 15, 15));
        horses.add(new Horse("3", 20, 10));

        Hippodrome hippodrome = new Hippodrome(horses);

        Horse actualHorse = hippodrome.getWinner();

        assertEquals("1", actualHorse.getName());
    }
}