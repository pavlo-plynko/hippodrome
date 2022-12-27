import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.lang.reflect.Field;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

class HorseTest {
    final String HORSE_NAME = "horse";
    final double CURRENT_SPEED = 2.0;
    final double CURRENT_DISTANCE = 30.0;

    @Test
    void testConstructor_whenFirstArgument_isNull(){
        String message = "Name cannot be null.";
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse(null, CURRENT_SPEED));
        assertEquals(message,exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"","  ","\t","\n"})
    void test_when_first_parameter_is_empty_or_space(String horsName){
        String message = "Name cannot be blank.";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse(horsName, CURRENT_SPEED));
        assertEquals(message,exception.getMessage());
    }

    @Test
    void test_when_the_second_parameter_in_the_constructor_is_negative(){
        String message = "Speed cannot be negative.";
        Throwable exception = assertThrows(IllegalArgumentException.class,
                ()-> new Horse(HORSE_NAME,-1));
        assertEquals(message,exception.getMessage());
    }

    @Test
    void test_when_the_third_parameter_in_the_constructor_is_negative(){
        String message = "Distance cannot be negative.";
        Throwable exception = assertThrows(IllegalArgumentException.class,
                ()-> new Horse(HORSE_NAME,CURRENT_SPEED,-1));
        assertEquals(message,exception.getMessage());
    }

    @Test
    void getName(){
        Horse horse = new Horse(HORSE_NAME,CURRENT_SPEED);
        assertEquals(HORSE_NAME,horse.getName());
    }

    @Test
    void getSpeed() throws NoSuchFieldException, IllegalAccessException {
        Horse horse = new Horse(HORSE_NAME,CURRENT_SPEED);
        assertEquals(CURRENT_SPEED,horse.getSpeed());
    }

    @Test
    void getDistance() {
        Horse horse = new Horse(HORSE_NAME,CURRENT_SPEED,CURRENT_DISTANCE);
        assertEquals(horse.getDistance(),CURRENT_DISTANCE);

        Horse horse1 = new Horse(HORSE_NAME,CURRENT_SPEED);
        assertEquals(0,horse1.getDistance());
    }

    @Test
    void move() {
        try(MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)){
            new Horse(HORSE_NAME,CURRENT_SPEED,CURRENT_DISTANCE).move();
            mockedStatic.verify(()-> Horse.getRandomDouble(0.2,0.9));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.1,0.3,0.7,1.5,4,6})
    void is_the_distance_calculated_by_the_formula(double number){
        try(MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            Horse horse = new Horse(HORSE_NAME,CURRENT_SPEED,CURRENT_DISTANCE);
            mockedStatic.when(()-> Horse.getRandomDouble(0.2,0.9)).thenReturn(number);

            horse.move();
            assertEquals(CURRENT_DISTANCE + CURRENT_SPEED * number, horse.getDistance());
        }
    }
}