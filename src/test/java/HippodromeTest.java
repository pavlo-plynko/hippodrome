import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;

class HippodromeTest {
    //a
    @Test
    void testConstructor_NullHorseList_ThrowsIllegalArgumentException() {

        assertThrows(IllegalArgumentException.class, () -> {
            new Hippodrome(null);
        });
    }

    @Test
    void testConstructor_NullHorseList_ExceptionMessageContainsCorrectErrorMessage() {

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Hippodrome(null);
        });
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    void testConstructor_EmptyHorseList_ThrowsIllegalArgumentException() {
        List<Horse> horses = new ArrayList<>();
        assertThrows(IllegalArgumentException.class, () -> {
            new Hippodrome(horses);
        });

    }

    @Test
    void testConstructor_EmptyHorseList_ExceptionMessageContainsCorrectErrorMessage() {
        List<Horse> horses = new ArrayList<>(5);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Hippodrome(horses);
        });
        //assertEquals("Horses cannot be null.", exception.getMessage());// i have no ideas, how to do this
    }

    //b
    @Test
    void getHorses() {
        List<Horse> expectedHorses = Create30DifferentHorses();
        Hippodrome hippodrome = new Hippodrome(expectedHorses);
        assertIterableEquals(expectedHorses, hippodrome.getHorses());
    }

    private List<Horse> Create30DifferentHorses() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("Horse" + i, 10));

        }
        return horses;
    }

    //c
    @Test
    void move() {
        List<Horse> horses=createListOf50MockHorses();
        Hippodrome hippodrome=new Hippodrome(horses);
        hippodrome.move();
        for (int i = 0; i <horses.size(); i++) {
            verify(horses.get(i)).move();

        }


    }
    private List<Horse> createListOf50MockHorses() {
        List<Horse> mockHorses = new ArrayList<>();
        for (int i = 1; i <= 50; i++) {
            Horse mockHorse = Mockito.mock(Horse.class);
            mockHorses.add(mockHorse);
        }
        return mockHorses;
    }


//d
    @Test
    void getWinner() {

        Horse horse1=new Horse("winner",2,100);
        Horse horse2=new Horse("Loser",2,1);
        List<Horse> horses= new ArrayList<>();
        horses.add(horse1);
        horses.add(horse2);
        Hippodrome hippodrome=new Hippodrome(horses);
        assertEquals(horse1,hippodrome.getWinner());

    }
}