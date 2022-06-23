import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class HorseTest {

    @Test
    public void HorseNameNotNullInConstructor() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Horse(null,1,1);
        });
        assertEquals("Name cannot be null.", exception.getMessage());
    }
    @ParameterizedTest
    @ValueSource(strings = {"", " ","\t", "\n"})
    public void HorseNameNotEmptyInConstructor() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Horse("",1,1);
        });
        assertEquals("Name cannot be blank.", exception.getMessage());
    }
    @Test
    public void HorseSpeedNotNegativeInConstructor() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Horse("Vasea",-1,1);
        });
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }
    @Test
    public void HorseDistanceNotNegativeInConstructor() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Horse("Vasea",4,-1);
        });
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }
    @Test
    public void HorseGetName() {
        Horse horse = new Horse("Bercut",4, 6);
        assertEquals("Bercut", horse.getName());
    }
    @Test
    public void HorseGetSpeed() {
        Horse horse = new Horse("Bercut",4, 6);
        assertEquals(4, horse.getSpeed());
    }
    @Test
    public void HorseGetDistance() {
        Horse horse1 = new Horse("Bercut",4, 6);
        assertEquals(6, horse1.getDistance());
        Horse horse2 = new Horse("Bercut",4);
        assertEquals(0, horse2.getDistance());
    }


    @ParameterizedTest
    @ValueSource(doubles = {7.0,9.0,13.0})
    public void HorseMove_GetRandomDouble_method(double value){
        double speedHorse = 10;
        double distanceHorse = 5;
        try(MockedStatic<Horse> fakeHorse = Mockito.mockStatic(Horse.class)){
            fakeHorse.when(()->Horse.getRandomDouble(0.2,0.9)).thenReturn(value);
            Horse testHorse = new Horse("Becaz",speedHorse,distanceHorse);
            testHorse.move();
            distanceHorse += speedHorse*value;
            assertEquals(distanceHorse,testHorse.getDistance());
        }

        //Mockito.doAnswer(x-> Horse.getRandomDouble(0.2, 0.9)).when(fakeHorse).move();
    }


}
