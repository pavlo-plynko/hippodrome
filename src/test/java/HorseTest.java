import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.params.ParameterizedTest;

import static java.util.Objects.isNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class HorseTest {
    //a
    @Test
    void testConstructor_NullName_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 10.5, 0));
    }

    @Test
    void testConstructor_NullName_massage() {

        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 10.5, 0));
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @CsvSource({
            "' ',10.5, 0",
            "'  ',10.5, 0",
            "'   ',10.5, 0",
            "' ',10.5, 0",//tab
            "'  ',10.5, 0",//tab+blank
    })
    void testConstructor_BlankName_ThrowsIllegalArgumentException(String name, double speed, int distance) {
        assertThrows(IllegalArgumentException.class, () -> new Horse(name, speed, distance));
    }

    @ParameterizedTest
    @CsvSource({
            "' ',10.5, 0",
            "'  ',10.5, 0",
            "'   ',10.5, 0",
            "' ',10.5, 0",//tab
            "'  ',10.5, 0",//tab+blank
    })
    void testConstructor_BlankName_message(String name, double speed, int distance) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name, speed, distance));
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    void testConstructor_negativeSpeed_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("NiceHorse", -14, 0));
    }

    @Test
    void testConstructor_negativeSpeed_message() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Horse("NiceHorse", -14, 0));
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    void testConstructor_negativeDistance_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("NiceHorse", 10, -10));
    }

    @Test
    void testConstructor_negativeDistance_message() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Horse("NiceHorse", 10, -10));
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    //b
    @Test
    void getName() {
        String expectedName = "Bucephalus";
        Horse horse = mock(Horse.class);
        when(horse.getName()).thenReturn(expectedName);
        String actualName = horse.getName();
        assertEquals(expectedName, actualName);
    }

    //c
    @Test
    void getSpeed() {
        double expectedSpeed = 10;
        Horse horse = mock(Horse.class);
        when(horse.getSpeed()).thenReturn(expectedSpeed);
        assertEquals(expectedSpeed, horse.getSpeed());
    }

    //d
    @Test
    void getDistance() {
        double expectedDistance = 10;
        Horse horse = mock(Horse.class);
        when(horse.getDistance()).thenReturn(expectedDistance);
        assertEquals(expectedDistance, horse.getDistance());
    }

    @Test
    void getDistance_whenTwoParams() {
        double expectedDistance = 0;
        Horse horse = new Horse("Pupik", 2000);
        assertEquals(expectedDistance, horse.getDistance());

    }

    //e
    @Test
    void move() {
        Horse horse = new Horse("Cezar", 10, 0);
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            horse.move();
            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));

        }
    }

    @Test
    void moveTest() {
        Horse horse = new Horse("Cezar", 10, 0);
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            double randomValue = 0.5;
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(randomValue);
            double expectedDistance = horse.getDistance() + horse.getSpeed() * randomValue;
            horse.move();
            assertEquals(expectedDistance, horse.getDistance());
        }

    }

}

// is null is empty is blank