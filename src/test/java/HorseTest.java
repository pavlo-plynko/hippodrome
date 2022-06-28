import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

public class HorseTest {

    @Test
    public void nullNameExceptions(){
        assertThrows(IllegalArgumentException.class, () -> new Horse(null,1,1));
    }

    @Test
    public void nullNameMessage(){
        try{
            new Horse(null,1,1);
            fail();
        }
        catch (IllegalArgumentException e)
        {
            assertEquals("Name cannot be null.", e.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ","\t", "\n"})
    public void horseName_NotEmpty_In_Constructor(String values) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Horse(values,1,1);
        });
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    public void HorseSpeedNotNegativeInConstructor() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Horse("Vasea",-1,1);
        });
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    public void horseDistanceNotNegativeInConstructor() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Horse("Vasea",4,-1);
        });
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    public void horseGetName() throws IllegalAccessException, NoSuchFieldException {
        Horse horse = new Horse("Bercut",4, 6);
        Field name = Horse.class.getDeclaredField("name");
        name.setAccessible(true);
        String nameValue = (String) name.get(horse);
        assertEquals("Bercut", nameValue);
    }

    @Test
    public void horseGetSpeed() {
        double expectedSpeed = 4;
        Horse horse = new Horse("Bercut",expectedSpeed, 6);
        assertEquals(expectedSpeed, horse.getSpeed());
    }

    @Test
    public void horseGetDistance() {
        Horse horse1 = new Horse("Bercut",4, 6);
        assertEquals(6, horse1.getDistance());
        Horse horse2 = new Horse("Bercut",4);
        assertEquals(0, horse2.getDistance());
    }

    @Test
    public void horseMove_GetRandomDouble_method(){
        try(MockedStatic<Horse> fakeHorse = mockStatic(Horse.class)){
            new Horse("Becaz",1,1).move();

            fakeHorse.verify(()->Horse.getRandomDouble(0.2,0.9));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.1,0.2,0.6,0.9,20})
    public void horseMove_GetDistance_By_Formula(double random){
        try(MockedStatic<Horse> fakeHorse = mockStatic(Horse.class)){
            Horse horse = new Horse("Garik",31,283);
            fakeHorse.when(() -> Horse.getRandomDouble(0.2,0.9)).thenReturn(random);

            horse.move();

            assertEquals(283+31*random,horse.getDistance());
        }
    }

}
