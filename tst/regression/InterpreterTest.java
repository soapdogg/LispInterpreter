package regression;

import edu.osu.cse6341.lispInterpreter.Interpreter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;


public class InterpreterTest {

    //ATOM TESTS
    @Test
    public void project3AtomValidTest(){
        interpreterTest("data/input/project3/atom/valid.lisp", "data/expected/project3/atom/valid.txt");
    }

    @Test
    public void project3AtomInvalid1Test(){
        interpreterTest("data/input/project3/atom/invalid1.lisp", "data/expected/project3/atom/invalid1.txt");
    }

    @Test
    public void project3AtomInvalid2Test(){
        interpreterTest("data/input/project3/atom/invalid2.lisp", "data/expected/project3/atom/invalid2.txt");
    }

    //ATOMIC TESTS
    @Test
    public void project3AtomicValidTest(){
        interpreterTest("data/input/project3/atomic/valid.lisp", "data/expected/project3/atomic/valid.txt");
    }

    @Test
    public void project3AtomicInvalid1Test(){
        interpreterTest("data/input/project3/atomic/invalid1.lisp", "data/expected/project3/atomic/invalid1.txt");
    }

    @Test
    public void project3AtomicInvalid2Test(){
        interpreterTest("data/input/project3/atomic/invalid2.lisp", "data/expected/project3/atomic/invalid2.txt");
    }

    @Test
    public void project3AtomicInvalid3Test(){
        interpreterTest("data/input/project3/atomic/invalid3.lisp", "data/expected/project3/atomic/invalid3.txt");
    }

    @Test
    public void project3AtomicInvalid4Test(){
        interpreterTest("data/input/project3/atomic/invalid4.lisp", "data/expected/project3/atomic/invalid4.txt");
    }



    //CAR TESTS
    @Test
    public void project3CarValidTest(){
        interpreterTest("data/input/project3/car/valid.lisp", "data/expected/project3/car/valid.txt");
    }

    @Test
    public void project3CarInvalid1Test(){
        interpreterTest("data/input/project3/car/invalid1.lisp", "data/expected/project3/car/invalid1.txt");
    }

    @Test
    public void project3CarInvalid2Test(){
        interpreterTest("data/input/project3/car/invalid2.lisp", "data/expected/project3/car/invalid2.txt");
    }

    @Test
    public void project3CarInvalid3Test(){
        interpreterTest("data/input/project3/car/invalid3.lisp", "data/expected/project3/car/invalid3.txt");
    }

    @Test
    public void project3CarInvalid4Test(){
        interpreterTest("data/input/project3/car/invalid4.lisp", "data/expected/project3/car/invalid4.txt");
    }



    //CDR TESTS
    @Test
    public void project3CdrValidTest(){
        interpreterTest("data/input/project3/cdr/valid.lisp", "data/expected/project3/cdr/valid.txt");
    }

    @Test
    public void project3CdrInvalid1Test(){
        interpreterTest("data/input/project3/cdr/invalid1.lisp", "data/expected/project3/cdr/invalid1.txt");
    }

    @Test
    public void project3CdrInvalid2Test(){
        interpreterTest("data/input/project3/cdr/invalid2.lisp", "data/expected/project3/cdr/invalid2.txt");
    }

    @Test
    public void project3CdrInvalid3Test(){
        interpreterTest("data/input/project3/cdr/invalid3.lisp", "data/expected/project3/cdr/invalid3.txt");
    }



    //COND TESTS
    @Test
    public void project3CondValidTest(){
        interpreterTest("data/input/project3/cond/valid.lisp", "data/expected/project3/cond/valid.txt");
    }

    @Test
    public void project3CondInvalid1Test(){
        interpreterTest("data/input/project3/cond/invalid1.lisp", "data/expected/project3/cond/invalid1.txt");
    }

    @Test
    public void project3CondInvalid2Test(){
        interpreterTest("data/input/project3/cond/invalid2.lisp", "data/expected/project3/cond/invalid2.txt");
    }

    @Test
    public void project3CondInvalid3Test(){
        interpreterTest("data/input/project3/cond/invalid3.lisp", "data/expected/project3/cond/invalid3.txt");
    }

    @Test
    public void project3CondInvalid4Test(){
        interpreterTest("data/input/project3/cond/invalid4.lisp", "data/expected/project3/cond/invalid4.txt");
    }



    //CONS TESTS
    @Test
    public void project3ConsValidTest(){
        interpreterTest("data/input/project3/cons/valid.lisp", "data/expected/project3/cons/valid.txt");
    }

    @Test
    public void project3ConsInvalid1Test(){
        interpreterTest("data/input/project3/cons/invalid1.lisp", "data/expected/project3/cons/invalid1.txt");
    }

    @Test
    public void project3ConsInvalid2Test(){
        interpreterTest("data/input/project3/cons/invalid2.lisp", "data/expected/project3/cons/invalid2.txt");
    }



    //EQ TESTS
    @Test
    public void project3EqValidTest(){
        interpreterTest("data/input/project3/eq/valid.lisp", "data/expected/project3/eq/valid.txt");
    }

    @Test
    public void project3EqInvalid1Test(){
        interpreterTest("data/input/project3/eq/invalid1.lisp", "data/expected/project3/eq/invalid1.txt");
    }

    @Test
    public void project3EqInvalid2Test(){
        interpreterTest("data/input/project3/eq/invalid2.lisp", "data/expected/project3/eq/invalid2.txt");
    }

    @Test
    public void project3EqInvalid3Test(){
        interpreterTest("data/input/project3/eq/invalid3.lisp", "data/expected/project3/eq/invalid3.txt");
    }



    //GREATER TESTS
    @Test
    public void project3GreaterValidTest(){
        interpreterTest("data/input/project3/greater/valid.lisp", "data/expected/project3/greater/valid.txt");
    }

    @Test
    public void project3GreaterInvalid1Test(){
        interpreterTest("data/input/project3/greater/invalid1.lisp", "data/expected/project3/greater/invalid1.txt");
    }

    @Test
    public void project3GreaterInvalid2Test(){
        interpreterTest("data/input/project3/greater/invalid2.lisp", "data/expected/project3/greater/invalid2.txt");
    }

    @Test
    public void project3GreaterInvalid3Test(){
        interpreterTest("data/input/project3/greater/invalid3.lisp", "data/expected/project3/greater/invalid3.txt");
    }



    //INT TESTS
    @Test
    public void project3IntValidTest(){
        interpreterTest("data/input/project3/int/valid.lisp", "data/expected/project3/int/valid.txt");
    }

    @Test
    public void project3IntInvalid1Test(){
        interpreterTest("data/input/project3/int/invalid1.lisp", "data/expected/project3/int/invalid1.txt");
    }

    @Test
    public void project3IntInvalid2Test(){
        interpreterTest("data/input/project3/int/invalid2.lisp", "data/expected/project3/int/invalid2.txt");
    }



    //LESS TESTS
    @Test
    public void project3LessValidTest(){
        interpreterTest("data/input/project3/less/valid.lisp", "data/expected/project3/less/valid.txt");
    }

    @Test
    public void project3LessInvalid1Test(){
        interpreterTest("data/input/project3/less/invalid1.lisp", "data/expected/project3/less/invalid1.txt");
    }

    @Test
    public void project3LessInvalid2Test(){
        interpreterTest("data/input/project3/less/invalid2.lisp", "data/expected/project3/less/invalid2.txt");
    }

    @Test
    public void project3LessInvalid3Test(){
        interpreterTest("data/input/project3/less/invalid3.lisp", "data/expected/project3/less/invalid3.txt");
    }



    //MINUS TESTS
    @Test
    public void project3MinusValidTest(){
        interpreterTest("data/input/project3/minus/valid.lisp", "data/expected/project3/minus/valid.txt");
    }

    @Test
    public void project3MinusInvalid1Test(){
        interpreterTest("data/input/project3/minus/invalid1.lisp", "data/expected/project3/minus/invalid1.txt");
    }

    @Test
    public void project3MinusInvalid2Test(){
        interpreterTest("data/input/project3/minus/invalid2.lisp", "data/expected/project3/minus/invalid2.txt");
    }

    @Test
    public void project3MinusInvalid3Test(){
        interpreterTest("data/input/project3/minus/invalid3.lisp", "data/expected/project3/minus/invalid3.txt");
    }



    //NULL TESTS
    @Test
    public void project3NullValidTest(){
        interpreterTest("data/input/project3/null/valid.lisp", "data/expected/project3/null/valid.txt");
    }

    @Test
    public void project3NullInvalid1Test(){
        interpreterTest("data/input/project3/null/invalid1.lisp", "data/expected/project3/null/invalid1.txt");
    }

    @Test
    public void project3NullInvalid2Test(){
        interpreterTest("data/input/project3/null/invalid2.lisp", "data/expected/project3/null/invalid2.txt");
    }



    //PLUS TESTS
    @Test
    public void project3PlusValidTest(){
        interpreterTest("data/input/project3/plus/valid.lisp", "data/expected/project3/plus/valid.txt");
    }

    @Test
    public void project3PlusInvalid1Test(){
        interpreterTest("data/input/project3/plus/invalid1.lisp", "data/expected/project3/plus/invalid1.txt");
    }

    @Test
    public void project3PlusInvalid2Test(){
        interpreterTest("data/input/project3/plus/invalid2.lisp", "data/expected/project3/plus/invalid2.txt");
    }

    @Test
    public void project3PlusInvalid3Test(){
        interpreterTest("data/input/project3/plus/invalid3.lisp", "data/expected/project3/plus/invalid3.txt");
    }



    //QUOTE TESTS
    @Test
    public void project3QuoteValidTest(){
        interpreterTest("data/input/project3/quote/valid.lisp", "data/expected/project3/quote/valid.txt");
    }

    @Test
    public void project3QuoteInvalid1Test(){
        interpreterTest("data/input/project3/quote/invalid1.lisp", "data/expected/project3/quote/invalid1.txt");
    }

    @Test
    public void project3QuoteInvalid2Test(){
        interpreterTest("data/input/project3/quote/invalid2.lisp", "data/expected/project3/quote/invalid2.txt");
    }



    //TIMES TESTS
    @Test
    public void project3TimesValidTest(){
        interpreterTest("data/input/project3/times/valid.lisp", "data/expected/project3/times/valid.txt");
    }

    @Test
    public void project3TimesInvalid1Test(){
        interpreterTest("data/input/project3/times/invalid1.lisp", "data/expected/project3/times/invalid1.txt");
    }

    @Test
    public void project3TimesInvalid2Test(){
        interpreterTest("data/input/project3/times/invalid2.lisp", "data/expected/project3/times/invalid2.txt");
    }

    @Test
    public void project3TimesInvalid3Test(){
        interpreterTest("data/input/project3/times/invalid3.lisp", "data/expected/project3/times/invalid3.txt");
    }




    private static void interpreterTest(String programFile, String expectedFile){
        Interpreter interpreter = new Interpreter();
        String actual;
        try{
            Scanner in = getScannerFromFilePath(programFile);
            actual = interpreter.interpret(in,  true);
        }catch (Exception e){
            actual = e.getMessage();
        }
        String expected = scanExpected(expectedFile);
        Assertions.assertEquals(expected, actual);
    }

    private static Scanner getScannerFromFilePath(String programFilePath){
        Scanner in = null;
        try {
            in = new Scanner(Paths.get(programFilePath));
        }catch (IOException e){
            System.out.println("File not found");
            System.out.println(programFilePath);
            System.exit(-10);
        }
        return in;
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
