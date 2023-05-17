import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HippodromeTest {

    @ParameterizedTest
    @NullSource
    public void nullHorsesShouldThrowIllegalArgumentException(ArrayList<Horse> arrayList) {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(arrayList));
    }

    @ParameterizedTest
    @NullSource
    public void nullHorsesShouldThrowTextNameCannotBeNull(ArrayList<Horse> arrayList) {
        try {
            new Hippodrome(arrayList);
        } catch (IllegalArgumentException e) {
            assertEquals("Horses cannot be null.", e.getMessage());
        }
    }
    @Test
    public void emptyHorsesShouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
    }

    @Test
    public void emptyHorsesShouldThrowTextNameCannotBeEmpty() {
        try {
            new Hippodrome(new ArrayList<>());
        } catch (IllegalArgumentException e) {
            assertEquals("Horses cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void getHorsesTest(){
        List<Horse> testHorses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            testHorses.add(new Horse("HorseNumber = " + i, i, i+1));
        }
        Hippodrome testHippo = new Hippodrome(testHorses);
        assertEquals(testHorses, testHippo.getHorses());
    }

    @Test
    public void moveTest(){
        List<Horse> testHorses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            testHorses.add(mock(Horse.class));
        }
        Hippodrome testHippo = new Hippodrome(testHorses);
        testHippo.move();

        for(Horse horse : testHorses){
            verify(horse).move();
        }
    }

    @Test
    public void getWinnerTest(){
        int winner = 5;
        List<Horse> testHorses = new ArrayList<>();
        for (int i = 1; i <= winner+1; i++) {
            testHorses.add(new Horse("HorseNumber = " + i, i, i+1));
        }
        Hippodrome testHippo = new Hippodrome(testHorses);
        assertSame(testHorses.get(winner), testHippo.getWinner());
    }

}