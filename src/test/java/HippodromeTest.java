import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class HippodromeTest {
    @Test
    public void emptyHorseException() {
        try {
            assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
        } catch (IllegalArgumentException ex) {
            assertEquals("Horses cannot be empty.", ex.getMessage());
        }

    }

    @Test
    public void getHorses() {
        List<Horse> horses = new ArrayList<>();
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
            horses.add(new Horse(name, k * 2, k * 4));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses, hippodrome.getHorses());
    }

    @Test
    public void move() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(mock(Horse.class));
        }
        new Hippodrome(horses).move();

        for (Horse horse : horses
        ) {
            verify(horse).move();
        }
    }

    @Test
    public void getWinner() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            horses.add(new Horse("name" + i, i * 2, i * 4));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        Horse horse = horses.stream()
                .max(Comparator.comparing(Horse::getDistance))
                .get();
        assertSame(horse, hippodrome.getWinner());
        Horse horse1 = horses.stream()
                .filter(h -> (h.getName().equals("name9"))).findFirst().orElse(null);
        assertSame(horse1, hippodrome.getWinner());

    }
}
