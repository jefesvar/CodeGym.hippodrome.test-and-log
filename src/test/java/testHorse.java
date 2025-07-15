import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;


public class testHorse {

    @Nested
    class testConstructor{



        @Test
        void testNullConstructor(){

            Throwable nullException = assertThrows(
                    IllegalArgumentException.class,
                    () -> {
                        Horse horse = new Horse(null,0);

                        assertNull(horse);
                    }
            );

            assertEquals("Name cannot be null.", nullException.getMessage());
        }

        @ParameterizedTest
        @ValueSource(strings = {" ", "  ",""})
        void testBlankConstructor(String blank){

            Throwable blankException = assertThrows(
                    IllegalArgumentException.class,
                    () -> {
                        Horse horse = new Horse(blank,0);

                        assertNull(horse);
                    }
            );

            assertEquals("Name cannot be blank.", blankException.getMessage());
        }

        @ParameterizedTest
        @ValueSource(ints = {-1, -1000, Integer.MIN_VALUE})
        void testNegativeSpeedConstructor(int negative){

            Throwable blankException = assertThrows(
                    IllegalArgumentException.class,
                    () -> {
                        Horse horse = new Horse("caballo",negative);
                        assertNull(horse);
                    }
            );

            assertEquals("Speed cannot be negative.", blankException.getMessage());
        }

        @ParameterizedTest
        @ValueSource(ints = {-1, -1000, Integer.MIN_VALUE})
        void testNegativeDistanceConstructor(int negative){

            Throwable blankException = assertThrows(
                    IllegalArgumentException.class,
                    () -> {
                        Horse horse = new Horse("caballo", 0,negative);
                        assertNull(horse);
                    }
            );

            assertEquals("Distance cannot be negative.", blankException.getMessage());
        }
    }


    @Nested
    class testBasic{
        private static Horse horse;

        @BeforeAll
        public static void init(){
            horse = new Horse("Caballo", 10, 10);
        }

        @Test
        void testGetName(){
            assertEquals("Caballo", horse.getName());
        }

        @Test
        void testGetSpeed(){
            assertEquals(10, horse.getSpeed());
        }

        @Test
        void testGetDistance(){
            assertEquals(10, horse.getDistance());
        }

        @Test
        void testTwoParamsGetDistance(){
            Horse horse2Param = new Horse("Caballo",10);
            assertEquals(0, horse2Param.getDistance());
        }

    }


    @ParameterizedTest
    @CsvSource({
            "Caballo, 10"
    })
    void testMove(String name, int Speed){
        try(MockedStatic<Horse> horseMock = Mockito.mockStatic(Horse.class)){

            // regla
            horseMock.when(() -> Horse.getRandomDouble(0.2,0.9)).thenReturn(1.0);

            //proceso
            Horse horse = new Horse(name,Speed);
            horse.move();

            //Verifica uso
            horseMock.verify(
                    () -> Horse.getRandomDouble(0.2,0.9),
                    times(1)
            );

            //Verifica resultado por formula
            assertEquals(10,horse.getDistance());

        }
    }
}
