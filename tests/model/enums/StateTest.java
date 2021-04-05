package model.enums;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 *  * Testklasse für die Enum State.
 */
public class StateTest {

    /**
     * Test für stateFromId-Methode des State-Enums.
     */
    @Test
    public void stateFromIdTest() {
        assertEquals(State.PASSED_WITH_GRADE,State.stateFromId(1));
        assertEquals(State.PASSED_WITHOUT_GRADE,State.stateFromId(2));
        assertEquals(State.NOT_PASSED,State.stateFromId(3));
        assertEquals(State.NO_RESULT,State.stateFromId(4));
        assertEquals(State.NO_RESULT,State.stateFromId(42));
    }


    /**
     * Test für getId-Methode des State-Enums.
     */
    @Test
    public void getIdTest() {
        assertEquals(1,State.PASSED_WITH_GRADE.getId());
        assertEquals(2,State.PASSED_WITHOUT_GRADE.getId());
        assertEquals(3,State.NOT_PASSED.getId());
        assertEquals(4,State.NO_RESULT.getId());
    }
}