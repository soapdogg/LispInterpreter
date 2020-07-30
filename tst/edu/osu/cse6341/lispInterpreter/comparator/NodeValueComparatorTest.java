package edu.osu.cse6341.lispInterpreter.comparator;

import edu.osu.cse6341.lispInterpreter.constants.ReservedValuesConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class NodeValueComparatorTest {

    private NodeValueComparator nodeValueComparator;

    @BeforeEach
    void setup() {
        nodeValueComparator = NodeValueComparator.newInstance();
    }

    @Test
    void equalsNilFalseTest() {
        boolean actual = nodeValueComparator.equalsNil(ReservedValuesConstants.T);

        Assertions.assertFalse(actual);
    }

    @Test
    void equalsNilTrueTest() {
        boolean actual = nodeValueComparator.equalsNil(ReservedValuesConstants.NIL);

        Assertions.assertTrue(actual);
    }

    @Test
    void equalsTFalseTest() {
        boolean actual = nodeValueComparator.equalsT(ReservedValuesConstants.NIL);

        Assertions.assertFalse(actual);
    }

    @Test
    void equalTTrueTest(){
        boolean actual = nodeValueComparator.equalsT(ReservedValuesConstants.T);

        Assertions.assertTrue(actual);
    }
}
