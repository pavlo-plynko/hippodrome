import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class HorseTest {
    public Horse horse;

    /**
     * Тест проверяет, что при передаче в конструктор первым параметром null, будет выброшено IllegalArgumentException.
     */
    @Test
    void testConstructor_ShouldThrowException_WhenInputIsNull(){
        assertThrows(IllegalArgumentException.class,() -> new Horse(null,0));
    }
    /**
     * Тест проверяет, что при передаче в конструктор первым параметром null,
     * выброшенное исключение будет содержать сообщение "Name cannot be null."
     */
    @Test
    void testConstructor_ShouldThrowExceptionAndGetMessage_WhenInputIsNull(){
        String actual = "Name cannot be null.";
        try {
            horse = new Horse(null,0);
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(),actual);
        }
    }
    /**
     * Тест проверяет, что при передаче в конструктор первым параметром пустой строки или
     * строки содержащей только пробельные символы (пробел, табуляция и т.д.), будет выброшено IllegalArgumentException.
     */
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\r", "\n"})
    void testConstructor_ShouldThrowException_WhenInputIsEmptyOrWhitespaceSymbols(String name){
        assertThrows(IllegalArgumentException.class,() -> new Horse(name,0));
    }
    /**
     * Тест проверяет, что при передаче в конструктор первым параметром пустой строки
     * или строки содержащей только пробельные символы (пробел, табуляция и т.д.),
     * выброшенное исключение будет содержать сообщение "Name cannot be blank."
     */
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\r", "\n"})
    void testConstructor_ShouldThrowExceptionAndGetMessage_WhenInputIsEmptyOrWhitespaceSymbols(String name){
        String actual = "Name cannot be blank.";
        try {
            horse = new Horse(name,0);
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(),actual);
        }
    }
    /**
     * Тест проверяет, что при передаче в конструктор вторым параметром отрицательного числа,
     * будет выброшено IllegalArgumentException.
     */
    @ParameterizedTest
    @ValueSource(doubles = {-1.0, -Double.MAX_VALUE})
    void testConstructor_ShouldThrowException_WhenInputIsNegativeNumberSpeed(double speed){
        assertThrows(IllegalArgumentException.class,() -> new Horse("Name",speed));
    }
    /**
     * Тест проверяет, что при передаче в конструктор вторым параметром отрицательного числа,
     * выброшенное исключение будет содержать сообщение "Speed cannot be negative.".
     */
    @ParameterizedTest
    @ValueSource(doubles = {-1.0, -Double.MAX_VALUE})
    void testConstructor_ShouldThrowExceptionAndGetMessage_WhenInputIsNegativeNumberSpeed(double speed){
        String actual = "Speed cannot be negative.";
        try {
            horse = new Horse("Name",speed);
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(),actual);
        }
    }
    /**
     * Тест проверяет, что при передаче в конструктор третьим параметром отрицательного числа,
     * будет выброшено IllegalArgumentException.
     */
    @ParameterizedTest
    @ValueSource(doubles = {-1.0, -Double.MAX_VALUE})
    void testConstructor_ShouldThrowException_WhenInputIsNegativeNumberDistance(double distance){
        assertThrows(IllegalArgumentException.class,() -> new Horse("Name",0, distance));
    }
    /**
     * Тест проверяет, что при передаче в конструктор третьим параметром отрицательного числа,
     * выброшенное исключение будет содержать сообщение "Distance cannot be negative.".
     */
    @ParameterizedTest
    @ValueSource(doubles = {-1.0, -Double.MAX_VALUE})
    void testConstructor_ShouldThrowExceptionAndGetMessage_WhenInputIsNegativeNumberDistance(double distance){
        String actual = "Distance cannot be negative.";
        try {
            horse = new Horse("Name",0, distance);
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(),actual);
        }
    }
    /**
     * Тест проверяет, что метод возвращает строку, которая была передана первым параметром в конструктор.
     */
    @Test
    void testGetName_ShouldReturnString(){
        String expected = "Name";
        String actual = new Horse("Name",0).getName();
        assertEquals(expected,actual);
    }
    /**
     * Тест проверяет, что метод возвращает число, которое было передано вторым параметром в конструктор.
     */
    @Test
    void testGetSpeed_ShouldReturnNumber(){
        double expected = 0;
        double actual = new Horse("Name",0).getSpeed();
        assertEquals(expected,actual);
    }
    /**
     * Тест проверяет, что метод возвращает число, которое было передано третьим параметром в конструктор.
     */
    @Test
    void testGetDistance_ShouldReturnNumber(){
        double expected = 0;
        double actual = new Horse("Name",0,0).getDistance();
        assertEquals(expected,actual);
    }
    /**
     * Тест проверяет, что метод возвращает ноль, если объект был создан с помощью конструктора с двумя параметрами.
     */
    @Test
    void testGetDistance_ShouldReturnNull(){
        double expected = 0;
        double actual = new Horse("Name",0).getDistance();
        assertEquals(expected,actual);
    }
//    /**
//     * Тест проверяет, что метод вызывает внутри метод getRandomDouble с параметрами 0.2 и 0.9.
//     */
//    @Test
//    void testGetMove_ShouldCauseMethodGetRandomDouble(){
//        try(MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)) {
//            mockedStatic.when(Horse::getRandomDouble).thenReturn()
//            mockedStatic.verify();
//        }
//    }
}