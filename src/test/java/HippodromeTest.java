import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HippodromeTest {

    @Test
    @DisplayName("null or Empty List constructor")
    void Hippodrome(){
        Exception exception = assertThrows(IllegalArgumentException.class, ()->{new Hippodrome(null);});
        assertEquals("Horses cannot be null.", exception.getMessage());

        Exception exception2 = assertThrows(IllegalArgumentException.class, ()->{new Hippodrome(new ArrayList<>());});
        assertEquals("Horses cannot be empty.", exception2.getMessage());

    }

    @Test
    @DisplayName("Check immutability list horses")
    void getHorses() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse(String.valueOf(i),0.1,0.1));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        List<Horse> newHorses = hippodrome.getHorses();
        boolean check = true;
        for (int i = 0; i < 3; i++) {
            if (!horses.get(i).getName().equals(newHorses.get(i).getName())){
                check = false;
            }

        }
        assertTrue(check);

    }

    @Test
    @DisplayName("check call horse.move() for each horse")
    void move() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(Mockito.mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();
        for (Horse horse : horses) {
            Mockito.verify(horse, Mockito.times(1)).move();
        }



    }

    @Test
    @DisplayName("return horse with max distance")
    void getWinner() {
        Horse horse = new Horse("Horse",0.1,0.1);
        Horse horse1 = new Horse("Horse",0.1,0.2);
        Horse horse2 = new Horse("Horse",0.1,0.3);
        Hippodrome hippodrome = new Hippodrome(List.of(horse,horse1,horse2));
        assertEquals(horse2,hippodrome.getWinner());
    }
}