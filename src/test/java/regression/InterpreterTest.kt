package regression

import com.soapdogg.lispInterpreter.singleton.InterpreterSingleton
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import regression.ScannerUtils.getScannerFromFilePath
import regression.ScannerUtils.scanExpected

class InterpreterTest {
    //ATOM TESTS
    @Test
    fun project3AtomValidTest() {
        interpreterTest("data/input/project3/atom/valid.lisp", "data/expected/project3/atom/valid.txt", false)
    }

    @Test
    fun project3AtomInvalid1Test() {
        interpreterTest("data/input/project3/atom/invalid1.lisp", "data/expected/project3/atom/invalid1.txt", false)
    }

    @Test
    fun project3AtomInvalid2Test() {
        interpreterTest("data/input/project3/atom/invalid2.lisp", "data/expected/project3/atom/invalid2.txt", false)
    }

    //ATOMIC TESTS
    @Test
    fun project3AtomicValidTest() {
        interpreterTest("data/input/project3/atomic/valid.lisp", "data/expected/project3/atomic/valid.txt", true)
    }

    @Test
    fun project3AtomicInvalid1Test() {
        interpreterTest("data/input/project3/atomic/invalid1.lisp", "data/expected/project3/atomic/invalid1.txt", false)
    }

    @Test
    fun project3AtomicInvalid2Test() {
        interpreterTest("data/input/project3/atomic/invalid2.lisp", "data/expected/project3/atomic/invalid2.txt", false)
    }

    @Test
    fun project3AtomicInvalid3Test() {
        interpreterTest("data/input/project3/atomic/invalid3.lisp", "data/expected/project3/atomic/invalid3.txt", false)
    }

    @Test
    fun project3AtomicInvalid4Test() {
        interpreterTest("data/input/project3/atomic/invalid4.lisp", "data/expected/project3/atomic/invalid4.txt", false)
    }

    //CAR TESTS
    @Test
    fun project3CarValidTest() {
        interpreterTest("data/input/project3/car/valid.lisp", "data/expected/project3/car/valid.txt", false)
    }

    @Test
    fun project3CarInvalid1Test() {
        interpreterTest("data/input/project3/car/invalid1.lisp", "data/expected/project3/car/invalid1.txt", false)
    }

    @Test
    fun project3CarInvalid2Test() {
        interpreterTest("data/input/project3/car/invalid2.lisp", "data/expected/project3/car/invalid2.txt", false)
    }

    @Test
    fun project3CarInvalid3Test() {
        interpreterTest("data/input/project3/car/invalid3.lisp", "data/expected/project3/car/invalid3.txt", false)
    }

    @Test
    fun project3CarInvalid4Test() {
        interpreterTest("data/input/project3/car/invalid4.lisp", "data/expected/project3/car/invalid4.txt", false)
    }

    //CDR TESTS
    @Test
    fun project3CdrValidTest() {
        interpreterTest("data/input/project3/cdr/valid.lisp", "data/expected/project3/cdr/valid.txt", false)
    }

    @Test
    fun project3CdrInvalid1Test() {
        interpreterTest("data/input/project3/cdr/invalid1.lisp", "data/expected/project3/cdr/invalid1.txt", false)
    }

    @Test
    fun project3CdrInvalid2Test() {
        interpreterTest("data/input/project3/cdr/invalid2.lisp", "data/expected/project3/cdr/invalid2.txt", false)
    }

    @Test
    fun project3CdrInvalid3Test() {
        interpreterTest("data/input/project3/cdr/invalid3.lisp", "data/expected/project3/cdr/invalid3.txt", false)
    }

    //COND TESTS
    @Test
    fun project3CondValidTest() {
        interpreterTest("data/input/project3/cond/valid.lisp", "data/expected/project3/cond/valid.txt", false)
    }

    @Test
    fun project3CondInvalid1Test() {
        interpreterTest("data/input/project3/cond/invalid1.lisp", "data/expected/project3/cond/invalid1.txt", false)
    }

    @Test
    fun project3CondInvalid2Test() {
        interpreterTest("data/input/project3/cond/invalid2.lisp", "data/expected/project3/cond/invalid2.txt", false)
    }

    @Test
    fun project3CondInvalid3Test() {
        interpreterTest("data/input/project3/cond/invalid3.lisp", "data/expected/project3/cond/invalid3.txt", false)
    }

    @Test
    fun project3CondInvalid4Test() {
        interpreterTest("data/input/project3/cond/invalid4.lisp", "data/expected/project3/cond/invalid4.txt", false)
    }

    //CONS TESTS
    @Test
    fun project3ConsValidTest() {
        interpreterTest("data/input/project3/cons/valid.lisp", "data/expected/project3/cons/valid.txt", false)
    }

    @Test
    fun project3ConsInvalid1Test() {
        interpreterTest("data/input/project3/cons/invalid1.lisp", "data/expected/project3/cons/invalid1.txt", false)
    }

    @Test
    fun project3ConsInvalid2Test() {
        interpreterTest("data/input/project3/cons/invalid2.lisp", "data/expected/project3/cons/invalid2.txt", false)
    }

    //EQ TESTS
    @Test
    fun project3EqValidTest() {
        interpreterTest("data/input/project3/eq/valid.lisp", "data/expected/project3/eq/valid.txt", false)
    }

    @Test
    fun project3EqInvalid1Test() {
        interpreterTest("data/input/project3/eq/invalid1.lisp", "data/expected/project3/eq/invalid1.txt", false)
    }

    @Test
    fun project3EqInvalid2Test() {
        interpreterTest("data/input/project3/eq/invalid2.lisp", "data/expected/project3/eq/invalid2.txt", false)
    }

    @Test
    fun project3EqInvalid3Test() {
        interpreterTest("data/input/project3/eq/invalid3.lisp", "data/expected/project3/eq/invalid3.txt", false)
    }

    //GREATER TESTS
    @Test
    fun project3GreaterValidTest() {
        interpreterTest("data/input/project3/greater/valid.lisp", "data/expected/project3/greater/valid.txt", false)
    }

    @Test
    fun project3GreaterInvalid1Test() {
        interpreterTest("data/input/project3/greater/invalid1.lisp", "data/expected/project3/greater/invalid1.txt", false)
    }

    @Test
    fun project3GreaterInvalid2Test() {
        interpreterTest("data/input/project3/greater/invalid2.lisp", "data/expected/project3/greater/invalid2.txt", false)
    }

    @Test
    fun project3GreaterInvalid3Test() {
        interpreterTest("data/input/project3/greater/invalid3.lisp", "data/expected/project3/greater/invalid3.txt", false)
    }

    //INT TESTS
    @Test
    fun project3IntValidTest() {
        interpreterTest("data/input/project3/int/valid.lisp", "data/expected/project3/int/valid.txt", false)
    }

    @Test
    fun project3IntInvalid1Test() {
        interpreterTest("data/input/project3/int/invalid1.lisp", "data/expected/project3/int/invalid1.txt", false)
    }

    @Test
    fun project3IntInvalid2Test() {
        interpreterTest("data/input/project3/int/invalid2.lisp", "data/expected/project3/int/invalid2.txt", false)
    }

    //LESS TESTS
    @Test
    fun project3LessValidTest() {
        interpreterTest("data/input/project3/less/valid.lisp", "data/expected/project3/less/valid.txt", false)
    }

    @Test
    fun project3LessInvalid1Test() {
        interpreterTest("data/input/project3/less/invalid1.lisp", "data/expected/project3/less/invalid1.txt", false)
    }

    @Test
    fun project3LessInvalid2Test() {
        interpreterTest("data/input/project3/less/invalid2.lisp", "data/expected/project3/less/invalid2.txt", false)
    }

    @Test
    fun project3LessInvalid3Test() {
        interpreterTest("data/input/project3/less/invalid3.lisp", "data/expected/project3/less/invalid3.txt", false)
    }

    //MINUS TESTS
    @Test
    fun project3MinusValidTest() {
        interpreterTest("data/input/project3/minus/valid.lisp", "data/expected/project3/minus/valid.txt", true)
    }

    @Test
    fun project3MinusInvalid1Test() {
        interpreterTest("data/input/project3/minus/invalid1.lisp", "data/expected/project3/minus/invalid1.txt", false)
    }

    @Test
    fun project3MinusInvalid2Test() {
        interpreterTest("data/input/project3/minus/invalid2.lisp", "data/expected/project3/minus/invalid2.txt", false)
    }

    @Test
    fun project3MinusInvalid3Test() {
        interpreterTest("data/input/project3/minus/invalid3.lisp", "data/expected/project3/minus/invalid3.txt", false)
    }

    //NULL TESTS
    @Test
    fun project3NullValidTest() {
        interpreterTest("data/input/project3/null/valid.lisp", "data/expected/project3/null/valid.txt", false)
    }

    @Test
    fun project3NullInvalid1Test() {
        interpreterTest("data/input/project3/null/invalid1.lisp", "data/expected/project3/null/invalid1.txt", false)
    }

    @Test
    fun project3NullInvalid2Test() {
        interpreterTest("data/input/project3/null/invalid2.lisp", "data/expected/project3/null/invalid2.txt", false)
    }

    //PLUS TESTS
    @Test
    fun project3PlusValidTest() {
        interpreterTest("data/input/project3/plus/valid.lisp", "data/expected/project3/plus/valid.txt", false)
    }

    @Test
    fun project3PlusInvalid1Test() {
        interpreterTest("data/input/project3/plus/invalid1.lisp", "data/expected/project3/plus/invalid1.txt", false)
    }

    @Test
    fun project3PlusInvalid2Test() {
        interpreterTest("data/input/project3/plus/invalid2.lisp", "data/expected/project3/plus/invalid2.txt", false)
    }

    @Test
    fun project3PlusInvalid3Test() {
        interpreterTest("data/input/project3/plus/invalid3.lisp", "data/expected/project3/plus/invalid3.txt", false)
    }

    //QUOTE TESTS
    @Test
    fun project3QuoteValidTest() {
        interpreterTest("data/input/project3/quote/valid.lisp", "data/expected/project3/quote/valid.txt", false)
    }

    @Test
    fun project3QuoteInvalid1Test() {
        interpreterTest("data/input/project3/quote/invalid1.lisp", "data/expected/project3/quote/invalid1.txt", false)
    }

    @Test
    fun project3QuoteInvalid2Test() {
        interpreterTest("data/input/project3/quote/invalid2.lisp", "data/expected/project3/quote/invalid2.txt", false)
    }

    //TIMES TESTS
    @Test
    fun project3TimesValidTest() {
        interpreterTest("data/input/project3/times/valid.lisp", "data/expected/project3/times/valid.txt", false)
    }

    @Test
    fun project3TimesInvalid1Test() {
        interpreterTest("data/input/project3/times/invalid1.lisp", "data/expected/project3/times/invalid1.txt", false)
    }

    @Test
    fun project3TimesInvalid2Test() {
        interpreterTest("data/input/project3/times/invalid2.lisp", "data/expected/project3/times/invalid2.txt", false)
    }

    @Test
    fun project3TimesInvalid3Test() {
        interpreterTest("data/input/project3/times/invalid3.lisp", "data/expected/project3/times/invalid3.txt", false)
    }

    companion object {
        private fun interpreterTest(
            programFile: String,
            expectedFile: String,
            useStackEval: Boolean
        ) {
            val interpreter = InterpreterSingleton.INSTANCE.interpreter
            val actual = try {
                val `in` = getScannerFromFilePath(programFile)
                interpreter.interpret(`in`, useStackEval)
            } catch (e: Exception) {
                e.message.toString()
            }
            val expected = scanExpected(expectedFile)
            Assertions.assertEquals(expected, actual)
        }
    }
}