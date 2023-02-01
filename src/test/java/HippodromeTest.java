
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(MockitoExtension.class)
public class HippodromeTest {

    @Test
    public void HippodromeTest1() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }

    @Test
    public void HippodromeTest2() {
        try {
            Hippodrome hippodrome = new Hippodrome(null);
        } catch (IllegalArgumentException e) {
            assertEquals("Horses cannot be null.", e.getMessage());
        }
    }

    @Test
    public void HippodromeTest3(){
        List<Horse> horses = new ArrayList<>();
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(horses));
    }

    @Test
    public void HippodromeTest4(){
        List<Horse> horses = new ArrayList<>();
        try {
            new Hippodrome(horses);
        } catch (IllegalArgumentException e){
            assertEquals("Horses cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void getHorsesTest(){
        List<Horse> horses = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 30; i++){
            horses.add(new Horse(RandomStringUtils.random(1), random.nextDouble(), random.nextDouble()));
        }

        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses, hippodrome.getHorses());
    }

    @Test
    public void moveTest(){
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++){
            Horse mockHorse = Mockito.mock(Horse.class);
            horses.add(mockHorse);
        }
        new Hippodrome(horses).move();
        for (int i = 0; i < 50; i++) {
            Mockito.verify(horses.get(i)).move();
        }
    }

    @Test
    public void getWinnerTest(){
        List<Horse> horses = new ArrayList<>();
        int winDistance = 5;
        for (int i = 1; i <= winDistance; i++){
            Horse horse = new Horse(RandomStringUtils.random(5), 0, i);
            horses.add(horse);
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(winDistance, hippodrome.getWinner().getDistance());
    }
}
