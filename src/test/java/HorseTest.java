import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;


class HorseTest {
    @Test
    public void constructor_NullNameParam_ThrowIllegalArgumentException() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(null,1,2));

        assertEquals( "Name cannot be null.",exception.getMessage());
    }
    @ParameterizedTest
    @ValueSource(strings = {" ","  ","\n","\n\n","\t","\t\t","\t \t"})
    public void constructor_EmptyNameParam_ThrowIllegalArgumentException( String name){
        String expectedMessage = "Name cannot be blank.";
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(name,1,2));

        assertEquals(expectedMessage,exception.getMessage());
    }
    @Test
    public void constructor_NegativeSpeedParam_ThrowIllegalArgumentException() {
        String expectedMessage = "Speed cannot be negative.";
        String nameTest = "name";
        int speed = -7;
        int distance = 6;
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(nameTest,speed,distance));

        assertEquals( expectedMessage,exception.getMessage());
    }
    @Test
    public void constructor_NegativeDistanceParam_ThrowIllegalArgumentException() {
        String expectedMessage = "Distance cannot be negative.";
        String nameTest = "name";
        int speed = 7;
        int distance =-6;
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(nameTest,speed,distance));

        assertEquals( expectedMessage,exception.getMessage());

    }
    @Test
    void getName_ReturnsName() {
        String nameTest = "name";
        double speed = 7;
        double distance = 6;
        Horse horse = new Horse(nameTest,speed,distance);
        String activeName = horse.getName();
        assertEquals(nameTest,activeName);
    }

    @Test
    void getSpeed_ReturnsSpeed() {
        String nameTest = "name";
        double speed = 7;
        double distance = 6;
        Horse horse = new Horse(nameTest,speed,distance);
        double activeSpeed = horse.getSpeed();
        assertEquals(speed,activeSpeed);
    }

    @Test
    void getDistance_ReturnsDistance() {
        String nameTest = "name";
        double speed = 8;
        double distance = 5;
        Horse horse = new Horse(nameTest,speed,distance);
        double activeDistance = horse.getDistance();
        assertEquals(distance,activeDistance);
    }

    @Test
    public void move_Call_GetRandomDouble() {
        String nameTest = "name";
        double speed = 9;
        double distance = 4;
        Horse horse = new Horse(nameTest,speed,distance);
        try (MockedStatic< Horse> utilities =mockStatic( Horse.class)){
            horse.move();
            utilities.verify(()->Horse.getRandomDouble(0.2,0.9));
        }

    }
    @ParameterizedTest
    @ValueSource(doubles ={0.2, 0.3, 0.5, 0.6, 0.9})
    public void move_Calculators(double fakeRandomValue){
        double min = 0.2;
        double max = 0.9;
        String nameTest = "name";
        double speed = 2.5;
        double distance = 250;
        Horse horse = new Horse(nameTest,speed,distance);
        double expectedDistance = distance + speed * fakeRandomValue;

        try (MockedStatic<Horse> utilities = mockStatic( Horse.class)){
            horse.move();
            utilities.when(()->Horse.getRandomDouble(min, max)).thenReturn(fakeRandomValue);
        }
          assertEquals(expectedDistance,horse.getDistance());
    }


  
}