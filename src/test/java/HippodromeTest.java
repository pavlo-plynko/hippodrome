import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class HippodromeTest {
    @Test
    public void constructorsHippodrome_NullParameter_ThrowIllegalArgumentException() {
        String expectedMessage = "Horses cannot be null.";
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Hippodrome(null));

        assertEquals( expectedMessage,exception.getMessage());
    }
     @Test
    public void constructorsHippodrome_EmptyParameter_ThrowIllegalArgumentException() {
        List<Horse> horses = new ArrayList<>();
        String expectedMessage =  "Horses cannot be empty.";
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Hippodrome(horses));

        assertEquals( expectedMessage,exception.getMessage());
    }

    @Test
    public void getHorses_ReturnsListWithAllHorsesOrders() {
        List<Horse> horseList = new ArrayList<>();
        for(int i=0; i<30;i++){
            horseList.add(new Horse("Horse" + i,i,i));
        }
         Hippodrome hippodrome = new Hippodrome(horseList);
        assertNotNull(hippodrome.getHorses());
        assertEquals(30,hippodrome.getHorses().size());
        assertEquals("Horse0",hippodrome.getHorses().get(0).getName());
        assertEquals("Horse10",hippodrome.getHorses().get(10).getName());
        assertEquals("Horse28",hippodrome.getHorses().get(28).getName());
    }

    @Test
    void move_CallMoveMethodAllHorses() {
        List<Horse> horseList = new ArrayList<>();
        for(int i=0; i<50;i++){
            horseList.add(Mockito.mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(horseList);
        hippodrome.move();
        for(Horse hors: horseList){
            Mockito.verify(hors,Mockito.times(1)).move();
        }
    }

    @Test
   public void getWinner_ReturnsHorsesMaxMeaningDistance() {
        Hippodrome hippodrome = new Hippodrome(List.of(
        new Horse("Horse 10",2,10),
        new Horse("Horse 19",2,19),
        new Horse("Horse 30",2,30)));
        assertEquals("Horse 30",hippodrome.getWinner().getName());
    }
}