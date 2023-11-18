import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class HippodromeTest {

    @Test
    void constructorNullListTests() {
        Throwable throwable =
                Assertions.assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        String expectedMessage = "Horses cannot be null.";
        String actualMessage = throwable.getMessage();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void constructorEmptyListTests() {
        Throwable throwable =
                Assertions.assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
        String expectedMessage = "Horses cannot be empty.";
        String actualMessage = throwable.getMessage();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void hippodromeGetHorsesTest() {
        List<Horse> actualList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            actualList.add(Mockito.mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(actualList);

        List<Horse> expectedList = hippodrome.getHorses();
        Assertions.assertEquals(actualList, expectedList);
    }

    @Test
    void hippodromeMoveTest() {
        List<Horse> actualList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            actualList.add(Mockito.mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(actualList);

        hippodrome.move();
        for (Horse horse: hippodrome.getHorses()
             ) {
            Mockito.verify(horse).move();
        }
    }

    @Test
    void hippodromeGetWinnerTest() {
        List<Horse> allHorses = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            allHorses.add(new Horse("horse" + i, 0.0, i * 100));
        }

        Horse actualWinner = new Hippodrome(allHorses).getWinner();
        Horse expectedWinner = allHorses.get(4);
        Assertions.assertEquals(expectedWinner,actualWinner);
    }
}
