package edu.osu.cse6341.lispInterpreter;

import org.junit.Assert;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;


public class InterpreterTest {

    //Tokenizer Tests
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



    //Parser Tests
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


    //Evaluator Tests
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



    @org.junit.Test
    public void project3AtomicValidTest(){
        interpreterTest("data/input/project3/atomic/valid.lisp", "data/expected/project3/atomic/valid.txt");
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



    //GREATER TESTS
    @org.junit.Test
    public void project3GreaterValidTest(){
        interpreterTest("data/input/project3/greater/valid.lisp", "data/expected/project3/greater/valid.txt");
    }

    @org.junit.Test
    public void project3GreaterInvalid1Test(){
        interpreterTest("data/input/project3/greater/invalid1.lisp", "data/expected/project3/greater/invalid1.txt");
    }

    @org.junit.Test
    public void project3GreaterInvalid2Test(){
        interpreterTest("data/input/project3/greater/invalid2.lisp", "data/expected/project3/greater/invalid2.txt");
    }

    @org.junit.Test
    public void project3GreaterInvalid3Test(){
        interpreterTest("data/input/project3/greater/invalid3.lisp", "data/expected/project3/greater/invalid3.txt");
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




    //MINUS TESTS
    @org.junit.Test
    public void project3MinusValidTest(){
        interpreterTest("data/input/project3/minus/valid.lisp", "data/expected/project3/minus/valid.txt");
    }

    @org.junit.Test
    public void project3MinusInvalid1Test(){
        interpreterTest("data/input/project3/minus/invalid1.lisp", "data/expected/project3/minus/invalid1.txt");
    }

    @org.junit.Test
    public void project3MinusInvalid2Test(){
        interpreterTest("data/input/project3/minus/invalid2.lisp", "data/expected/project3/minus/invalid2.txt");
    }

    @org.junit.Test
    public void project3MinusInvalid3Test(){
        interpreterTest("data/input/project3/minus/invalid3.lisp", "data/expected/project3/minus/invalid3.txt");
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




    //QUOTE TESTS
    @org.junit.Test
    public void project3QuoteValidTest(){
        interpreterTest("data/input/project3/quote/valid.lisp", "data/expected/project3/quote/valid.txt");
    }

    @org.junit.Test
    public void project3QuoteInvalid1Test(){
        interpreterTest("data/input/project3/quote/invalid1.lisp", "data/expected/project3/quote/invalid1.txt");
    }

    @org.junit.Test
    public void project3QuoteInvalid2Test(){
        interpreterTest("data/input/project3/quote/invalid2.lisp", "data/expected/project3/quote/invalid2.txt");
    }


    //TIMES TESTS
    @org.junit.Test
    public void project3TimesValidTest(){
        interpreterTest("data/input/project3/times/valid.lisp", "data/expected/project3/times/valid.txt");
    }

    @org.junit.Test
    public void project3TimesInvalid1Test(){
        interpreterTest("data/input/project3/times/invalid1.lisp", "data/expected/project3/times/invalid1.txt");
    }

    @org.junit.Test
    public void project3TimesInvalid2Test(){
        interpreterTest("data/input/project3/times/invalid2.lisp", "data/expected/project3/times/invalid2.txt");
    }

    @org.junit.Test
    public void project3TimesInvalid3Test(){
        interpreterTest("data/input/project3/times/invalid3.lisp", "data/expected/project3/times/invalid3.txt");
    }




    //Helpers
    private static void tokenizerTest(String programFile, String expectedFile){
        Interpreter interpreter = new Interpreter();
        String actual;
        try {
            actual = interpreter.testTokenizer(programFile);
        }catch (Exception e){
            actual = e.getMessage();
        }
        String expected = scanExpected(expectedFile);
        Assert.assertEquals(expected, actual);
    }


    private static void parserTest(String programFile, String expectedFile){
        Interpreter interpreter = new Interpreter();
        String actual;
        try{
            actual = interpreter.testParser(programFile);
        } catch (Exception e){
            actual = e.getMessage();
        }
        String expected = scanExpected(expectedFile);
        Assert.assertEquals(expected, actual);
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
