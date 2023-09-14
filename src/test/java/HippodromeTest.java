import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class HippodromeTest {
    @Test
    public void nameHippodromeNullException() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }

    @Test
    public void whenHippodromeMessageNullName() {
        try {
            new Hippodrome(null);
        } catch (IllegalArgumentException e) {
            assertEquals("Horses cannot be null.", e.getMessage());
        }
    }

    @Test
    public void hippodromeEmptyException() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
    }

    @Test
    public void hippodromeMessageEmptyException() {
        try {
            new Hippodrome(new ArrayList<>());
        } catch (IllegalArgumentException e) {
            assertEquals("Horses cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void hippodromeGetHorsesException() {
        List<Horse> horseList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horseList.add(new Horse(Integer.toString(i), i, i));
        }
        Hippodrome hippodrome = new Hippodrome(horseList);
        assertEquals(horseList, hippodrome.getHorses());
    }

    @Test
    public void hippodromeCallMove() {
        List<Horse> horseList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horseList.add(mock(Horse.class));
        }
        Hippodrome hippodrome=new Hippodrome(horseList);
        hippodrome.move();
        for (Horse horse:horseList){
          verify(horse).move();
        }
    }
   @Test
   public void returnGetWinner(){
        Horse horse1=new Horse(Integer.toString(1), 1, 1);
       Horse horse2=new Horse(Integer.toString(2), 2, 2);
       Horse horse3=new Horse(Integer.toString(3), 3, 3);
       Horse horse4=new Horse(Integer.toString(4), 4, 4);
      Hippodrome hippodrome=new Hippodrome(List.of(horse1,horse2,horse3,horse4));
      assertSame(horse4,hippodrome.getWinner());
   }

}