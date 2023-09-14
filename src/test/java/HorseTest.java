import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HorseTest {
@Test
    public void nameHorseNullException(){
            assertThrows(IllegalArgumentException.class, ()-> new Horse(null,2,2));
}
@Test
    public void whenHorseMessageNullName(){
   try {
       new Horse(null,2,2);
   }catch (IllegalArgumentException e){
       assertEquals("Name cannot be null.",e.getMessage());
   }
}
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t","\n" })
    public void whenNameCannotBeBlankException(String name){
        assertThrows(IllegalArgumentException.class, ()-> new Horse(name,2,2));
}
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t","\n" })
    public void whenNameCannotBeBlankMessage(String name){
       try {
           new Horse(name,2,2);
       }catch (Exception e){
           assertEquals( "Name cannot be blank.",e.getMessage());
       }
    }
    @Test
    public void whenSpeedNegativeException(){
        assertThrows(IllegalArgumentException.class, ()-> new Horse("null",-1,2));
    }
    @Test
    public void whenSpeedNegativeMessage(){
        try {
            new Horse("null",-1,2);
        }catch (IllegalArgumentException e){
            assertEquals("Speed cannot be negative.",e.getMessage());
        }
    }
    @Test
    public void whenDistanceNegativeException(){
        assertThrows(IllegalArgumentException.class, ()-> new Horse("null",2,-1));
    }
    @Test
    public void whenDistanceNegativeMessage(){
        try {
            new Horse("null",2,-1);
        }catch (IllegalArgumentException e){
            assertEquals("Distance cannot be negative.",e.getMessage());
        }
    }
    @Test
    public void whenGetNameIsString()  {
    Horse horse =new Horse("name",2,2);

        assertEquals("name",horse.getName());
    }
    @Test
    public void whenGetSpeed() {
        Horse horse =new Horse("name",2,2);

        assertEquals(2,horse.getSpeed());
    }
    @Test
    public void whenGetDistance() {
        Horse horse =new Horse("name",2,2);

        assertEquals(2,horse.getDistance());
    }

    @Test
    public void returnZeroDistanceByDefault(){
        Horse horse =new Horse("name",2);

        assertEquals(0,horse.getDistance());
    }
    @Test
    void moveUsesGetRandom () {
        try (MockedStatic< Horse> mockStatic =  Mockito.mockStatic( Horse.class)) {
            //добавляем правило
             new Horse("null",2,2).move();
           mockStatic.verify(()->Horse.getRandomDouble(0.2, 0.9));
        }
    }
    @ParameterizedTest
    @ValueSource(doubles = {0.1,0.2,0.5,0.9,1.0,999.999,0.0})
    public void move(double d){
        try (MockedStatic< Horse> mockStatic =  Mockito.mockStatic( Horse.class)) {

           Horse horse= new Horse("null",1,2);
            mockStatic.when(()->Horse.getRandomDouble(0.2, 0.9)).thenReturn(d);

            horse.move();

            assertEquals(2+1*d,horse.getDistance());
        }
    }
}
