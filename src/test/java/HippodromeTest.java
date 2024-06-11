import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HippodromeTest {

    @Test
    public void constructor_NullListParam_ThrowsIllegalArgumentException() {
        String exceptionMessage = "Horses cannot be null.";
        List<Horse> horses = null;

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(horses));
        assertEquals(exception.getMessage(), exceptionMessage);
    }

    @Test
    public void constructor_EmptyListParam_ThrowsIllegalArgumentException() {
        String exceptionMessage = "Horses cannot be empty.";
        List<Horse> horses = new ArrayList<>();

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(horses));
        assertEquals(exception.getMessage(), exceptionMessage);
    }

    @Test
    void getHorses_ListWithAllHorses() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("Horse" + i,i,i));
        }
        Hippodrome hippodrome = new Hippodrome(horses);

        assertNotNull(hippodrome.getHorses());
        assertEquals(30,hippodrome.getHorses().size());
        assertEquals("Horse0", hippodrome.getHorses().get(0).getName());
        assertEquals("Horse10", hippodrome.getHorses().get(10).getName());
        assertEquals("Horse29", hippodrome.getHorses().get(29).getName());
    }

    @Test
    void move_MoveMethodForAllHorses() {
        List<Horse> horses = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            horses.add(Mockito.mock(Horse.class));
        }

        Hippodrome hippodrome = new Hippodrome(horses);

        hippodrome.move();

        for (Horse horse : horses) {
            Mockito.verify(horse, Mockito.times(1)).move();
        }
    }

    @Test
    void getWinner_Correct() {
        Hippodrome hippodrome = new Hippodrome(List.of(
                new Horse("horse10", 3,10),
                new Horse("horse20", 3,20),
                new Horse("horse30", 3,30)
        ));

        assertEquals("horse30", hippodrome.getWinner().getName());
    }
}