package regression;

import edu.osu.cse6341.lispInterpreter.Interpreter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class UserDefinedTest {

    @Test
    public void project4DiffTest(){
        interpreterTest("data/input/project4/diff.lisp", "data/expected/project4/diff.txt");
    }

    @Test
    public void project4DynamicTest(){
        interpreterTest("data/input/project4/dynamicscoping.lisp", "data/expected/project4/dynamicscoping.txt");
    }

    @Test
    public void project4MemTest(){
        interpreterTest("data/input/project4/mem.lisp", "data/expected/project4/mem.txt");
    }


    @Test
    public void project4UniTest(){
        interpreterTest("data/input/project4/uni.lisp", "data/expected/project4/uni.txt");
    }

    @Test
    public void project4FactorialTest(){
        interpreterTest("data/input/project4/factorial.lisp", "data/expected/project4/factorial.txt");
    }

    @Test
    public void project4NegateTest(){
        interpreterTest("data/input/project4/negate.lisp", "data/expected/project4/negate.txt");
    }

    @Test
    public void project4IncrementTest(){
        interpreterTest("data/input/project4/increment.lisp", "data/expected/project4/increment.txt");
    }

    @Test
    public void project4DefunIncorrectNumberParamsTest(){
        interpreterTest("data/input/project4/defun_incorrect_number_params.lisp", "data/expected/project4/defun_incorrect_number_params.txt");
    }

    @Test
    public void project4DefunInvalidMethodNameTest(){
        interpreterTest("data/input/project4/defun_invalid_method_name.lisp", "data/expected/project4/defun_invalid_method_name.txt");
    }

    @Test
    public void project4formalsActualsDifferentLengthTest(){
        interpreterTest("data/input/project4/formals_actuals_different.lisp", "data/expected/project4/formals_actuals_different.txt");
    }

    @Test
    public void project4invalidFormalTest(){
        interpreterTest("data/input/project4/invalid_formal.lisp", "data/expected/project4/invalid_formal.txt");
    }

     @Test
    public void project4duplicateFormalTest(){
        interpreterTest("data/input/project4/duplicate_formal.lisp", "data/expected/project4/duplicate_formal.txt");
    }

     @Test
    public void project4OneTest(){
        interpreterTest("data/input/project4/one.lisp", "data/expected/project4/one.txt");
    }

    @Test
    public void project4UndefinedFunctionTest(){
        interpreterTest("data/input/project4/undefined_function.lisp", "data/expected/project4/undefined_function.txt");
    }

    @Test
    public void project4UndefinedVariableTest(){
        interpreterTest("data/input/project4/undefined_variable.lisp", "data/expected/project4/undefined_variable.txt");
    }

    @Test
    public void project4MemLargeTest(){
        //interpreterTest("data/input/project4/mem_large.lisp", "data/expected/project4/mem_large.txt");
    }

    @Test
    public void project4BoundTest(){
        interpreterTest("data/input/project4/bound.lisp", "data/expected/project4/bound.txt");
    }

    @Test
    public void project4GetValTest(){
        interpreterTest("data/input/project4/getval.lisp", "data/expected/project4/getval.txt");
    }

    @Test
    public void project4AddPairsTest(){
        interpreterTest("data/input/project4/addpairs.lisp", "data/expected/project4/addpairs.txt");
    }

    private static void interpreterTest(String programFile, String expectedFile){
        Interpreter interpreter = new Interpreter();
        String actual;
        try{
            actual = interpreter.testInterpreter(programFile);
        }catch (Exception e){
            actual = e.getMessage();
        }
        String expected = scanExpected(expectedFile);
        Assertions.assertEquals(expected, actual);
    }


    private static String scanExpected(String expectedFile) {
        StringBuilder builder = new StringBuilder();
        try (Scanner in = new Scanner(Paths.get(expectedFile))) {
            while (in.hasNextLine()) {
                String next = in.nextLine();
                builder.append(next);
                builder.append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }
}
