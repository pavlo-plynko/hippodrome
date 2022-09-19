import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

public class HorseTest {

    @Test
    public void nullNameException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1, 1));
    }

    @Test
    public void nullNameMessage() {
        try {
            new Horse(null, 1, 1);
            fail();
        } catch (Exception e) {
            assertEquals("Name cannot be null.", e.getMessage());

        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "name", "    ", "\t\t\t\t", "\n\n\n\n", "\b\b\b", "\n\n\n", "Horse"})
    public void blankNameException(String name) {
        try {
            new Horse(name, 1, 1);
            if (name.isBlank()) {
                fail();
            }
        } catch (IllegalArgumentException ex) {
            assertEquals("Name cannot be blank.", ex.getMessage());
        }
    }

    @Test
    public void negativeSpeedExceptionTest() {
        assertThrows(IllegalArgumentException.class,
                () -> new Horse("name", -2, 2));
    }

    @Test
    public void negativeSpeedExceptionMessageTest() {
        String expectedErrorMessage = "Speed cannot be negative.";
        try {
            new Horse("name", -2, 2);
        } catch (IllegalArgumentException e) {
            assertEquals(expectedErrorMessage, e.getMessage());
        }
    }

    @Test
    public void negativeDistanceExceptionTest() {
        assertThrows(IllegalArgumentException.class,
                () -> new Horse("name", 2, -2));
    }

    @Test
    public void negativeDistanceExceptionMessageTest() {
        String expectedErrorMessage = "Distance cannot be negative.";
        try {
            new Horse("name", 2, -2);
        } catch (IllegalArgumentException e) {
            assertEquals(expectedErrorMessage, e.getMessage());
        }
    }


    @Test
    public void getName() {
        char[] chars = new char[52];
        char i = 'a';
        int j = 0;
        for (; i <= 'z'; i++, j++) {
            chars[j] = i;
            chars[j + 26] = (char) (i - 32);
        }
        String stringForName = new String(chars);
        for (int k = 0; k < 30; k++) {
            String name = stringForName.substring(k, k + 6);
            Horse horse = new Horse(name, 1, 1);
            assertEquals(name, horse.getName());
        }
    }

    @Test
    public void getSpeed() {
        for (double i = 1.0; i < 5; i++) {
            Horse horse = new Horse("name", i, 1);
            assertEquals(i, horse.getSpeed());
        }
    }

    @Test
    public void getDistance() {
        Horse horse = new Horse("name", 1, 1);
        assertEquals(1, horse.getDistance());
        Horse horse1 = new Horse("name", 1);
        assertEquals(0, horse1.getDistance());
    }

    @Test
    void moveUsesGetRandom() {
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            new Horse("horse", 25, 200).move();
            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.1, 0, 2, 0.5, 0.9, 99.99, 0.0})
    public void move(double random) {
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            Horse horse = new Horse("name", 31, 283);

            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(random);
            horse.move();
            assertEquals(283 + 31 * random, horse.getDistance());
        }
    }
}
