import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;


@ExtendWith(MockitoExtension.class)
public class HippodromeTest {

    List<Horse> horses = new ArrayList<>();

    @Test
    public void testConstructorWithNull() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(null));
    }

    @Test
    public void testConstructorWithNullWithException() {
        Throwable exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(null));
        Assertions.assertEquals("Horses cannot be null.", exception.getMessage());

    }

    @Test
    public void testConstructorWithEmptyList() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(horses));
    }

    @Test
    public void testConstructorWithEmptyListWithException() {
        Throwable exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(horses));
        Assertions.assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    public void testGetHorses() {
        for (int i = 1; i <= 30; i++) {
            horses.add(new Horse("Horse" + i, i + 10));
        }
        Assertions.assertEquals(horses, new Hippodrome(horses).getHorses());
    }


    @Test
    public void testMove() {
        for (int i = 1; i <= 50; i++) {
            Horse horseMock = Mockito.mock(Horse.class);
            horses.add(horseMock);
        }

        new Hippodrome(horses).move();

        for (Horse horseMock : horses) {
            Mockito.verify(horseMock).move();
        }
    }

    @Test
    public void testGetWinner() {
        for (int i = 1; i <= 10; i++) {
            horses.add(new Horse("Horse" + i, i + 10, i * 10));
        }
        Assertions.assertSame(horses.get(9), new Hippodrome(horses).getWinner());
    }
}

