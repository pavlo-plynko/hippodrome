import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class HippodromeTest {

    @Test
    void constructor_ShouldThrowIllegalArgumentException_WhenArgumentIsNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        String expectedMessage = "Horses cannot be null.";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    void constructor_ShouldThrowIllegalArgumentException_WhenArgumentIsEmpty() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
        String expectedMessage = "Horses cannot be empty.";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void getHorses() {
        List<Horse> horses = new ArrayList<>();

        int horseCount = 30;
        for (int i = 0; i < horseCount; i++) {
            String horseName = "Horse " + (i + 1);
            horses.add(new Horse(horseName, i + 1, i + 2));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        List<Horse> actualHorses = hippodrome.getHorses();
        assertEquals(horses, actualHorses);
    }

    @Test
    void move() {
        List<Horse> horses = new ArrayList<>();
        Horse horse = Mockito.mock(Horse.class);
        int countMockHorses = 50;
        for (int i = 0; i < countMockHorses; i++) {
            horses.add(horse);
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();
        Mockito.verify(horse, Mockito.times(countMockHorses)).move();
    }

    @Test
    void getWinner_ShouldReturnHorseWithMaxDistance() {

        List<Horse> horses = new ArrayList<>();
        horses.add(new Horse("Horse 1", 1, 10));
        horses.add(new Horse("Horse 2", 4, 7));
        horses.add(new Horse("Horse 3", 2, 9));
        horses.add(new Horse("Horse 4", 1, 4));
        horses.add(new Horse("Horse 5", 3, 2));
        Hippodrome hippodrome = new Hippodrome(horses);
        Horse horseWinner = hippodrome.getWinner();
        double maxDistanceActual = horseWinner.getDistance();
        double maxDistanceExpected = 10;
        assertEquals(maxDistanceExpected, maxDistanceActual);
    }
}