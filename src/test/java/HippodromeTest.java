import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
@ExtendWith(MockitoExtension.class)
public class HippodromeTest {
    
  @Test
    public void testConstructorHippodromeNull (){
      Assertions.assertThrows(IllegalArgumentException.class, ()-> new Hippodrome(null));
  }
    @Test
    public void testConstructorHippodromeNullMessage (){
        Throwable throwable = Assertions.assertThrows(IllegalArgumentException.class, ()-> new Hippodrome(null));
        Assertions.assertEquals("Horses cannot be null.", throwable.getMessage());
  }

    @Test
    public void testConstructorHippodromeEmptyList (){
        Assertions.assertThrows(IllegalArgumentException.class, ()-> new Hippodrome(new ArrayList<>()));
    }

    @Test
    public void testConstructorHippodromeEmptyListMessage (){
        for (int i = 0; i < 30; i++) {

        }
        Throwable throwable = Assertions.assertThrows(IllegalArgumentException.class, ()-> new Hippodrome(new ArrayList<>()));
        Assertions.assertEquals("Horses cannot be empty.", throwable.getMessage());
    }

    @Test
    public void testGetHorses() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse(Integer.toString(i), i, i));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        Assertions.assertEquals(horses, hippodrome.getHorses());
    }

    @Test
    public void testCountHorsesMove() {

        List<Horse> horses = new ArrayList<>();
        Horse horse =Mockito.mock(Horse.class);

        for (int i = 0; i < 50; i++) {
            horse = Mockito.mock(Horse.class);
            horses.add(horse);
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        Mockito.doCallRealMethod().when(horse).move();
        hippodrome.move();
        Mockito.verify(horse,Mockito.times(1)).move();
    }

    @Test
    public void testGerWinner() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse(Integer.toString(i), i, i));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        Assertions.assertEquals(horses.get(29),hippodrome.getWinner());
    }

}
