import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

@ExtendWith(MockitoExtension.class)
class HorseTest {


    @Test
    void constructor_ShouldThrowIllegalArgumentException_WhenFirstArgumentIsNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 5, 7));
        String expectedMessage = "Name cannot be null.";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t\t", "\n"})
    void constructor_ShouldThrowIllegalArgumentException_WhenFirstArgumentIsEmptyOrIsBlank(String name ) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name, 5, 7));
        String expectedMessage = "Name cannot be blank.";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }


    @Test
    void constructor_ShouldThrowIllegalArgumentException_WhenSecondArgumentIsLessZero() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Horse("Bulat", -2, 7));
        String expectedMessage = "Speed cannot be negative.";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

    }


    @Test
    void constructor_ShouldThrowIllegalArgumentException_WhenThirdArgumentIsLessZero() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Horse("Bulat", 5, -7));
        String expectedMessage = "Distance cannot be negative.";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

    }


    @Test
    void getName() {
        Horse horse = new Horse("Bulat", 5, 7);
        assertEquals("Bulat", horse.getName());
    }

    @Test
    void getSpeed() {
        Horse horse = new Horse("Bulat", 5, 7);
        assertEquals(5, horse.getSpeed());
    }

    @Test
    void getDistance() {
        Horse horse = new Horse("Bulat", 5, 7);
        assertEquals(7, horse.getDistance());
    }

    @Test
    void move_GetRandomDoubleMethodShouldBeCalled() {
        try(MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            Horse horse = new Horse("Bulat", 5, 7);
            horse.move();
            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }

    }

    @Test
    void move_AssignCorrectValueDistance() {
        try(MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            Horse horse = new Horse("Bulat", 5, 7);
            double value = 0.3;
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(value);
            double speed = horse.getSpeed();
            double distance = horse.getDistance();
            horse.move();
            double expectedDistance = distance + speed * value;
            double actualDistance = horse.getDistance();
            Assertions.assertEquals(expectedDistance,actualDistance);

        }

    }
}