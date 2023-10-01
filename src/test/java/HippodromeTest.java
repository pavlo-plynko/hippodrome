import static common.HorseParams.DEFAULT_DISTANCE;
import static common.HorseParams.DEFAULT_NAME;
import static common.HorseParams.DEFAULT_SPEED;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class HippodromeTest {
    private static final int MAX_HORSES_LIMIT = 30;
    static Stream<Arguments> constructorParamsWithExceptionProvider() {
        return Stream.of(Arguments.of(null, "Horses cannot be null."),
                         Arguments.of(new ArrayList<>(), "Horses cannot be empty."));
    }

    @ParameterizedTest
    @MethodSource("constructorParamsWithExceptionProvider")
    @DisplayName("Check Hippodrome constructor with invalid params throws IllegalArgumentException")
    void testShouldReturnIllegalArgumentExceptionForInvalidParam(List<Horse> horses, String errMsg) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(horses));
        assertEquals(errMsg, exception.getMessage());
    }


    @Test
    @DisplayName("Check Hippodrome getHorses returns exactly the same horses list as set on constructor step")
    void testShouldReturnTheSameHorsesListForHippodrome() {
        List<Horse> horses = getHorsesList();
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses, hippodrome.getHorses());
    }

    @Test
    @DisplayName("Check Hippodrome move calls method for all horses in the list")
    void testShouldCallMoveMethodForAllHorses() {
        int horseLimit = 50;
        List<Horse> horses =  new LinkedList<>();
        for (int i = 0; i < horseLimit; i++) {
            horses.add(mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();
        for(Horse horse : hippodrome.getHorses()){
            verify(horse,times(1)).move();
        }
    }

    @Test
    @DisplayName("Check Hippodrome winner() return the horse with the biggest distance")
    void testShouldReturnHorseWithTheBiggestDistance() {
        List<Horse> horses = getHorsesList();
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(DEFAULT_DISTANCE + MAX_HORSES_LIMIT - 1, hippodrome.getWinner().getDistance());
    }


    private static List<Horse> getHorsesList() {
        List<Horse> horses =  new LinkedList<>();
        for (int i = 0; i < MAX_HORSES_LIMIT; i++) {
            horses.add(new Horse(DEFAULT_NAME + i,
                                 DEFAULT_SPEED + i,
                                 DEFAULT_DISTANCE + i ));
        }
        return horses;
    }
}
