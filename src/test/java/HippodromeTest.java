import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class HippodromeTest {

    @Test
    void when_passing_null_to_the_constructor() {
        String exceptionMessage = "Horses cannot be null.";
        Throwable exception = assertThrows(IllegalArgumentException.class, ()-> new Hippodrome(null));

        assertEquals(new IllegalArgumentException(exceptionMessage).getMessage(),exception.getMessage());
    }
    @Test
    void when_passing_an_empty_list_to_the_constructor(){
        String exceptionMessage = "Horses cannot be empty.";
        List<Horse> emptyList = new ArrayList<>();
        Throwable exception = assertThrows(IllegalArgumentException.class, ()-> new Hippodrome(emptyList));

        assertEquals(new IllegalArgumentException(exceptionMessage).getMessage(),exception.getMessage());
    }

    @Test
    void getHorses(){
        List<Horse> horseList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horseList.add(new Horse("horse"+(i+1),(i+1)));
        }
        Hippodrome hippodrome = new Hippodrome(horseList);
        assertEquals(horseList,hippodrome.getHorses());
    }

    @Test
    void move() {
        List<Horse>horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();

        for (Horse horse: horses){
            verify(horse).move();
        }
    }

    @Test
    void getWinner() {
        Horse horse1 = new Horse("horse1",1,2.98);
        Horse horse2 = new Horse("horse2",1,3);
        Horse horse3 = new Horse("horse3",1,2);
        Hippodrome hippodrome = new Hippodrome(List.of(horse1,horse2,horse3));
        assertSame(horse2,hippodrome.getWinner());
    }
}