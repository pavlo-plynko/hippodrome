import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class HippodromeTest {

    @Test
    void shouldBeThrownIllegalExceptionWhenConstructorParameterNull() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));

        assertEquals("Horses cannot be null.", thrown.getMessage());
    }

    @Test
    void shouldBeThrownIllegalExceptionWhenConstructorParameterEmptyList() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));

        assertEquals("Horses cannot be empty.", thrown.getMessage());
    }

    @Test
    void shouldMethodGetHorsesReturnSameListFromConstructor() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            String name = "horse";
            horses.add(new Horse(name + 1, i, i));
        }

        Hippodrome hippodrome = new Hippodrome(horses);

        assertEquals(horses, hippodrome.getHorses());
    }

    @Test
    void shouldEachObjectInvokeMethodMove() {
        List<Horse> horses = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            horses.add(mock(Horse.class));
        }

        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();

        for (Horse hors : hippodrome.getHorses()) {
            verify(hors).move();
        }

    }

    @Test
    void shouldMethodGetWinnerReturnHorseObjectWithTheBiggestDistanceValue() {

        Horse loser = new Horse("loser", 1,1);
        Horse winner = new Horse("winner", 2,2);

        Hippodrome hippodrome = new Hippodrome(List.of(loser,winner));

        assertEquals(winner.getDistance(),hippodrome.getWinner().getDistance());


    }
}
