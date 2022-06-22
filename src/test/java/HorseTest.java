import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class HorseTest {

    @Test
    public void HorseNameNotNullInConstructor() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Horse(null,1,1);
        });
        Assertions.assertEquals("Name cannot be null.", exception.getMessage());
    }
    @ParameterizedTest
    @ValueSource(strings = {"", " ","\t", "\n"})
    public void HorseNameNotEmptyInConstructor() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Horse("",1,1);
        });
        Assertions.assertEquals("Name cannot be blank.", exception.getMessage());
    }
    @Test
    public void HorseSpeedNotNegativeInConstructor() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Horse("Vasea",-1,1);
        });
        Assertions.assertEquals("Speed cannot be negative.", exception.getMessage());
    }
    @Test
    public void HorseDistanceNotNegativeInConstructor() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Horse("Vasea",4,-1);
        });
        Assertions.assertEquals("Distance cannot be negative.", exception.getMessage());
    }
    @Test
    public void HorseGetName() {
        Horse horse = new Horse("Bercut",4, 6);
        Assertions.assertEquals("Bercut", horse.getName());
    }
    @Test
    public void HorseGetSpeed() {
        Horse horse = new Horse("Bercut",4, 6);
        Assertions.assertEquals(4, horse.getSpeed());
    }
    @Test
    public void HorseGetDistance() {
        Horse horse1 = new Horse("Bercut",4, 6);
        Assertions.assertEquals(6, horse1.getDistance());
        Horse horse2 = new Horse("Bercut",4);
        Assertions.assertEquals(0, horse2.getDistance());
    }


/*    @Test
    public void HorseMove_GetRandomDouble_method(){
        MockedStatic<Horse> fakeHorse = Mockito.mockStatic(Horse.class);
        Mockito.when(fakeHorse.)
        Mockito.doReturn(0.5).when(fakeHorse).g
        fakeHorse.when(Horse::getRandomDouble).

        //Mockito.doAnswer(x-> Horse.getRandomDouble(0.2, 0.9)).when(fakeHorse).move();
    }*/


}
