import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HippodromeTest {

    final private ArrayList<Horse> EMPTY_HORSES_LIST = new ArrayList<>();
    final private String NULL_HORSES_LIST_ERROR_MESSAGE = "Horses cannot be null.";
    final private String EMPTY_HORSES_LIST_ERROR_MESSAGE = "Horses cannot be empty.";


    ArrayList<Horse> getInitedListOfHorses(int size) {
        ArrayList<Horse> horsesList = new ArrayList<>();
        for (int i = 1; i <= size; i++) {
            horsesList.add(Mockito.spy(new Horse("Name" + i, 1.0, 1.0)));
        }
        return horsesList;
    }

    @Test
    @DisplayName("Hippodrome creation with null horses list fails")
    void constructorFailsWithNullList() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }

    @Test
    @DisplayName("Hippodrome creation with null horses list fails with message " + NULL_HORSES_LIST_ERROR_MESSAGE)
    void constructorFailsWithNullNameAndHasSpecificErrorMsg() {
        String errorMsg = "";

        try {
            new Hippodrome(null);
        } catch (IllegalArgumentException exception) {
            errorMsg = exception.getMessage();
        }

        assertEquals(NULL_HORSES_LIST_ERROR_MESSAGE, errorMsg);
    }

    @Test
    @DisplayName("Hippodrome creation with empty horses list fails")
    void constructorFailsWithEmptyList() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(EMPTY_HORSES_LIST));
    }

    @Test
    @DisplayName("Hippodrome creation with empty horses list fails with message " + EMPTY_HORSES_LIST_ERROR_MESSAGE)
    void constructorFailsWithEmptyListAndHasSpecificErrorMsg() {
        String errorMsg = "";

        try {
            new Hippodrome(EMPTY_HORSES_LIST);
        } catch (IllegalArgumentException exception) {
            errorMsg = exception.getMessage();
        }

        assertEquals(EMPTY_HORSES_LIST_ERROR_MESSAGE, errorMsg);
    }

    @Test
    @DisplayName("Hippodromes getHorses returns same list of 30 horses with same order which was passed to constructor")
    void getHorsesWorksAsExpected() {
        List<Horse> listOf30Horses = getInitedListOfHorses(30);
        Hippodrome hippodrome = new Hippodrome(listOf30Horses);

        assertEquals(listOf30Horses, hippodrome.getHorses());
    }

    @Test
    @DisplayName("Hippodromes move method calls move method of every horse from 50 list only ones")
    void moveWorksAsExpected() {
        Hippodrome hippodrome = new Hippodrome(getInitedListOfHorses(50));

        hippodrome.move();

        hippodrome.getHorses().forEach((horse) -> {
            Mockito.verify(horse, Mockito.only()).move();
        });
    }

    @Test
    @DisplayName("Hippodromes getWinner method return horse with biggest distance")
    void getWinnerWorksAsExpected() {
        Hippodrome hippodrome = new Hippodrome(getInitedListOfHorses(10));
        int expectedWinnerIndex = 5;
        Mockito.when(hippodrome.getHorses().get(expectedWinnerIndex).getDistance()).thenReturn(1_000_000.0);

        hippodrome.move();

        assertEquals(hippodrome.getHorses().get(expectedWinnerIndex), hippodrome.getWinner());
    }
}
