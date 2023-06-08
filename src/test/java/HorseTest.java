
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.MockedStatic;


import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

class HorseTest {


    @Test
    @DisplayName("exception null name")
    void nullNameValidation() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(null, 10, 20)
        );
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @DisplayName("exception symbol in name")
    @MethodSource("dataFactoryForName")
    void invalidSymbolNameValidation(String name) {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(name, 10, 10)
        );
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    static Stream<String> dataFactoryForName() {
        return Stream.of("", "    ", "\t", "\n");
    }

    @ParameterizedTest
    @DisplayName("exception number data")
    @CsvSource({"-1,1",
            "1,-1",
            "-1,-1"})
    void SpeedAndDistantValidation(double speed, double distance) {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse("mockHorse", speed, distance)
        );
        if (speed < 0) assertEquals("Speed cannot be negative.", exception.getMessage());
        else if (distance < 0) assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    void getName() {
        Horse horse = new Horse("speedy", 1, 2);
        assertEquals("speedy", horse.getName());
    }

    @Test
    void getSpeed() {
        Horse horse = new Horse("mockHorse", 1, 2);
        assertEquals(1, horse.getSpeed());
    }

    @ParameterizedTest
    @CsvSource({"1,2"})
    void getDistanceWithParam(double speed, double distance) {
        Horse horse = new Horse("mockHorse", speed, distance);
        assertEquals(2, horse.getDistance());

    }

    @Test
    void getDistanceWithDefaultDistance() {
        Horse horse = new Horse("mockHorse", 1);
        assertEquals(0, horse.getDistance());
    }

    @Test
    void moveWithGetRandom() {
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)){
          new Horse("staticHorse",15,32).move();
          mockedStatic.verify(()->Horse.getRandomDouble(0.2,0.9));
        }
    }

}