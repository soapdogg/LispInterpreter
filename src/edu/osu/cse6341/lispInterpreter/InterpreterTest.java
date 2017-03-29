package edu.osu.cse6341.lispInterpreter;

import org.junit.Assert;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;


public class InterpreterTest {

    //ATOM TESTS
    @org.junit.Test
    public void project3AtomValidTest(){
        interpreterTest("data/input/project3/atom/valid.lisp", "data/expected/project3/atom/valid.txt");
    }

    @org.junit.Test
    public void project3AtomInvalid1Test(){
        interpreterTest("data/input/project3/atom/invalid1.lisp", "data/expected/project3/atom/invalid1.txt");
    }

    @org.junit.Test
    public void project3AtomInvalid2Test(){
        interpreterTest("data/input/project3/atom/invalid2.lisp", "data/expected/project3/atom/invalid2.txt");
    }

    //ATOMIC TESTS
    @org.junit.Test
    public void project3AtomicValidTest(){
        interpreterTest("data/input/project3/atomic/valid.lisp", "data/expected/project3/atomic/valid.txt");
    }

    @org.junit.Test
    public void project3AtomicInvalid1Test(){
        interpreterTest("data/input/project3/atomic/invalid1.lisp", "data/expected/project3/atomic/invalid1.txt");
    }

    @org.junit.Test
    public void project3AtomicInvalid2Test(){
        interpreterTest("data/input/project3/atomic/invalid2.lisp", "data/expected/project3/atomic/invalid2.txt");
    }

    @org.junit.Test
    public void project3AtomicInvalid3Test(){
        interpreterTest("data/input/project3/atomic/invalid3.lisp", "data/expected/project3/atomic/invalid3.txt");
    }

    @org.junit.Test
    public void project3AtomicInvalid4Test(){
        interpreterTest("data/input/project3/atomic/invalid4.lisp", "data/expected/project3/atomic/invalid4.txt");
    }



    //CAR TESTS
    @org.junit.Test
    public void project3CarValidTest(){
        interpreterTest("data/input/project3/car/valid.lisp", "data/expected/project3/car/valid.txt");
    }

    @org.junit.Test
    public void project3CarInvalid1Test(){
        interpreterTest("data/input/project3/car/invalid1.lisp", "data/expected/project3/car/invalid1.txt");
    }

    @org.junit.Test
    public void project3CarInvalid2Test(){
        interpreterTest("data/input/project3/car/invalid2.lisp", "data/expected/project3/car/invalid2.txt");
    }

    @org.junit.Test
    public void project3CarInvalid3Test(){
        interpreterTest("data/input/project3/car/invalid3.lisp", "data/expected/project3/car/invalid3.txt");
    }

    @org.junit.Test
    public void project3CarInvalid4Test(){
        interpreterTest("data/input/project3/car/invalid4.lisp", "data/expected/project3/car/invalid4.txt");
    }



    //CDR TESTS
    @org.junit.Test
    public void project3CdrValidTest(){
        interpreterTest("data/input/project3/cdr/valid.lisp", "data/expected/project3/cdr/valid.txt");
    }

    @org.junit.Test
    public void project3CdrInvalid1Test(){
        interpreterTest("data/input/project3/cdr/invalid1.lisp", "data/expected/project3/cdr/invalid1.txt");
    }

    @org.junit.Test
    public void project3CdrInvalid2Test(){
        interpreterTest("data/input/project3/cdr/invalid2.lisp", "data/expected/project3/cdr/invalid2.txt");
    }

    @org.junit.Test
    public void project3CdrInvalid3Test(){
        interpreterTest("data/input/project3/cdr/invalid3.lisp", "data/expected/project3/cdr/invalid3.txt");
    }



    //COND TESTS
    @org.junit.Test
    public void project3CondValidTest(){
        interpreterTest("data/input/project3/cond/valid.lisp", "data/expected/project3/cond/valid.txt");
    }

    @org.junit.Test
    public void project3CondInvalid1Test(){
        interpreterTest("data/input/project3/cond/invalid1.lisp", "data/expected/project3/cond/invalid1.txt");
    }

    @org.junit.Test
    public void project3CondInvalid2Test(){
        interpreterTest("data/input/project3/cond/invalid2.lisp", "data/expected/project3/cond/invalid2.txt");
    }

    @org.junit.Test
    public void project3CondInvalid3Test(){
        interpreterTest("data/input/project3/cond/invalid3.lisp", "data/expected/project3/cond/invalid3.txt");
    }

    @org.junit.Test
    public void project3CondInvalid4Test(){
        interpreterTest("data/input/project3/cond/invalid4.lisp", "data/expected/project3/cond/invalid4.txt");
    }



    //CONS TESTS
    @org.junit.Test
    public void project3ConsValidTest(){
        interpreterTest("data/input/project3/cons/valid.lisp", "data/expected/project3/cons/valid.txt");
    }

    @org.junit.Test
    public void project3ConsInvalid1Test(){
        interpreterTest("data/input/project3/cons/invalid1.lisp", "data/expected/project3/cons/invalid1.txt");
    }

    @org.junit.Test
    public void project3ConsInvalid2Test(){
        interpreterTest("data/input/project3/cons/invalid2.lisp", "data/expected/project3/cons/invalid2.txt");
    }



    //EQ TESTS
    @org.junit.Test
    public void project3EqValidTest(){
        interpreterTest("data/input/project3/eq/valid.lisp", "data/expected/project3/eq/valid.txt");
    }

    @org.junit.Test
    public void project3EqInvalid1Test(){
        interpreterTest("data/input/project3/eq/invalid1.lisp", "data/expected/project3/eq/invalid1.txt");
    }

    @org.junit.Test
    public void project3EqInvalid2Test(){
        interpreterTest("data/input/project3/eq/invalid2.lisp", "data/expected/project3/eq/invalid2.txt");
    }

    @org.junit.Test
    public void project3EqInvalid3Test(){
        interpreterTest("data/input/project3/eq/invalid3.lisp", "data/expected/project3/eq/invalid3.txt");
    }



    //INT TESTS
    @org.junit.Test
    public void project3IntValidTest(){
        interpreterTest("data/input/project3/int/valid.lisp", "data/expected/project3/int/valid.txt");
    }

    @org.junit.Test
    public void project3IntInvalid1Test(){
        interpreterTest("data/input/project3/int/invalid1.lisp", "data/expected/project3/int/invalid1.txt");
    }

    @org.junit.Test
    public void project3IntInvalid2Test(){
        interpreterTest("data/input/project3/int/invalid2.lisp", "data/expected/project3/int/invalid2.txt");
    }



    //LESS TESTS
    @org.junit.Test
    public void project3LessValidTest(){
        interpreterTest("data/input/project3/less/valid.lisp", "data/expected/project3/less/valid.txt");
    }

    @org.junit.Test
    public void project3LessInvalid1Test(){
        interpreterTest("data/input/project3/less/invalid1.lisp", "data/expected/project3/less/invalid1.txt");
    }

    @org.junit.Test
    public void project3LessInvalid2Test(){
        interpreterTest("data/input/project3/less/invalid2.lisp", "data/expected/project3/less/invalid2.txt");
    }

    @org.junit.Test
    public void project3LessInvalid3Test(){
        interpreterTest("data/input/project3/less/invalid3.lisp", "data/expected/project3/less/invalid3.txt");
    }


    //NULL TESTS
    @org.junit.Test
    public void project3NullValidTest(){
        interpreterTest("data/input/project3/null/valid.lisp", "data/expected/project3/null/valid.txt");
    }

    @org.junit.Test
    public void project3NullInvalid1Test(){
        interpreterTest("data/input/project3/null/invalid1.lisp", "data/expected/project3/null/invalid1.txt");
    }

    @org.junit.Test
    public void project3NullInvalid2Test(){
        interpreterTest("data/input/project3/null/invalid2.lisp", "data/expected/project3/null/invalid2.txt");
    }



    //PLUS TESTS
    @org.junit.Test
    public void project3PlusValidTest(){
        interpreterTest("data/input/project3/plus/valid.lisp", "data/expected/project3/plus/valid.txt");
    }

    @org.junit.Test
    public void project3PlusInvalid1Test(){
        interpreterTest("data/input/project3/plus/invalid1.lisp", "data/expected/project3/plus/invalid1.txt");
    }

    @org.junit.Test
    public void project3PlusInvalid2Test(){
        interpreterTest("data/input/project3/plus/invalid2.lisp", "data/expected/project3/plus/invalid2.txt");
    }

    @org.junit.Test
    public void project3PlusInvalid3Test(){
        interpreterTest("data/input/project3/plus/invalid3.lisp", "data/expected/project3/plus/invalid3.txt");
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
        Assert.assertEquals(expected, actual);
    }


    private static String scanExpected(String expectedFile) {
        StringBuilder builder = new StringBuilder();
        Scanner in = null;
        try {
            in = new Scanner(Paths.get(expectedFile));
            while (in.hasNextLine()) {
                String next = in.nextLine();
                builder.append(next);
                builder.append('\n');
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(in != null) in.close();
        }
        return builder.toString();
    }
}
