import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.powermock.core.classloader.annotations.PrepareForTest;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


 @RunWith(MockitoJUnitRunner.class)
 @PrepareForTest
public class HorseTest {
    public static final String MESSAGE_SECOND_ARGUMENT_NEGATIVE="Speed cannot be negative.";
    public static final String MESSAGE_THIRD_ARGUMENT_NEGATIVE="Distance cannot be negative.";
    @Mock
    private Horse horse;

    @Test
    public void isConstructorNullable() {
     when(horse.getName()==null).thenThrow(IllegalArgumentException.class);
     assertThrows(IllegalArgumentException.class, ()->
              new Horse(null, 0));
    }
    @Test
    public void testExceptionMessage() {
       Exception exception= assertThrows(IllegalArgumentException.class, ()-> {
                new Horse(null,0);
        });
       assertEquals(exception.getMessage(), ("Name cannot be null."));
    }

  @ParameterizedTest
  @ValueSource(strings = {"", " ", "\n", "\t", "\r", " \t\n\r"})
    public void testArgumentSpace(String arg) {
    assertThrows(IllegalArgumentException.class, ()->
            new Horse(arg, 0));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\n", "\t", "\r", " \t\n\r"})
    public void testMessageIfArgumentSpace(String arg) {
        Exception exception=assertThrows(IllegalArgumentException.class, ()->
                new Horse(arg, 0));

        assertEquals(exception.getMessage(), "Name cannot be blank.");
    }

    @Test
    public void secondArgumentNegative () {
      when(horse.getSpeed()<0).thenThrow(IllegalArgumentException.class);
      assertThrows(IllegalArgumentException.class, ()->
        new Horse("sad", -2));
    }
    @Test
    public void messageIfSecondArgumentNegative () {
        when(horse.getSpeed()<0).thenThrow(new IllegalArgumentException(MESSAGE_SECOND_ARGUMENT_NEGATIVE));
        assertThrows(IllegalArgumentException.class, ()-> {
            new Horse("dsa", -2);
        }, MESSAGE_SECOND_ARGUMENT_NEGATIVE);
    }
    @Test
    public void thirdArgumentNegative () {
        when(horse.getDistance()<0).thenThrow(IllegalArgumentException.class);
        assertThrows(IllegalArgumentException.class, ()->
                new Horse("sad", 1,-2));
    }
    @Test
    public void messageIfThirdArgumentNegative () {
        when(horse.getSpeed()<0).thenThrow(new IllegalArgumentException(MESSAGE_THIRD_ARGUMENT_NEGATIVE));
        assertThrows(IllegalArgumentException.class, ()-> {
            new Horse("dsa", 5, -2);
        }, MESSAGE_THIRD_ARGUMENT_NEGATIVE);
    }
    @Test
     public void getHorseName() {
        when(horse.getName()).thenReturn("Vasia");
        assertEquals(horse.getName(), "Vasia");
    }
     @Test
     public void getHorseSpeed() {
         when(horse.getSpeed()).thenReturn(50.0);
         assertEquals(horse.getSpeed(), 50);
     }
     @Test
     public void getHorseDistance() {
        when(horse.getDistance()).thenReturn(200.0);
         assertEquals(horse.getDistance(), 200);
     }
     @Test
     public void distanceWhenHorseWithTwoArgs() {
         when(horse.getDistance()).thenReturn(0.0);
         assertEquals(horse.getDistance(), 0);
     }
     //  assertEquals(Horse.getRandomDouble(0.5, 0.5), 0.5);
     @Test
     public void verifyGetRandomDouble() {
        try(MockedStatic<Horse> mockedStatic= Mockito.mockStatic(Horse.class)) {
            mockedStatic.when(()->Horse.getRandomDouble(0.2,0.9)).thenReturn(0.5);
            new Horse("sd", 20, 200).move();
              mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));

        }
     }
     @Test
     public void verifyDistance() {
         try(MockedStatic<Horse> mockedStatic= Mockito.mockStatic(Horse.class)) {
             mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(0.5);

             Horse h = new Horse("sd", 20, 100);
             assertEquals( h.getDistance() * h.getSpeed()*Horse.getRandomDouble(0.2,0.9), 20*100*0.5);
         }
     }



 }
