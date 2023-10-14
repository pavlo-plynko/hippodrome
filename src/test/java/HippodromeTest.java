import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HippodromeTest {
    public Hippodrome hippodrome;
    /**
     * Тест проверяет, что при передаче в конструктор первым параметром null, будет выброшено IllegalArgumentException.
     */
    @Test
    void testConstructor_ShouldThrowException_WhenInputIsNull(){
        assertThrows(IllegalArgumentException.class,() -> new Hippodrome(null));
    }
    /**
     * Тест проверяет, что при передаче в конструктор первым параметром null,
     * выброшенное исключение будет содержать сообщение "Horses cannot be null."
     */
    @Test
    void testConstructor_ShouldThrowExceptionAndGetMessage_WhenInputIsNull(){
        String actual = "Horses cannot be null.";
        try {
            hippodrome = new Hippodrome(null);
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(),actual);
        }
    }
    /**
     * Тест проверяет, что при передаче в конструктор пустого списка, будет выброшено IllegalArgumentException.
     */
    @Test
    void testConstructor_ShouldThrowException_WhenInputIsEmptyList(){
        assertThrows(IllegalArgumentException.class,() -> new Hippodrome(Collections.emptyList()));
    }
    /**
     * Тест проверяет, что при передаче в конструктор пустого списка,
     * выброшенное исключение будет содержать сообщение "Horses cannot be empty."
     */
    @Test
    void testConstructor_ShouldThrowExceptionAndGetMessage_WhenInputIsEmptyList(){
        String actual = "Horses cannot be empty.";
        try {
            hippodrome = new Hippodrome(Collections.emptyList());
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(),actual);
        }
    }
    /**
     * Тест проверяет, что метод возвращает список, который содержит те же объекты и в той же последовательности,
     * что и список который был передан в конструктор.
     */
    @Test
    void testGetHorses_ShouldReturnImmutableList(){
        List<Horse> expected = new ArrayList<>();
        expected.add(new Horse("Буцефал", 2.4));
        expected.add(new Horse("Туз Пик", 2.5));
        expected.add(new Horse("Зефир", 2.6));
        expected.add(new Horse("Пожар", 2.7));
        expected.add(new Horse("Лобстер", 2.8));
        expected.add(new Horse("Пегас", 2.9));
        expected.add(new Horse("Вишня", 3));
        expected.add(new Horse("Апполон", 2.4));
        expected.add(new Horse("Ангел", 2.4));
        expected.add(new Horse("Астон", 2.4));
        expected.add(new Horse("Адонис", 2.4));
        expected.add(new Horse("Буран", 2.4));
        expected.add(new Horse("Базилик", 2.4));
        expected.add(new Horse("Беркут", 2.4));
        expected.add(new Horse("Брюс", 2.4));
        expected.add(new Horse("Версаль", 2.4));
        expected.add(new Horse("Виконт", 2.4));
        expected.add(new Horse("Верный", 2.4));
        expected.add(new Horse("Вулкан", 2.4));
        expected.add(new Horse("Гамлет", 2.4));
        expected.add(new Horse("Галоп", 2.4));
        expected.add(new Horse("Геопард", 2.4));
        expected.add(new Horse("Гранат", 2.4));
        expected.add(new Horse("Гранит", 2.4));
        expected.add(new Horse("Дакар", 2.4));
        expected.add(new Horse("Дарко", 2.4));
        expected.add(new Horse("Домбай", 2.4));
        expected.add(new Horse("Дублон", 2.4));
        expected.add(new Horse("Дюранго", 2.4));
        expected.add(new Horse("Енисей", 2.4));

        List<Horse> actual = new Hippodrome(expected).getHorses();
        assertEquals(expected,actual);
    }
    /**
     * Тест проверяет, что метод возвращает лошадь с самым большим значением distance.
     */
    @Test
    void testGetWinner_ShouldReturnMaxDistance(){
        Horse expectedWinner = new Horse("Вишня", 3,100);
        List<Horse> horses = new ArrayList<>();
        horses.add(new Horse("Буцефал", 2.4,55));
        horses.add(new Horse("Туз Пик", 2.5,70));
        horses.add(expectedWinner);

        Horse actualWinner = new Hippodrome(horses).getWinner();
        assertEquals(expectedWinner,actualWinner);
    }
}