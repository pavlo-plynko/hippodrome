import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HippodromeTest {

    @Test
    void constructorWithNullParameter(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    void constructorWithEmptyList(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(List.of()));
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    void getHorsesTest() {
        List<Horse> horseList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horseList.add(i, new Horse("Horse №" + i, i, i));
        }
        Hippodrome hippodrome = new Hippodrome(horseList);
        assertNotNull(hippodrome.getHorses());
        assertEquals(30, hippodrome.getHorses().size());
        assertEquals("Horse №5", hippodrome.getHorses().get(5).getName());
        assertEquals("Horse №11", hippodrome.getHorses().get(11).getName());
    }

    @Test
    void moveTest() {
        List<Horse> horseList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horseList.add(Mockito.mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(horseList);

        hippodrome.move();
        for (Horse hors : hippodrome.getHorses()) {
            Mockito.verify(hors, Mockito.times(1)).move();
        }
    }

    @Test
    void getWinner() {
        Hippodrome hippodrome = new Hippodrome(List.of(
                new Horse("Expected winner", 5, 100),
                new Horse("Second", 3, 20),
                new Horse("Third", 2, 10)
        ));

        assertEquals("Expected winner", hippodrome.getWinner().getName());
    }
}