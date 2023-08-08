import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import ru.javarush.mukhametzyanov.hippodrome.Horse;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

public class HorseTest {

    @Test
    public void horseName_checkNull_throwException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1, 1));
    }

    @Test
    public void horseName_checkNull_exceptionMassageIsEqual() {
        try {
            new Horse(null, 1, 1);
        } catch (IllegalArgumentException exception) {
            assertEquals("Name cannot be null.", exception.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "\t", "\n", "\r", "\f"})
    public void horseName_checkChar_throwException(String argument) {
        assertThrows(IllegalArgumentException.class, () -> new Horse(argument, 1, 1));
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "\t", "\n", "\r", "\f"})
    public void horseName_checkChar_exceptionMassageIsEqual(String argument) {
        try {
            new Horse(argument, 1, 1);
        } catch (IllegalArgumentException exception) {
            assertEquals("Name cannot be blank.", exception.getMessage());
        }
    }

    @Test()
    public void horseSpeed_checkNegative_throwException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("argument", -1, 1));
    }

    @Test
    public void horseSpeed_checkNegative_exceptionMassageIsEqual() {
        try {
            new Horse("argument", -1, 1);
        } catch (IllegalArgumentException exception) {
            assertEquals("Speed cannot be negative.", exception.getMessage());
        }
    }

    @Test
    public void horseDistance_checkNegative_throwException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("argument", 1, -1));
    }

    @Test
    public void horseDistance_checkNegative_exceptionMassageIsEqual() {
        try {
            new Horse("argument", 1, -1);
        } catch (IllegalArgumentException exception) {
            assertEquals("Distance cannot be negative.", exception.getMessage());
        }
    }

    @Test
    public void moveTest_checkMethodParam_paramIsRight() {
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            new Horse("argument", 1, 1).move();
            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.1, 0.2, 0.5, 0.9})
    public void moveTest_checkDistanceValue_valueIsRight(double random) {
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            Horse horse = new Horse("argument", 1, 1);
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(random);
            horse.move();
            assertEquals(1 + 1 * random, horse.getDistance());
        }
    }

    @Test
    public void horseName_checkValue_stringsIsEqual() {
        Horse horse = new Horse("argument", 1, 1);
        assertEquals("argument", horse.getName());
    }
//        try {
//            Field name = Horse.class.getDeclaredField("name");
//            name.setAccessible(true);
//            String nameValue = (String) name.get(horse);
//            assertEquals("value", nameValue);
//        } catch (NoSuchFieldException | IllegalAccessException e) {
//            System.out.println(e.getMessage());
//        }

    @Test
    public void horseSpeed_checkValue_numberIsEqual() {
        Horse horse = new Horse("argument", 1, 1);
        assertEquals(1, horse.getSpeed());
    }

    @Test
    public void horseDistance_checkValue_numberIsEqual() {
        Horse horse = new Horse("argument", 1, 1);
        assertEquals(1, horse.getDistance());

        Horse notDistanceHorse = new Horse("argument", 1);
        assertEquals(0, notDistanceHorse.getDistance());
    }
}