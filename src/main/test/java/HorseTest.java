import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyDouble;


public class HorseTest {

    @Test
    public void whenConstructorGetNull(){
        assertThrows(IllegalArgumentException.class,()->new Horse(null,5.5,3.4));
    }

    @ParameterizedTest
    @ValueSource(strings = {" ","  ",""})
    public void whenConstructorGetNullStr(String strings){
        assertThrows(IllegalArgumentException.class,()->new Horse(strings,5.5,3.4));
    }

    @Test
    public void whenConstructorGetNullMessage(){
        IllegalArgumentException thrown= assertThrows(IllegalArgumentException.class,()->new Horse(null,5.5,3.4),"Name cannot be null.");
        assertEquals("Name cannot be null.",thrown.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {" ","  ",""})
    public void whenConstructorGetNullStrMsg(String strings){
        IllegalArgumentException thrown= assertThrows(IllegalArgumentException.class,()->new Horse(strings,5.5,3.4),"Name cannot be blank.");
        assertEquals("Name cannot be blank.",thrown.getMessage());
    }

    @Test
    public void whenConstructorGetNegative(){
        assertThrows(IllegalArgumentException.class,()->new Horse("null",-5.5,3.4));
    }

    @Test
    public void whenConstructorGetNegativeMessage(){
        IllegalArgumentException thrown= assertThrows(IllegalArgumentException.class,()->new Horse("null",-5.5,3.4),"Speed cannot be negative.");
        assertEquals("Speed cannot be negative.",thrown.getMessage());
    }

    @Test
    public void whenConstructorGetNegDist(){
        assertThrows(IllegalArgumentException.class,()->new Horse("null",5.5,-3.4));
    }

    @Test
    public void whenConstructorGetNegDistMessage(){
        IllegalArgumentException thrown= assertThrows(IllegalArgumentException.class,()->new Horse("null",5.5,-3.4),"Distance cannot be negative.");
        assertEquals("Distance cannot be negative.",thrown.getMessage());
    }

    @Test
    public void whenGetNameReturnString(){
        Horse horse=new Horse("horse",5.5,3.4);
        assertEquals("horse", horse.getName());
    }
    @Test
    public void whenGetSpeedReturnSecondArg(){
        Horse horse=new Horse("horse",5.5,3.4);
        assertEquals(5.5, horse.getSpeed());
    }

    @Test
    public void whenGetDistanceReturnSecondArg(){
        Horse horse=new Horse("horse",5.5,3.4);
        assertEquals(3.4, horse.getDistance());
    }
    @Test
    public void whenGetDistanceReturnNull(){
        Horse horse=new Horse("horse",5.5);
        assertEquals(0, horse.getDistance());
    }
    @Spy
    Horse horse=new Horse("horse",35,6.7);
    @ExtendWith(MockitoExtension.class)
    @Test
    public void whenMoveUseGetRandomDouble(){

        try(MockedStatic<Horse> horseMockedStatic= Mockito.mockStatic(Horse.class)) {
            horse.move();
            horseMockedStatic.verify(()-> Horse.getRandomDouble(0.2,0.9));

        }
    }

    @Test
    public void whenMoveGetRandomDouble(){

        try(MockedStatic<Horse> horseMockedStatic= Mockito.mockStatic(Horse.class)) {
            horseMockedStatic.when(() -> Horse.getRandomDouble(anyDouble(),anyDouble())).thenReturn(1.0);
            horse.move();
            assertEquals(41.7,horse.getDistance());
        }
    }


}