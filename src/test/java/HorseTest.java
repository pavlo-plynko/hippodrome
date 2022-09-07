import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

public class HorseTest {
    @Test
    public void createHorseWithNullName() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Horse(null, 2.0, 2.0));
    }

    @Test
    public void createHorseWithNullNameAndCheckExceptionMessage() {
        try {
            new Horse(null, 2.0, 2.0);
        } catch (Exception e) {
            Assertions.assertEquals("Name cannot be null.", e.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t"})
    public void createHorseWithEmptyName(String name) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Horse(name, 2.0, 2.0));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t"})
    public void createHorseWithEmptyNameAndCheckExceptionMessage(String name) {
        try {
            new Horse(name, 2.0, 2.0);
        } catch (Exception e) {
            Assertions.assertEquals("Name cannot be blank.", e.getMessage());
        }
    }

    @Test
    public void createHorseWithNegativeSpeed() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Horse("Name", -2.0, 2.0));
    }

    @Test
    public void createHorseWithNegativeSpeedAndCheckExceptionMessage() {
        try {
            new Horse("Name", -2.0, 2.0);
        } catch (Exception e) {
            Assertions.assertEquals("Speed cannot be negative.", e.getMessage());
        }
    }

    @Test
    public void createHorseWithNegativeDistance() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Horse("Name", 2.0, -2.0));
    }

    @Test
    public void createHorseWithNegativeDistanceAndCheckExceptionMessage() {
        try {
            new Horse("Name", 2.0, -2.0);
        } catch (Exception e) {
            Assertions.assertEquals("Distance cannot be negative.", e.getMessage());
        }
    }

    @Test
    public void checkHorseGetName() {
        Horse horse = new Horse("Pepper", 2.0, 2.0);
        Assertions.assertEquals("Pepper", horse.getName());
    }

    @Test
    public void checkHorseGetSpeed() {
        Horse horse = new Horse("Name", 888.0, 2.0);
        Assertions.assertEquals(888.0, horse.getSpeed());
    }

    @Test
    public void checkHorseGetDistance() {
        Horse horse = new Horse("Name", 2.0, 888.0);
        Assertions.assertEquals(888.0, horse.getDistance());
    }

    @Test
    public void checkHorseGetDistanceWithDefaultValue() {
        Horse horse = new Horse("Name", 2.0);
        Assertions.assertEquals(0, horse.getDistance());
    }

    @Test
    public void checkMove() {
        Horse horse = new Horse("Name", 2.0, 2.0);
        try (MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)) {
            horse.move();
            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.2, 0.5, 0.9})
    public void checkDistanceFormulae(double randomValue) {
        Horse horse = new Horse("Name", 2.0, 2.0);
        try (MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)) {
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(randomValue);
            horse.move();
        }
        double expected = 2.0 + 2.0 * randomValue;
        Assertions.assertEquals(expected, horse.getDistance());
    }

}
