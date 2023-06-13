import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import java.lang.reflect.Field;


public class HorseTest {

    Horse horsePrince = new Horse("Prince", 20.0, 1000.0);


    @Test
    public void testConstructorWithNullName() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Horse(null, 20.0, 1000.0));
    }

    @Test
    public void testConstructorWithNullNameWithExceptionMessage() {
        Throwable exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(null, 20.0, 1000.0));
        Assertions.assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\n", "\r"})
    public void testConstructorWithBlankName(String name) {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Horse(name, 20.0, 1000.0));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\n", "\r"})
    public void testConstructorWithBlankNameWithExceptionMessage(String name) {
        Throwable exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Horse(name, 20.0, 1000.0));
        Assertions.assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    public void testConstructorWithNegativeSpeed() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Horse("Prince", -4.2, 1000.0));
    }

    @Test
    public void testConstructorWithNegativeSpeedWithExceptionMessage() {
        Throwable exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Horse("Prince", -4.2, 1000.0));
        Assertions.assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    public void testConstructorWithNegativeDistance() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Horse("Prince", 20.0, -400.5));
    }

    @Test
    public void testWithNegativeDistanceWithExceptionMessage() {
        Throwable exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Horse("Prince", 20.0, -400.5));
        Assertions.assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    public void testGetName() throws NoSuchFieldException, IllegalAccessException {
        // by reflection API
        Field name = Horse.class.getDeclaredField("name");
        name.setAccessible(true);
        String horseName = (String) name.get(horsePrince);
        Assertions.assertEquals("Prince", horseName);
        // or Assertions.assertEquals("Prince", horsePrince.getName());
    }

    @Test
    public void testGetSpeed() {
        Assertions.assertEquals(20.0, horsePrince.getSpeed());
    }

    @Test
    public void testGetDistance() {
        Assertions.assertEquals(1000.0, horsePrince.getDistance());
    }

    @Test
    public void testGetZeroDistance() {
        Horse horseVenus = new Horse("Venus", 15.0);
        Assertions.assertEquals(0.0, horseVenus.getDistance());
    }

    @Test
    public void testMoveInvokesGetRandomDouble() {
        try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
            horsePrince.move();
            horseMockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.5, 0.8, 0.4})
    public void testMove(double randomArg) {
        try (MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)) {
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(randomArg);
            double initialDistance = horsePrince.getDistance();

            horsePrince.move();

            double expectedDistance = initialDistance + horsePrince.getSpeed() * randomArg;
            Assertions.assertEquals(expectedDistance, horsePrince.getDistance());
        }
    }
}