package edu.osu.cse6341.lispInterpreter;

import org.junit.Assert;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;


public class InterpreterTest {


    @org.junit.Test
    public void project1ValidTest(){
        tokenizerTest("data/input/project1/valid.lisp", "data/expected/project1/valid.txt");
    }

    @org.junit.Test
    public void project1Valid2Test(){
        tokenizerTest("data/input/project1/valid2.lisp", "data/expected/project1/valid2.txt");
    }

    @org.junit.Test
    public void project1Valid3Test(){
        tokenizerTest("data/input/project1/valid3.lisp", "data/expected/project1/valid3.txt");
    }

    @org.junit.Test
    public void project1Valid4Test(){
        tokenizerTest("data/input/project1/valid4.lisp", "data/expected/project1/valid4.txt");
    }

    @org.junit.Test
    public void project1Valid5Test(){
        tokenizerTest("data/input/project1/valid5.lisp", "data/expected/project1/valid5.txt");
    }

    @org.junit.Test
    public void project1Valid6Test(){
        tokenizerTest("data/input/project1/valid6.lisp", "data/expected/project1/valid6.txt");
    }

    @org.junit.Test
    public void project1Valid7Test(){
        tokenizerTest("data/input/project1/valid7.lisp", "data/expected/project1/valid7.txt");
    }

    @org.junit.Test
    public void project1Valid8Test(){
        tokenizerTest("data/input/project1/valid8.lisp", "data/expected/project1/valid8.txt");
    }

    @org.junit.Test
    public void project1Invalid1Test() {
        tokenizerTest("data/input/project1/invalid1.lisp", "data/expected/project1/invalid1.txt");
    }

    @org.junit.Test
    public void project1Invalid2Test(){
        tokenizerTest("data/input/project1/invalid2.lisp", "data/expected/project1/invalid2.txt");
    }

    @org.junit.Test
    public void project1Invalid3Test(){
        tokenizerTest("data/input/project1/invalid3.lisp", "data/expected/project1/invalid3.txt");
    }


    @org.junit.Test
    public void project2ValidTest(){
        parserTest("data/input/project2/valid.lisp", "data/expected/project2/valid.txt");
    }

    @org.junit.Test
    public void project2Valid2Test(){
        parserTest("data/input/project2/valid2.lisp", "data/expected/project2/valid2.txt");
    }

    @org.junit.Test
    public void project2Valid3Test(){
        parserTest("data/input/project2/valid3.lisp", "data/expected/project2/valid3.txt");
    }

    @org.junit.Test
    public void project2Valid4Test(){
        parserTest("data/input/project2/valid4.lisp", "data/expected/project2/valid4.txt");
    }

    @org.junit.Test
    public void project2Valid5Test(){
        parserTest("data/input/project2/valid5.lisp", "data/expected/project2/valid5.txt");
    }

    @org.junit.Test
    public void project2Valid6Test(){
        parserTest("data/input/project2/valid6.lisp", "data/expected/project2/valid6.txt");
    }

    @org.junit.Test
    public void project2Invalid1Test(){
        parserTest("data/input/project2/invalid1.lisp", "data/expected/project2/invalid1.txt");
    }

    @org.junit.Test
    public void project2Invalid2Test(){
        parserTest("data/input/project2/invalid2.lisp", "data/expected/project2/invalid2.txt");
    }

    @org.junit.Test
    public void project2Invalid3Test(){
        parserTest("data/input/project2/invalid3.lisp", "data/expected/project2/invalid3.txt");
    }

    @org.junit.Test
    public void project2Invalid4Test(){
        parserTest("data/input/project2/invalid4.lisp", "data/expected/project2/invalid4.txt");
    }

    @org.junit.Test
    public void project2Invalid5Test(){
        parserTest("data/input/project2/invalid5.lisp", "data/expected/project2/invalid5.txt");
    }


    @org.junit.Test
    public void project3AtomValidTest(){
        interpreterTest("data/input/project3/atom/valid.lisp", "data/expected/project3/atom/valid.txt");
    }

    @org.junit.Test
    public void project3AtomicValidTest(){
        interpreterTest("data/input/project3/atomic/valid.lisp", "data/expected/project3/atomic/valid.txt");
    }

    /*
    //CAR TESTS
    @org.junit.Test
    public void project3CarValidTest(){
        interpreterTest("data/input/project3/car/valid.lisp", "data/expected/project3/car/valid.txt");
    }

    //CDR TESTS
    @org.junit.Test
    public void project3CdrValidTest(){
        interpreterTest("data/input/project3/cdr/valid.lisp", "data/expected/project3/cdr/valid.txt");
    }

    */
    //CONS TESTS
    @org.junit.Test
    public void project3ConsValidTest(){
        interpreterTest("data/input/project3/cons/valid.lisp", "data/expected/project3/cons/valid.txt");
    }


    //EQ TESTS
    @org.junit.Test
    public void project3EqValidTest(){
        interpreterTest("data/input/project3/eq/valid.lisp", "data/expected/project3/eq/valid.txt");
    }


    //GREATER TESTS
    @org.junit.Test
    public void project3GreaterValidTest(){
        interpreterTest("data/input/project3/greater/valid.lisp", "data/expected/project3/greater/valid.txt");
    }

    //INT TESTS
    @org.junit.Test
    public void project3IntValidTest(){
        interpreterTest("data/input/project3/int/valid.lisp", "data/expected/project3/int/valid.txt");
    }

    //LESS TESTS
    @org.junit.Test
    public void project3LessValidTest(){
        interpreterTest("data/input/project3/less/valid.lisp", "data/expected/project3/less/valid.txt");
    }


    //MINUS TESTS
    @org.junit.Test
    public void project3MinusValidTest(){
        interpreterTest("data/input/project3/minus/valid.lisp", "data/expected/project3/minus/valid.txt");
    }


    //NULL TESTS
    @org.junit.Test
    public void project3NullValidTest(){
        interpreterTest("data/input/project3/null/valid.lisp", "data/expected/project3/null/valid.txt");
    }


    //PLUS TESTS
    @org.junit.Test
    public void project3PlusValidTest(){
        interpreterTest("data/input/project3/plus/valid.lisp", "data/expected/project3/plus/valid.txt");
    }


    //TIMES TESTS
    @org.junit.Test
    public void project3TimesValidTest(){
        interpreterTest("data/input/project3/times/valid.lisp", "data/expected/project3/times/valid.txt");
    }

    private static void tokenizerTest(String programFile, String expectedFile){
        Interpreter interpreter = new Interpreter();
        String actual = interpreter.testTokenizer(programFile);
        String expected = scanExpected(expectedFile);
        Assert.assertEquals(expected, actual);
    }


    private static void parserTest(String programFile, String expectedFile){
        Interpreter interpreter = new Interpreter();
        String actual = interpreter.testParser(programFile);
        String expected = scanExpected(expectedFile);
        Assert.assertEquals(expected, actual);
    }

    private static void interpreterTest(String programFile, String expectedFile){
        Interpreter interpreter = new Interpreter();
        String actual = interpreter.testInterpreter(programFile);
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
