import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class HipodromeTest {
    @Test
    public void hippodrome_HorseList_CannotBe_Null() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));

        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    public void hippodrome_HorseList_CannotBe_Empty() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Hippodrome(new ArrayList<>());
        });
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    public void hippodrome_GetHorse_List_Untachable(){
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("ID"+i,i));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses,hippodrome.getHorses());
    }

    @Test
    public void hippodrome_getWinner(){
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            horses.add(new Horse("ID"+i,i,i));
        }
        assertEquals(horses.get(4),new Hippodrome(horses).getWinner());
    }

    @Test
    public void hippodrome_HorseMoveTesting(){
        //arrange
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(mock(Horse.class));
        }
        //act
        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();
        //assert
        for (Horse horse: hippodrome.getHorses()) {
            verify(horse,times(1)).move();
        }
    }
}
