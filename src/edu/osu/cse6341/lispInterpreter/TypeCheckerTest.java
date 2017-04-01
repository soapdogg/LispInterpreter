package edu.osu.cse6341.lispInterpreter;

import org.junit.Assert;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;


public class TypeCheckerTest {

    //ATOM TESTS
    @org.junit.Test
    public void project5AtomValidTest(){
        typeCheckerTest("data/input/project3/atom/valid.lisp", "data/expected/project5/atom/valid.txt");
    }

    @org.junit.Test
    public void project5AtomInvalid1Test(){
        typeCheckerTest("data/input/project3/atom/invalid1.lisp", "data/expected/project5/atom/invalid1.txt");
    }

    @org.junit.Test
    public void project5AtomInvalid2Test(){
        typeCheckerTest("data/input/project3/atom/invalid2.lisp", "data/expected/project5/atom/invalid2.txt");
    }

    //ATOMIC TESTS
    @org.junit.Test
    public void project5AtomicValidTest(){
        typeCheckerTest("data/input/project3/atomic/valid.lisp", "data/expected/project5/atomic/valid.txt");
    }

    @org.junit.Test
    public void project5AtomicInvalid1Test(){
        typeCheckerTest("data/input/project3/atomic/invalid1.lisp", "data/expected/project5/atomic/invalid1.txt");
    }

    @org.junit.Test
    public void project5AtomicInvalid2Test(){
        typeCheckerTest("data/input/project3/atomic/invalid2.lisp", "data/expected/project5/atomic/invalid2.txt");
    }

    @org.junit.Test
    public void project5AtomicInvalid3Test(){
        typeCheckerTest("data/input/project3/atomic/invalid3.lisp", "data/expected/project5/atomic/invalid3.txt");
    }

    @org.junit.Test
    public void project5AtomicInvalid4Test(){
        typeCheckerTest("data/input/project3/atomic/invalid4.lisp", "data/expected/project5/atomic/invalid4.txt");
    }



    //CAR TESTS
    @org.junit.Test
    public void project5CarValidTest(){
        typeCheckerTest("data/input/project3/car/valid.lisp", "data/expected/project5/car/valid.txt");
    }

    @org.junit.Test
    public void project5CarInvalid1Test(){
        typeCheckerTest("data/input/project3/car/invalid1.lisp", "data/expected/project5/car/invalid1.txt");
    }

    @org.junit.Test
    public void project5CarInvalid2Test(){
        typeCheckerTest("data/input/project3/car/invalid2.lisp", "data/expected/project5/car/invalid2.txt");
    }

    @org.junit.Test
    public void project5CarInvalid3Test(){
        typeCheckerTest("data/input/project3/car/invalid3.lisp", "data/expected/project5/car/invalid3.txt");
    }

    @org.junit.Test
    public void project5CarInvalid4Test(){
        typeCheckerTest("data/input/project3/car/invalid4.lisp", "data/expected/project5/car/invalid4.txt");
    }

    @org.junit.Test
    public void project5CarInvalid5Test(){
        typeCheckerTest("data/input/project3/car/invalid5.lisp", "data/expected/project5/car/invalid5.txt");
    }



    //CDR TESTS
    @org.junit.Test
    public void project5CdrValidTest(){
        typeCheckerTest("data/input/project3/cdr/valid.lisp", "data/expected/project5/cdr/valid.txt");
    }

    @org.junit.Test
    public void project5CdrInvalid1Test(){
        typeCheckerTest("data/input/project3/cdr/invalid1.lisp", "data/expected/project5/cdr/invalid1.txt");
    }

    @org.junit.Test
    public void project5CdrInvalid2Test(){
        typeCheckerTest("data/input/project3/cdr/invalid2.lisp", "data/expected/project5/cdr/invalid2.txt");
    }

    @org.junit.Test
    public void project5CdrInvalid3Test(){
        typeCheckerTest("data/input/project3/cdr/invalid3.lisp", "data/expected/project5/cdr/invalid3.txt");
    }

    @org.junit.Test
    public void project5CdrInvalid4Test(){
        typeCheckerTest("data/input/project3/cdr/invalid4.lisp", "data/expected/project5/cdr/invalid4.txt");
    }


    //COND TESTS
    @org.junit.Test
    public void project5CondValidTest(){
        typeCheckerTest("data/input/project3/cond/valid.lisp", "data/expected/project5/cond/valid.txt");
    }

    @org.junit.Test
    public void project5CondInvalid1Test(){
        typeCheckerTest("data/input/project3/cond/invalid1.lisp", "data/expected/project5/cond/invalid1.txt");
    }

    @org.junit.Test
    public void project5CondInvalid2Test(){
        typeCheckerTest("data/input/project3/cond/invalid2.lisp", "data/expected/project5/cond/invalid2.txt");
    }

    @org.junit.Test
    public void project5CondInvalid3Test(){
        typeCheckerTest("data/input/project3/cond/invalid3.lisp", "data/expected/project5/cond/invalid3.txt");
    }

    @org.junit.Test
    public void project5CondInvalid4Test(){
        typeCheckerTest("data/input/project3/cond/invalid4.lisp", "data/expected/project5/cond/invalid4.txt");
    }

    @org.junit.Test
    public void project5CondInvalid5Test(){
        typeCheckerTest("data/input/project3/cond/invalid5.lisp", "data/expected/project5/cond/invalid5.txt");
    }



    //CONS TESTS
    @org.junit.Test
    public void project5ConsValidTest(){
        typeCheckerTest("data/input/project3/cons/valid.lisp", "data/expected/project5/cons/valid.txt");
    }

    @org.junit.Test
    public void project5ConsInvalid1Test(){
        typeCheckerTest("data/input/project3/cons/invalid1.lisp", "data/expected/project5/cons/invalid1.txt");
    }

    @org.junit.Test
    public void project5ConsInvalid2Test(){
        typeCheckerTest("data/input/project3/cons/invalid2.lisp", "data/expected/project5/cons/invalid2.txt");
    }



    //EQ TESTS
    @org.junit.Test
    public void project5EqValidTest(){
        typeCheckerTest("data/input/project3/eq/valid.lisp", "data/expected/project5/eq/valid.txt");
    }

    @org.junit.Test
    public void project5EqInvalid1Test(){
        typeCheckerTest("data/input/project3/eq/invalid1.lisp", "data/expected/project5/eq/invalid1.txt");
    }

    @org.junit.Test
    public void project5EqInvalid2Test(){
        typeCheckerTest("data/input/project3/eq/invalid2.lisp", "data/expected/project5/eq/invalid2.txt");
    }

    @org.junit.Test
    public void project5EqInvalid3Test(){
        typeCheckerTest("data/input/project3/eq/invalid3.lisp", "data/expected/project5/eq/invalid3.txt");
    }



    //INT TESTS
    @org.junit.Test
    public void project5IntValidTest(){
        typeCheckerTest("data/input/project3/int/valid.lisp", "data/expected/project5/int/valid.txt");
    }

    @org.junit.Test
    public void project5IntInvalid1Test(){
        typeCheckerTest("data/input/project3/int/invalid1.lisp", "data/expected/project5/int/invalid1.txt");
    }

    @org.junit.Test
    public void project5IntInvalid2Test(){
        typeCheckerTest("data/input/project3/int/invalid2.lisp", "data/expected/project5/int/invalid2.txt");
    }



    //LESS TESTS
    @org.junit.Test
    public void project5LessValidTest(){
        typeCheckerTest("data/input/project3/less/valid.lisp", "data/expected/project5/less/valid.txt");
    }

    @org.junit.Test
    public void project5LessInvalid1Test(){
        typeCheckerTest("data/input/project3/less/invalid1.lisp", "data/expected/project5/less/invalid1.txt");
    }

    @org.junit.Test
    public void project5LessInvalid2Test(){
        typeCheckerTest("data/input/project3/less/invalid2.lisp", "data/expected/project5/less/invalid2.txt");
    }

    @org.junit.Test
    public void project5LessInvalid3Test(){
        typeCheckerTest("data/input/project3/less/invalid3.lisp", "data/expected/project5/less/invalid3.txt");
    }


    //NULL TESTS
    @org.junit.Test
    public void project5NullValidTest(){
        typeCheckerTest("data/input/project3/null/valid.lisp", "data/expected/project5/null/valid.txt");
    }

    @org.junit.Test
    public void project5NullInvalid1Test(){
        typeCheckerTest("data/input/project3/null/invalid1.lisp", "data/expected/project5/null/invalid1.txt");
    }

    @org.junit.Test
    public void project5NullInvalid2Test(){
        typeCheckerTest("data/input/project3/null/invalid2.lisp", "data/expected/project5/null/invalid2.txt");
    }



    //PLUS TESTS
    @org.junit.Test
    public void project5PlusValidTest(){
        typeCheckerTest("data/input/project3/plus/valid.lisp", "data/expected/project5/plus/valid.txt");
    }

    @org.junit.Test
    public void project5PlusInvalid1Test(){
        typeCheckerTest("data/input/project3/plus/invalid1.lisp", "data/expected/project5/plus/invalid1.txt");
    }

    @org.junit.Test
    public void project5PlusInvalid2Test(){
        typeCheckerTest("data/input/project3/plus/invalid2.lisp", "data/expected/project5/plus/invalid2.txt");
    }

    @org.junit.Test
    public void project5PlusInvalid3Test(){
        typeCheckerTest("data/input/project3/plus/invalid3.lisp", "data/expected/project5/plus/invalid3.txt");
    }





    private static void typeCheckerTest(String programFile, String expectedFile){
        Interpreter interpreter = new Interpreter();
        String actual;
        try{
            actual = interpreter.testTypeChecker(programFile);
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
