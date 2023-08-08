import org.junit.jupiter.api.Test;
import ru.javarush.mukhametzyanov.hippodrome.Hippodrome;
import ru.javarush.mukhametzyanov.hippodrome.Horse;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class HippodromeTest {
    @Test
    public void constructorParam_checkNull_throwException() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }

    @Test
    public void constructorParam_checkNull_exceptionMassageIsEqual() {
        try {
            new Hippodrome(null);
        } catch (IllegalArgumentException exception) {
            assertEquals("Horses cannot be null.", exception.getMessage());
        }
    }

    @Test
    public void constructorParam_checkListIsEmpty_throwException() {
        List<Horse> emptyList = new ArrayList<>();
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(emptyList));
    }

    @Test
    public void constructorParam_checkListIsEmpty_exceptionMassageIsEqual() {
        List<Horse> emptyList = new ArrayList<>();
        try {
            new Hippodrome(emptyList);
        } catch (IllegalArgumentException exception) {
            assertEquals("Horses cannot be empty.", exception.getMessage());
        }
    }

    @Test
    public void returnParam_checkLists_objectIsEqual() {
        List<Horse> horseList = new ArrayList<>();
        String name = "horse";
        int counter = 0;
        for (int i = 0; i < 30; i++) {
            horseList.add(new Horse(name + i, counter + i, counter + i));
        }
        Hippodrome hippodrome = new Hippodrome(horseList);
        assertEquals(horseList, hippodrome.getHorses());
    }

    @Test
    public void checkMoveMethod_methodIsCalled() {
        List<Horse> horseList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horseList.add(mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(horseList);
        hippodrome.move();
        for (Horse horse : horseList) {
            verify(horse).move();
        }
    }

    @Test
    public void checkGetWinnerMethod_returnHorse() {
        Horse horse = new Horse("argument", 1, 1);
        Horse horse1 = new Horse("argument", 1, 12);
        Horse horse2 = new Horse("argument", 1, 23);
        Hippodrome hippodrome = new Hippodrome(List.of(horse, horse1, horse2));
        assertSame(horse2, hippodrome.getWinner());
    }
}
