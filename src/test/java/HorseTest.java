import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
public class HorseTest {

    @Test
    public void checkIArgEThrowsInConstructorWithNull() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Horse(null,100,100);
                }
        );
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @MethodSource("argsProviderFactory")
    public void checkIArgEThrowsInConstructorWithEmptyString(String argument) {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Horse(argument,100,100);
                }
        );
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    static Stream<String> argsProviderFactory() {
        return Stream.of("", "  ",  "\t");
    }

    @Test
    public void checkIArgEThrowsInConstructorWithNegativeSpeed() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Horse("Name",-1,100);
                }
        );
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    public void checkIArgEThrowsInConstructorWithNegativeDistance() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Horse("Name",100,-1);
                }
        );
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    public void checkReturnParameters() {
        Horse horse = new Horse("Blaik",100,1000);
        assertAll(
                () -> assertEquals("Blaik",horse.getName()),
                () -> assertEquals(100,horse.getSpeed()),
                () -> assertEquals(1000,horse.getDistance())
        );
    }

    @Test
    public void checkReturnZeroDistance() {
        Horse horse = new Horse("Blaik",100);
        assertEquals(0,horse.getDistance());
    }

    @Test
    public void checkValueDistance() {
        try (MockedStatic<Horse> horseMock =  Mockito.mockStatic(Horse.class)) {
            horseMock.when(() -> Horse.getRandomDouble(0.2, 0.9))
                    .thenReturn(1.5);
            Horse horse = new Horse("Name", 100, 1000);
            horse.move();
            horseMock.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = { 1, 2, 3 })
    public void checkCallGetRandomDoubleIntoMove(double randomValue) {
        try (MockedStatic<Horse> horseMock =  Mockito.mockStatic(Horse.class)) {
            horseMock.when(() -> Horse.getRandomDouble(0.2, 0.9))
                    .thenReturn(randomValue);
            Horse horse = new Horse("Name", 1, 1);
            double expected = 1 + 1 * randomValue;
            horse.move();
            assertEquals(expected,horse.getDistance(), 0.001);
        }
    }
}
