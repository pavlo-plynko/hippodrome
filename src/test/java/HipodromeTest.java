import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.util.List.of;
import static org.mockito.Mockito.times;

public class HipodromeTest {
    @Test
    public void Hippodrome_HorseList_CannotBe_Null() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Hippodrome(null);
        });
        Assertions.assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    public void Hippodrome_HorseList_CannotBe_Empty() {
        List<Horse> horses = List.of();
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Hippodrome(horses);
        });
        Assertions.assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    public void Hippodrome_GetHorse_List_Untachable(){
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("ID"+i,i));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        Assertions.assertEquals(horses,hippodrome.getHorses());
    }

    @Test
    public void Hippodrome_getWinner(){
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            horses.add(new Horse("ID"+i,i,i));
        }
        Assertions.assertEquals(horses.get(4),horses.stream()
                .max(Comparator.comparing(Horse::getDistance))
                .get());
    }

    @Test
    public void Hippodrome_HorseMoveTesting(){
        List<Horse> horses = Mockito.spy(new ArrayList<>());
        for (int i = 0; i < 49; i++) {
            horses.add(new Horse("ID"+i,i,i));
        }
        Mockito.verify(Mockito.spy(new Hippodrome(horses)),times(50)).move();
    }
}
