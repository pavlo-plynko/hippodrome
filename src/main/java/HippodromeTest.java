import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class HippodromeTest {
    @Mock
    Hippodrome hippodrome;

    @Test
    public void argumentIsNull() {
        when(hippodrome.getHorses() == null).thenThrow(IllegalArgumentException.class);
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }

    @Test
    public void exceptionMessage() {
        String messageWhenConstructorNull = "Horses cannot be null.";
        when(hippodrome.getHorses() == null).thenThrow(new IllegalArgumentException(messageWhenConstructorNull));
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Hippodrome(null);
        });
        assertEquals(messageWhenConstructorNull, exception.getMessage());
    }

    @Test
    public void emptyList() {
        when(hippodrome.getHorses().isEmpty()).thenThrow(IllegalArgumentException.class);
        assertThrows(IllegalArgumentException.class, () ->
                new Hippodrome(new ArrayList<Horse>(0)));
    }

    @Test
    public void messageWhenEmptyList() {
        String messageWhenHorsesEmpty = "Horses cannot be empty.";
        when(hippodrome.getHorses().isEmpty()).thenThrow(new IllegalArgumentException(messageWhenHorsesEmpty));
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new Hippodrome(new ArrayList<Horse>(0)));
        assertEquals(exception.getMessage(), messageWhenHorsesEmpty);
    }

    @Test
    public void testGetHorses() {
        for (int i = 0; i < 30; i++) {
            hippodrome.getHorses().add(new Horse("horse " + i, 50 + i));
        }

        List<Horse> horses = hippodrome.getHorses();
        for (int i = 0; i < hippodrome.getHorses().size(); i++) {
            assertSame(hippodrome.getHorses().get(i), horses.get(i));
        }
    }

    @Test
    public void testMoveHorse() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            Horse horse = Mockito.mock(Horse.class);
            hippodrome.getHorses().add(horse);
            hippodrome.move();
        }
        for (int i = 0; i < hippodrome.getHorses().size(); i++) {
            verify(hippodrome.getHorses().get(i)).move();
        }
    }

    @Test
    public void testGetWinner() {
        List<Horse> horses = new ArrayList<>();
        var kolia = new Horse("kolia", 200);
        Horse vasia = new Horse("vasia", 100);
        horses.add(kolia);
        horses.add(vasia);
        when(hippodrome.getWinner()).thenReturn(horses.get(0));
        assertSame(hippodrome.getWinner(), kolia);
    }

}
