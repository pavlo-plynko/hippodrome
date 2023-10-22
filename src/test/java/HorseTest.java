import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.OngoingStubbing;
import org.powermock.core.classloader.annotations.PrepareForTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
@PrepareForTest
public class HorseTest {
    public static final String MESSAGE_SECOND_ARGUMENT_NEGATIVE = "Speed cannot be negative.";
    public static final String MESSAGE_THIRD_ARGUMENT_NEGATIVE = "Distance cannot be negative.";
    @Mock
    private Horse horse;

    @Test
    public void constructorNullableThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () ->
                new Horse(null, 0));
    }

    @Test
    public void illegalArgumentExceptionMessage() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Horse(null, 0);
        });
        assertEquals(exception.getMessage(), ("Name cannot be null."));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\n", "\t", "\r", " \t\n\r"})
    public void argumentSpace(String arg) {
        assertThrows(IllegalArgumentException.class, () ->
                new Horse(arg, 0));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\n", "\t", "\r", " \t\n\r"})
    public void messageWhenArgumentSpace(String arg) {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new Horse(arg, 0));

        assertEquals(exception.getMessage(), "Name cannot be blank.");
    }

    @Test
    public void secondArgumentNegative() {
        assertThrows(IllegalArgumentException.class, () ->
                new Horse("vasia", -2));
    }

    @Test
    public void messageWhenSecondArgumentNegative() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Horse("vasia", -2);
        }, MESSAGE_SECOND_ARGUMENT_NEGATIVE);
    }

    @Test
    public void thirdArgumentNegative() {
        assertThrows(IllegalArgumentException.class, () ->
                new Horse("vasia", 1, -2));
    }

    @Test
    public void messageWhenThirdArgumentNegative() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Horse("vasia", 5, -2);
        }, MESSAGE_THIRD_ARGUMENT_NEGATIVE);
    }

    @Test
    public void getHorseName() {
        var horse1 = new Horse("vasia", 1, 1);
        assertEquals(horse1.getName(), "vasia");
    }

    @Test
    public void getHorseSpeed() {
        when(horse.getSpeed()).thenReturn(50.0);
        assertEquals(horse.getSpeed(), 50);
    }

    @Test
    public void getHorseDistance() {
        when(horse.getDistance()).thenReturn(200.0);
        assertEquals(horse.getDistance(), 200);
    }

    @Test
    public void distanceWhenHorseWithTwoArgs() {
        when(horse.getDistance()).thenReturn(0.0);
        assertEquals(horse.getDistance(), 0);
    }

    @Test
    public void verifyGetRandomDouble() {
        try (MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)) {
            Horse horse = new Horse("vasia", 20, 200);
            horse.move();
            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));

        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9} )
    public void verifyDistance(double randomValue) {
        double distance=100;
        double speed=20;
        double expect=distance+speed*randomValue;
        try (MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)) {
           mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(randomValue);
            Horse h = new Horse("vasia", speed, distance);
            h.move();
            assertEquals(expect, distance+(speed*randomValue));
        }
    }


}
