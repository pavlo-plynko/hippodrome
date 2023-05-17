import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

class HorseTest {

    static Horse testHorse;

    final static private double TEST_SPEED = 1d;
    final static private double NEG_TEST_SPEED = -1d;
    final static private double TEST_DISTANCE = 2d;
    final static private double NEG_TEST_DISTANCE = -2d;
    final static private String TEST_NAME = "test";

    @BeforeAll
    static void init(){
        testHorse = new Horse(TEST_NAME, TEST_SPEED, TEST_DISTANCE);

    }
    @ParameterizedTest
    @NullAndEmptySource
    public void nullNameShouldThrowIllegalArgumentException(String name) {
        assertThrows(IllegalArgumentException.class, () -> new Horse(name, TEST_SPEED, TEST_DISTANCE));
    }

    @ParameterizedTest
    @NullSource
    public void nullNameShouldThrowTextNameCannotBeNull(String name) {
        try {
            new Horse(name, TEST_SPEED, TEST_DISTANCE);
        } catch (IllegalArgumentException e) {
            assertEquals("Name cannot be null.", e.getMessage());
        }
    }

    @ParameterizedTest
    @EmptySource
    public void blankNameShouldThrowTextNameCannotBeBlank(String name) {
        try {
            new Horse(name, TEST_SPEED, TEST_DISTANCE);
        } catch (IllegalArgumentException e) {
            assertEquals("Name cannot be blank.", e.getMessage());
        }

    }

    @Test
    public void negativeSpeedShouldThrowIllegalArgumentException () {
        assertThrows(IllegalArgumentException.class, () -> new Horse(TEST_NAME, NEG_TEST_SPEED, TEST_DISTANCE));
    }

    @Test
    public void negativeSpeedShouldThrowTextSpeedCannotBeNegative() {
        try {
            new Horse(TEST_NAME, NEG_TEST_SPEED, TEST_DISTANCE);
        } catch (IllegalArgumentException e) {
            assertEquals("Speed cannot be negative.", e.getMessage());
        }
    }

    @Test
    public void negativeDistanceShouldThrowIllegalArgumentException () {
        assertThrows(IllegalArgumentException.class, () -> new Horse(TEST_NAME, TEST_SPEED, NEG_TEST_DISTANCE));
    }

    @Test
    public void negativeDistanceShouldThrowTextDistanceCannotBeNegative() {
        try {
            new Horse(TEST_NAME, TEST_SPEED, NEG_TEST_DISTANCE);
        } catch (IllegalArgumentException e) {
            assertEquals("Distance cannot be negative.", e.getMessage());
        }
    }

    @Test
    public void getNameTest() {
        assertEquals(TEST_NAME, testHorse.getName());
    }

    @Test
    public void getSpeedTest()  {
        assertEquals(TEST_SPEED, testHorse.getSpeed());
    }
    @Test
    public void getDistanceTest()  {
        assertEquals(TEST_DISTANCE, testHorse.getDistance());
    }
    @Test
    public void getDistanceZeroTest() {
       Horse testHorseZeroDist = new Horse(TEST_NAME, TEST_SPEED);
        assertEquals(0, testHorseZeroDist.getDistance());
    }

    @Test
    public void moveShouldInvokeGetRandomDoubleTest(){
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            new Horse(TEST_NAME, TEST_SPEED, TEST_DISTANCE).move();

            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));

        }

    }
    @Test
    void getRandomDoubleCalculateTest() {
        try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
            horseMockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(1d);
            Horse horse = new Horse(TEST_NAME, TEST_SPEED, TEST_DISTANCE);
            double tempDist = horse.getDistance();
            horse.move();
            assertEquals(horse.getSpeed()+tempDist, horse.getDistance());
        }
    }
}