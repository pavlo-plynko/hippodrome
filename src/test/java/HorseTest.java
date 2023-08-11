import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Any;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Random;


@ExtendWith(MockitoExtension.class)
public class HorseTest {
    @Test
    void constructorWithNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Horse(null, 12, 12));
    }

    @Test
    void constructorMessageWithNull() {
        Throwable exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Horse(null, 12, 12);
                }
        );
        Assertions.assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "  ", "    "})
    void constructorWithEmptySymbol(String argument) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Horse(argument, 12, 12));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "  ", "    "})
    void constructorMessageWithEmptySymbol(String argument) {
        Throwable exception =
                Assertions.assertThrows(IllegalArgumentException.class, () -> new Horse(argument, 12, 12));
        Assertions.assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    void constructorExceptionNegativeSpeed() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Horse("ok", -12, 12));
    }

    @Test
    void constructorMessageNegativeSpeed() {
        Throwable exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Horse("ok", -12, 12);
                }
        );
        Assertions.assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    void constructorExceptionNegativeDistance() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Horse("ok", 12, -12));
    }

    @Test
    void constructorMessageNegativeDistance() {
        Throwable exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Horse("ok", 12, -12);
                }
        );
        Assertions.assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    void testGetName() {
        Horse horse = new Horse("HorseName", 12, 12);
        Assertions.assertEquals("HorseName", horse.getName());
    }

    @Test
    void testGetSpeed() {
        Horse horse = new Horse("HorseName", 12, 15);
        Assertions.assertEquals(12, horse.getSpeed());
    }

    @Test
    void testGetDistance() {
        Horse horse = new Horse("HorseName", 12, 15);
        Assertions.assertEquals(15, horse.getDistance());
    }

    @Test
    public void testMoveRandomBorder() {
        Horse horse = new Horse("Ok", 13, 12);
        try (MockedStatic mockStatic = Mockito.mockStatic(Horse.class)) {
            horse.move();
            mockStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }
    @Test
   public void testMoveresult() {
       Horse horse = new Horse("13", 10, 5);
           try (MockedStatic mockStatic = Mockito.mockStatic(Horse.class)) {
              mockStatic.when(() -> Horse.getRandomDouble(0.2,0.9)).thenReturn(0.5);
               horse.move();
               Assertions.assertEquals((5+10*0.5),horse.getDistance());
        }
    }


}



