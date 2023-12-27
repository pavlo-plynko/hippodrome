import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class HippodromeTest {
    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Hippodrome_Constructor")
    void hipodromesTest(List<Horse> argument) {
        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(argument));
        if(isNull(argument)) assertEquals("Horses cannot be null.", throwable.getMessage());
        else if (argument.isEmpty()) assertEquals("Horses cannot be empty.", throwable.getMessage());
    }
    @Test
    @DisplayName("getHorses")
    void getHorsesTest() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) horses.add(new Horse(String.format("Horse №%d",i+1), 1.0d, 4.0d));
        Hippodrome hippodrome = new Hippodrome(horses);
        List<Horse> getedHorses = hippodrome.getHorses();
        assertEquals(horses.size(), getedHorses.size());
        for (int i = 0; i < horses.size(); i++) assertEquals(horses.get(i), getedHorses.get(i));
    }

    @Test
    @DisplayName("move")
    void moveTest() {
        List<Horse> mockedHorses = new ArrayList<>();
        for (int i = 0; i < 50; i++) mockedHorses.add(Mockito.mock(Horse.class));
        Hippodrome hippodrome = new Hippodrome(mockedHorses);
        hippodrome.move();
        for (Horse horse:mockedHorses) Mockito.verify(horse, Mockito.times(1)).move();
    }

    @Test
    @DisplayName("getWinner")
    void getWinnerTest() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) horses.add(new Horse(String.format("Horse №%d",i+1), 1.0d, Double.parseDouble(String.format("4.%d",i+1))));
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(4.9d, hippodrome.getWinner().getDistance());
    }
}