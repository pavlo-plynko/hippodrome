import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.*;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class HorseTest {
    @Test
    public void HorseTest1(){
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 0,0));
    }
    @Test
    public void HorseTest2(){
        try {
            new Horse(null, 0,0);
        } catch (IllegalArgumentException e){
            assertEquals("Name cannot be null.", e.getMessage());
        }
    }
    @ParameterizedTest
    @ValueSource(strings = {" ", "\t", "\n"})
    public void HorseTest3(String args){
        assertThrows(IllegalArgumentException.class, () -> new Horse(args, 0, 0));
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "\t", "\n"})
    public void HorseTest4(String args){
        try{
            new Horse(args, 0, 0);
        } catch (IllegalArgumentException e){
            assertEquals("Name cannot be blank.", e.getMessage());
        }
    }

    @Test
    public void HorseTest5(){
        assertThrows(IllegalArgumentException.class, () -> new Horse("Kevin", -1, 0));
    }

    @Test
    public void HorseTest6(){
        try {
            new Horse("Kevin", -1, 0);
        } catch (IllegalArgumentException e){
            assertEquals("Speed cannot be negative.", e.getMessage());
        }
    }

    @Test
    public void HorseTest7(){
        assertThrows(IllegalArgumentException.class, () -> new Horse("Kevin", 0, -1));
    }

    @Test
    public void HorseTest8(){
        try {
            new Horse("Kevin", 0, -1);
        } catch (IllegalArgumentException e){
            assertEquals("Distance cannot be negative.", e.getMessage());
        }
    }

    @Test
    public void getNameTest(){
        String name = "Kevin";
        assertEquals(name, new Horse(name, 0, 0).getName());
    }

    @Test
    public void getSpeedTest(){
        double speed = 20;
        assertEquals(speed, new Horse("Kevin", speed, 0).getSpeed());
    }

    @Test
    public void getDistanceTest1(){
        double distance = 20;
        assertEquals(distance, new Horse("Kevin", 0, distance).getDistance());
    }

    @Test
    public void getDistanceTest2(){
        assertEquals(0, new Horse("Kevin", 0).getDistance());
    }

    @Test
    public void moveTest1(){
        try(MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)){
            Horse horse = new Horse("Kevin", 0, 0);
            horse.move();
            horseMockedStatic.verify(() -> {
                Horse.getRandomDouble(0.2, 0.9);
            });
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {2.0, 0.8, 1.2})
    public void moveTest2(double args){
        try(MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)){
            horseMockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(args);
            Horse horse = new Horse("Kevin", 2);
            horse.move();
            assertEquals(horse.getDistance(), horse.getSpeed() * Horse.getRandomDouble(0.2, 0.9));
        }
    }


}
