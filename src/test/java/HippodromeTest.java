import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class HippodromeTest {
    @Test
    public void constructor_ShouldThrowException_WhenHorsesAreNull() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }

    @Test
    public void constructor_ShouldThrowExceptionWithMessage_WhenHorsesAreNull() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    public void constructor_ShouldThrowException_WhenHorsesAreEmpty() {
        List<Horse> horses = new ArrayList<>();
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome((horses)));
    }

    @Test
    public void constructor_ShouldThrowExceptionWithMessage_WhenHorsesAreEmpty() {
        List<Horse> horses = new ArrayList<>();
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(horses));
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    public void shouldGetHorses() {
        List<Horse> horses = IntStream.range(0, 30)
                .mapToObj(i -> new Horse("Pedro" + (i + 1), 3))
                .collect(Collectors.toList());
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses, hippodrome.getHorses());
    }

    @Test
    public void shouldMove() {
        List<Horse> horses = IntStream.range(0, 50)
                .mapToObj(i -> Mockito.mock(Horse.class))
                .collect(Collectors.toList());
        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();
        for (Horse horse : horses) {
            Mockito.verify(horse, Mockito.times(1)).move();
        }
    }

    @Test
    public void shouldGetWinner() {
        List<Horse> horses = List.of(
                new Horse("Perdro1", 3, 2),
                new Horse("Perdro2", 1, 6),
                new Horse("Perdro3", 7, 1),
                new Horse("Perdro4", 3, 16),
                new Horse("Perdro5", 1, 4),
                new Horse("Perdro6", 2, 7),
                new Horse("Perdro7", 5, 10)
        );
        Hippodrome hippodrome = new Hippodrome(horses);
        Horse actualWinner = hippodrome.getWinner();
        Horse expectedWinner = horses.stream()
                .max(Comparator.comparing(Horse::getDistance))
                .get();
        assertEquals(expectedWinner, actualWinner);
    }
}