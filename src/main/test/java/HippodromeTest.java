import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.mockito.Mockito;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class HippodromeTest {
    @Test
    public void whenConstructorGetNull(){
        assertThrows(IllegalArgumentException.class,()->new Hippodrome(null));
    }
    @Test
    public void whenConstructorGetNullMsg(){
        IllegalArgumentException thrown= assertThrows(IllegalArgumentException.class,()->new Hippodrome(null),"Horses cannot be null.");
        assertEquals("Horses cannot be null.",thrown.getMessage());
    }

    @ParameterizedTest
    @EmptySource
    public void whenConstructorGetEmptyList(List<Horse> horses){
        assertThrows(IllegalArgumentException.class,()->new Hippodrome(horses));
    }
    @ParameterizedTest
    @EmptySource
    public void whenConstructorGetEmptyListMsg(List<Horse> horses){
        IllegalArgumentException thrown= assertThrows(IllegalArgumentException.class,()->new Hippodrome(horses),"Horses cannot be empty.");
        assertEquals("Horses cannot be empty.",thrown.getMessage());
    }

    @Spy
    List<Horse> horses=new ArrayList<>();
    @Test
    public void whenGetHorses(){
        for (int i = 0; i < 30; i++) {
            Horse horse=new Horse("horse"+i,i*0.1);
            horses.add(horse);
        }
        Hippodrome hippodrome=new Hippodrome(horses);
        assertEquals(horses,hippodrome.getHorses());
    }


    @Test
    public void whenMove(){
        for (int i = 0; i < 50; i++) {
            horses.add(Mockito.mock(Horse.class));
        }
        Hippodrome hippodrome=new Hippodrome(horses);
        hippodrome.move();
        for (Horse horse:horses) {
            Mockito.verify(horse).move();
        }
    }

    @Test
    public void whenGetWinner(){
        for (int i = 0; i < 30; i++) {
            Horse horse=new Horse("horse"+i,i,i*10.0);
            horses.add(horse);
        }
        Hippodrome hippodrome=new Hippodrome(horses);
        assertEquals(290.0,hippodrome.getWinner().getDistance());
    }

}

