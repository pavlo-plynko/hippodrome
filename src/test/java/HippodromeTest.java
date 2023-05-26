
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class HippodromeTest {

    @Test
    void IfListIsNullGetIllegalArgumentException() {
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Hippodrome(null);
        });
        assertEquals("Horses cannot be null.", thrown.getMessage());
    }

    @Test
    void IfListIsEmptyGetIllegalArgumentException() {
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Hippodrome(new ArrayList<>());
        });
        assertEquals("Horses cannot be empty.", thrown.getMessage());
    }

    @Test
    void moveAllHorses() {
        List<Horse> horses = new ArrayList<>();
        Horse horseMock = Mockito.mock(Horse.class);
        for (int i = 1; i < 51; i++) {
            horses.add(horseMock);
        }
        new Hippodrome(horses).move();

        Mockito.verify(horseMock, Mockito.times(50)).move();
    }

    @Test
    void getWinner() {
        Horse horseJim = new Horse("Jim", 5.1, 8.3);
        Horse horseAngela = new Horse("Angela", 2.3, 5.0);
        Horse horseMichael = new Horse("Michael", 9.9, 2.1);

        Hippodrome expected = new Hippodrome(List.of(horseJim, horseAngela, horseMichael));

        assertSame(horseJim, expected.getWinner());
    }
}