import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class HippodromeTest {

    private final List<Horse> horses = prepareHorses(30);

    List<Horse> prepareHorses(int size) {
        Random random = new Random();
        int randomSpeed;
        int randomDistance;
        List<Horse> horses = new ArrayList<>();
        horses.add(0, new Horse("horse", 150, 200));

        for (int i = 1; i <= size - 1; i++) {
            randomSpeed = random.nextInt((100 - 50) + 50);
            randomDistance = random.nextInt((150 - 100) + 100);
            horses.add(i, new Horse("horse" + i, randomSpeed, randomDistance));
        }
        return horses;
    }

    List<Horse> prepareMockHorses(int size) {
        List<Horse> horses = new ArrayList<>();

        for (int i = 1; i <= size; i++) {
            horses.add(mock(Horse.class));
        }
        return horses;
    }

    @Test
    void testHippodromeConstructorForNullIllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }

    @Test
    void testHippodromeConstructorForNullIllegalArgumentExceptionMessage() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        String expectedMessage = "Horses cannot be null.";
        String actualMessage = exception.getMessage();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void testHippodromeConstructorForEmptyIllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
    }

    @Test
    void testHippodromeConstructorForEmptyIllegalArgumentExceptionMessage() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
        String expectedMessage = "Horses cannot be empty.";
        String actualMessage = exception.getMessage();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void testGetHorses() {
        Hippodrome hippodrome = new Hippodrome(horses);
        Assertions.assertEquals(hippodrome.getHorses(), horses);
    }

    @Test
    void testMoveVerification() {
        List<Horse> mockHorses = prepareMockHorses(50);
        Hippodrome hippodrome = new Hippodrome(mockHorses);
        hippodrome.move();
        for (Horse horse : hippodrome.getHorses()) {
            verify(horse).move();
        }
    }

    @Test
    void testGetWinner() {
        Hippodrome hippodrome = new Hippodrome(horses);
        Horse expectedHorse = hippodrome.getWinner();
        Horse actualHorse = horses.get(0);
        Assertions.assertEquals(expectedHorse, actualHorse);
    }
}