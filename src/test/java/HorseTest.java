import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HorseTest {

    @Test
    void shouldBeThrownIllegalExceptionWhenFirstConstructorParameterNull() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
                () -> new Horse(null, 7, 7));

        assertEquals("Name cannot be null.", thrown.getMessage());
    }

    //3
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\t ", "\n"})
    void shouldNotBeFirstParameterEmptyStringOrContainsOnlySpaceSymbols(String parameter) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse(parameter, 7, 7));
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    void shouldBeThrownIllegalExceptionWhenSecondConstructorParameterNegativeNumber(){
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Horse("Amigo", -7, 7));

        assertEquals("Speed cannot be negative.",thrown.getMessage());
    }

    @Test
    void shouldBeThrownIllegalExceptionWhenThirdConstructorParameterNegativeNumber(){
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Horse("Amigo", 7, -7));

        assertEquals("Distance cannot be negative.",thrown.getMessage());
    }

    @Test
    void shouldBeSameNameValueWhenInstanceCreated() throws NoSuchFieldException, IllegalAccessException {
        Horse stepan = new Horse("Stepan", 1, 2);


        Field nameField = stepan.getClass().getDeclaredField("name");
        nameField.setAccessible(true);
        String name = (String) nameField.get(stepan);

        assertEquals(name, stepan.getName());
    }

    @Test
    void shouldBeSameSpeedValueWhenInstanceCreated() throws NoSuchFieldException, IllegalAccessException {
        Horse stepan = new Horse("Stepan", 1, 2);

        Field speedField = stepan.getClass().getDeclaredField("speed");
        speedField.setAccessible(true);
        Double speed = (Double) speedField.get(stepan);

        assertEquals(speed, stepan.getSpeed());
    }

    @Test
    void shouldBeSameDistanceValueWhenInstanceCreated() throws NoSuchFieldException, IllegalAccessException {
        Horse stepan = new Horse("Stepan", 1, 2);

        Field distanceField = stepan.getClass().getDeclaredField("distance");
        distanceField.setAccessible(true);
        Double distance = (Double) distanceField.get(stepan);

        assertEquals(stepan.getDistance(), distance);
    }

    @Test
    void shouldReturnDistanceValueZeroWhenTwoParametersInConstructor() {
        Horse stepan = new Horse("Stepan", 1);

        assertEquals(0,stepan.getDistance());
    }

    @Test
    void shouldMethodMoveCallMethodGetRandomDouble() {

        try (MockedStatic<Horse> horse = Mockito.mockStatic(Horse.class)) {
            new Horse("Goose", 4, 9).move();
            horse.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.1,0.5,0.9})
    void shouldCorrectlyCountDistance(Double value){
        try(MockedStatic<Horse> horseMock = Mockito.mockStatic(Horse.class)){
            Horse horse =new Horse("Voop", 2,3);
            horseMock.when(()->Horse.getRandomDouble(0.2,0.9)).thenReturn(value);

            horse.move();
            assertEquals(3 + 2 * value,horse.getDistance());
        }
    }
}
