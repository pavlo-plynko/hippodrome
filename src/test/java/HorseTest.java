
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;


import static org.junit.jupiter.api.Assertions.*;

class HorseTest {
    @ParameterizedTest
    @NullSource
    void IfNameIsNullGetIllegalArgumentException(String name) {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
                () -> new Horse(name, 2.3, 1.4));
        assertEquals("Name cannot be null.", illegalArgumentException.getMessage());
    }

    @ParameterizedTest
    @EmptySource
    void IfNameIsEmptyGetIllegalArgumentException(String name) {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
                () -> new Horse(name, 2.3, 1.4));
        assertEquals("Name cannot be blank.", illegalArgumentException.getMessage());

    }

    @ParameterizedTest
    @ValueSource(doubles = {-0.00009, -1500, -Double.MAX_VALUE})
    void IfNegativeSpeedGetIllegalArgumentException(double speed) {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
                () -> new Horse("Вишня", speed, 1.4));
        assertEquals("Speed cannot be negative.", illegalArgumentException.getMessage());
    }

    @ParameterizedTest
    @ValueSource(doubles = {-0.00009, -1500, -Double.MAX_VALUE})
    void IfNegativeDistanceGetIllegalArgumentException(double distance) {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
                () -> new Horse("Вишня", 2.3, distance));
        assertEquals("Distance cannot be negative.", illegalArgumentException.getMessage());
    }

    @Test
    void testConstructor() {
        Horse horse = new Horse("Lion", 3.1, 3.0);
        assertEquals("Lion", horse.getName());
        assertEquals(3.1, horse.getSpeed());
        assertEquals(3.0, horse.getDistance());
    }
    @Test
    void IfTwoParametersInConstructorGetZero() {
        Horse horse = new Horse("Мурка", 0.2);
        assertEquals(0, horse.getDistance());
    }


    @Test
    void getRandomDouble() {
        try (MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)) {
            new Horse("Санчо", 4.2, 10).move();
            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }
}