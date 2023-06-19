import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimpleCalculatorTest {
    @Test
    void twoPlusTwoShouldEqualsFour(){
        SimpleCalculator calculator = new SimpleCalculator();
        assertEquals(6, calculator.add(2, 4));
        assertNotEquals(4, calculator.add(4,4));
        assertTrue(calculator.add(2, 2)==4);
        assertFalse(calculator.add(2,2)==5);
//        assertNull();
//        assertNotNull(calculator)

    }

}