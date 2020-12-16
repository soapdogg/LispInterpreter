package regression

import com.soapdogg.lispInterpreter.singleton.InterpreterSingleton
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import regression.ScannerUtils.getScannerFromFilePath
import regression.ScannerUtils.scanExpected

class UserDefinedTest {
    @Test
    fun project4DiffTest() {
        interpreterTest("data/input/project4/diff.lisp", "data/expected/project4/diff.txt", true)
    }

    @Test
    fun project4DynamicTest() {
        interpreterTest("data/input/project4/dynamicscoping.lisp", "data/expected/project4/dynamicscoping.txt", true)
    }

    @Test
    fun project4MemTest() {
        interpreterTest("data/input/project4/mem.lisp", "data/expected/project4/mem.txt", true)
    }

    @Test
    fun project4UniTest() {
        interpreterTest("data/input/project4/uni.lisp", "data/expected/project4/uni.txt", false)
    }

    @Test
    fun project4FactorialTest() {
        interpreterTest("data/input/project4/factorial.lisp", "data/expected/project4/factorial.txt", false)
    }

    @Test
    fun project4NegateTest() {
        interpreterTest("data/input/project4/negate.lisp", "data/expected/project4/negate.txt", true)
    }

    @Test
    fun project4IncrementTest() {
        interpreterTest("data/input/project4/increment.lisp", "data/expected/project4/increment.txt", true)
    }

    @Test
    fun project4DefunIncorrectNumberParamsTest() {
        interpreterTest("data/input/project4/defun_incorrect_number_params.lisp", "data/expected/project4/defun_incorrect_number_params.txt", true)
    }

    @Test
    fun project4DefunInvalidMethodNameTest() {
        interpreterTest("data/input/project4/defun_invalid_method_name.lisp", "data/expected/project4/defun_invalid_method_name.txt", true)
    }

    @Test
    fun project4formalsActualsDifferentLengthTest() {
        interpreterTest("data/input/project4/formals_actuals_different.lisp", "data/expected/project4/formals_actuals_different.txt", true)
    }

    @Test
    fun project4invalidFormalTest() {
        interpreterTest("data/input/project4/invalid_formal.lisp", "data/expected/project4/invalid_formal.txt", true)
    }

    @Test
    fun project4duplicateFormalTest() {
        interpreterTest("data/input/project4/duplicate_formal.lisp", "data/expected/project4/duplicate_formal.txt", true)
    }

    @Test
    fun project4OneTest() {
        interpreterTest("data/input/project4/one.lisp", "data/expected/project4/one.txt", true)
    }

    @Test
    fun project4OutOfOrderDefunTest() {
        interpreterTest("data/input/project4/out_of_order_defun.lisp", "data/expected/project4/out_of_order_defun.txt", true)
    }

    @Test
    fun project4UndefinedVariableTest() {
        interpreterTest("data/input/project4/undefined_variable.lisp", "data/expected/project4/undefined_variable.txt", true)
    }

    @Test
    fun project4MemLargeTest() {
        interpreterTest("data/input/project4/mem_large.lisp", "data/expected/project4/mem_large.lisp", true)
    }

    @Test
    fun project4BoundTest() {
        interpreterTest("data/input/project4/bound.lisp", "data/expected/project4/bound.txt", true)
    }

    @Test
    fun project4GetValTest() {
        interpreterTest("data/input/project4/getval.lisp", "data/expected/project4/getval.txt", true)
    }

    @Test
    fun project4AddPairsTest() {
        interpreterTest("data/input/project4/addpairs.lisp", "data/expected/project4/addpairs.txt", true)
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