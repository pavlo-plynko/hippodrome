import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyDouble;

public class HorseTest {

    @Test
    void constructorNameNullExceptionTest(){
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1) );
    }

    @Test
    void constructorNameNullMessageTest(){
        try{
            new Horse(null, 0);
        }catch (IllegalArgumentException e){
            assertEquals("Name cannot be null.",e.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t"})
    void constructorNameEmptyExceptionTest(String name){
        assertThrows(IllegalArgumentException.class, () -> new Horse(name, 1));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t"})
    void constructorNameEmptyMessageTest(String name){
        try{
            new Horse(name, 0);
        }catch (IllegalArgumentException e){
            assertEquals("Name cannot be blank.",e.getMessage());
        }
    }

    @Test
    void constructorSpeedNegativeExceptionTest(){
        assertThrows(IllegalArgumentException.class, () -> new Horse("Bugay", -1));
    }

    @Test
    void constructorSpeedNegativeMessageTest(){
        try{
            new Horse("Bugay", -1);
        }catch (IllegalArgumentException e){
            assertEquals("Speed cannot be negative.", e.getMessage());
        }
    }

    @Test
    void constructorDistanceNegativeExceptionTest(){
        assertThrows(IllegalArgumentException.class, () -> new Horse("Bugay", 1, -1));
    }

    @Test
    void constructorDistanceNegativeMessageTest(){
        try{
            new Horse("Bugay", 1,1);
        }catch (IllegalArgumentException e){
            assertEquals("Distance cannot be negative.", e.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"Kon` v palto", "Blaze", "Prinz"})
    void getNameReturnTest(String name){
        assertEquals(name, new Horse(name, 1).getName());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 10, 100})
    void getSpeedReturnTest(int speed){
        assertEquals(speed, new Horse("Napoleon" + speed, speed).getSpeed());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 10, 100})
    void getDistanceReturnTest(int dist){
        assertEquals(dist, new Horse("Burak", 1, dist).getDistance());
    }

    @Test
    void getDistanceReturnZeroTest(){
        assertEquals(0, new Horse("Burak",1).getDistance());
    }

    @Test
    @ExtendWith(MockitoExtension.class)
    void moveRandomValueTest(){
        try(MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)){
            new Horse("example Horse", 2).move();
            horseMockedStatic.verify(() -> Horse.getRandomDouble(0.2,0.9));
        }
    }

    @ParameterizedTest
    @CsvSource({"0.5, 3.0", "0.3, 0", "10,100"})
    void moveDistanceTest(double speed, double distance){
        try(MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)){
            Horse horse = new Horse("example", speed, distance);
            horseMockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(0.5);
            horse.move();
            double expectedDistance = distance + speed * Horse.getRandomDouble(0.2,0.9);
            assertEquals(expectedDistance, horse.getDistance());
        }
    }
}
