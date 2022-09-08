import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class HippodromeTest {
    private final String validName = "name";
    private final double validSpeed = 1.0;
    private final double validDistance = 1.0;

    @Test
    void constructorShouldThrowIllegalArgumentExceptionWhenHorseListIsNull() {
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> new Hippodrome(null)
        );

        assertEquals("Horses cannot be null.", thrown.getMessage());
    }
    @Test
    void constructorShouldThrowIllegalArgumentExceptionWhenHorseListIsEmpty() {
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> new Hippodrome(new ArrayList<>())
        );

        assertEquals("Horses cannot be empty.", thrown.getMessage());
    }

    @Test
    void getHorsesShouldReturnTheSameListAsConstructorArgument() {
        List<Horse> expectedList = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            String name = validName + i;
            expectedList.add(new Horse(name, 1.0));
        }

        Hippodrome hippodrome = new Hippodrome(expectedList);
        assertEquals(expectedList, hippodrome.getHorses());
//        assertArrayEquals(expectedList.toArray(), hippodrome.getHorses().toArray());
    }



    @Test
    void moveShouldCallMoveMethodForEachHorse() {
        List<Horse> mockedHorseList = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            mockedHorseList.add(Mockito.mock(Horse.class));
        }

        Hippodrome hippodrome = new Hippodrome(mockedHorseList);
        hippodrome.move();

        mockedHorseList.forEach(
                mockedHorse -> Mockito.verify(mockedHorse).move()
        );
    }

    @Test
    void getWinnerShouldReturnHorseWithMaxDistance() {
        double expected = 10;

        List<Horse> horseList = new ArrayList<>();
        horseList.add(new Horse(validName, validSpeed, expected - 0.1));
        horseList.add(new Horse(validName, validSpeed, expected));
        horseList.add(new Horse(validName, validSpeed, expected - 3));

        Hippodrome hippodrome = new Hippodrome(horseList);
        double actual = hippodrome.getWinner().getDistance();

        assertEquals(expected, actual);
    }
}