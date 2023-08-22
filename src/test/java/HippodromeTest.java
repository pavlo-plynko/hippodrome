import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class HippodromeTest {
    Hippodrome hippodrome;


    @Nested
    class ConstructorTests {
        @Test
        void constructor_ShouldReturnIllegalArgumentExceptionWhithCurrectMessage_WhenArgumentIsNull() {
            Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
            assertEquals("Horses cannot be null.", exception.getMessage());
        }

        @Test
        void constructor_ShouldReturnIllegalArgumentExceptionWhithCurrectMessage_WhenArgumentIsEmptyList() {
            Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
            assertEquals("Horses cannot be empty.", exception.getMessage());
        }
    }

    @Test
    void getHorses_ShouldReturnListWhichPassedConstructor() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            double speed = Math.random() * 500;
            double distance = Math.random() * 2000;
            horses.add(new Horse("Horse №" + i, speed, distance));
        }
        hippodrome = new Hippodrome(horses);
        for (int i = 0; i < 30; i++) {
            assertSame(horses.get(i), hippodrome.getHorses().get(i));
        }
    }

    @Test
    void move() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++)
            horses.add(Mockito.mock(Horse.class));
        hippodrome = new Hippodrome(horses);
        hippodrome.move();
        for (int i = 0; i < 50; i++)
            Mockito.verify(hippodrome.getHorses().get(i)).move();
    }

    @Test
    void getWinner() {
        List<Horse> horses = new ArrayList<>();
        horses.add(new Horse("Plotva", 200, 1000));
        horses.add(new Horse("Sovraska", 100, 800));
        horses.add(new Horse("Milka", 500, 1001));
        hippodrome = new Hippodrome(horses);
        assertSame(horses.get(2), hippodrome.getWinner());
    }
}