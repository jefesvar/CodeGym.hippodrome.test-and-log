import org.junit.jupiter.api.*;
import org.mockito.Mockito;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;


public class testHippodrome {

    @Nested
    class testConstructor{

        @Test
        void testNullConstructor(){
            Throwable nullException = assertThrows(
                    IllegalArgumentException.class,
                    () -> {
                        Hippodrome hippodrome = new Hippodrome(null);

                        assertNull(hippodrome);
                    }
            );

            assertEquals("Horses cannot be null.",nullException.getMessage());
        }

        @Test
        void testBlankListConstructor(){
            Throwable BlankListException = assertThrows(
                    IllegalArgumentException.class,
                    () -> {
                        Hippodrome hippodrome = new Hippodrome(new ArrayList<>());

                        assertNull(hippodrome);
                    }
            );

            assertEquals("Horses cannot be empty.",BlankListException.getMessage());
        }


    }

    @Test
    void testGetHorse(){
        List<Horse> horses = new ArrayList<>();
        for(int i = 0; i < 30; i++){
            horses.add(new Horse("Caballo"+i,i));
        }
        Hippodrome hippodrome = new Hippodrome(horses);


        assertArrayEquals(horses.toArray(), hippodrome.getHorses().toArray());
    }


    @Test
    void testMove(){
        List<Horse> horses = new ArrayList<>();
        Horse horse = Mockito.mock(Horse.class);
        for(int i = 0; i < 50; i++){
            horses.add(horse);
        }
        Hippodrome hippodrome = new Hippodrome(horses);

        hippodrome.move();

        Mockito.verify(horse, times(50)).move();
    }

    @Test
    void testGetWinner() {
        List<Horse> horses = new ArrayList<>();

        horses.add(new Horse("Caballo", 0, 0));
        horses.add(new Horse("Caballo", 0, 50));
        horses.add(new Horse("Caballo", 0, 10));
        Hippodrome hippodrome = new Hippodrome(horses);

        assertEquals(50, hippodrome.getWinner().getDistance());
    }

}
