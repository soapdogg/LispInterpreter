package edu.osu.cse6341.lispInterpreter;

import edu.osu.cse6341.lispInterpreter.tokenizer.Tokenizer;
import org.junit.Assert;
import edu.osu.cse6341.lispInterpreter.program.Program;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;


public class InterpreterTest {

    private void setUp() {
        Program.getProgram().reset();
    }

   /* @org.junit.Test
    public void project1ValidTest(){
        setUp();
        tokenizerTest("data/input/project1/valid.lisp", "data/expected/project1/valid.txt");
    }

    @org.junit.Test
    public void project1Invalid1Test() {
        setUp();
        tokenizerTest("data/input/project1/invalid1.lisp", "data/expected/project1/invalid1.txt");
    }

    @org.junit.Test
    public void project1Invalid2Test(){
        setUp();
        tokenizerTest("data/input/project1/invalid2.lisp", "data/expected/project1/invalid2.txt");
    }

    @org.junit.Test
    public void project2ValidTest(){
        setUp();
        parserTest("data/input/project2/valid.lisp", "data/expected/project2/valid.txt");
    }

    @org.junit.Test
    public void project2Invalid1Test(){
        setUp();
        parserTest("data/input/project2/invalid1.lisp", "data/expected/project2/invalid1.txt");
    }

    @org.junit.Test
    public void project2Invalid2Test(){
        setUp();
        parserTest("data/input/project2/invalid2.lisp", "data/expected/project2/invalid1.txt");
    }
    */

    @org.junit.Test
    public void project3AtomValidTest(){
        setUp();
        interpreterTest("data/input/project3/atom/valid.lisp", "data/expected/project3/atom/valid.txt");
    }

    /*
    @org.junit.Test
    public void project3AtomInvalid1Test() {
        setUp();
        interpreterTest("data/input/project3/atom/invalid1.lisp", "data/expected/project3/atom/invalid1.txt");
    }

    @org.junit.Test
    public void project3AtomInvalid2Test() {
        setUp();
        interpreterTest("data/input/project3/atom/invalid2.lisp","data/expected/project3/atom/invalid2.txt");
    }

    @org.junit.Test
    public void project3AtomInvalid3Test() {
        setUp();
        interpreterTest("data/input/project3/atom/invalid3.lisp","data/expected/project3/atom/invalid3.txt");
    }
    */

    @org.junit.Test
    public void project3AtomicValidTest(){
        setUp();
        interpreterTest("data/input/project3/atomic/valid.lisp", "data/expected/project3/atomic/valid.txt");
    }

    @org.junit.Test
    public void project3AtomicInvalid1Test() {
        setUp();
        interpreterTest("data/input/project3/atomic/invalid1.lisp", "data/expected/project3/atomic/invalid1.txt");
    }

    //CAR TESTS
    @org.junit.Test
    public void project3CarValidTest(){
        setUp();
        interpreterTest("data/input/project3/car/valid.lisp", "data/expected/project3/car/valid.txt");
    }

    //CDR TESTS
    @org.junit.Test
    public void project3CdrValidTest(){
        setUp();
        interpreterTest("data/input/project3/cdr/valid.lisp", "data/expected/project3/cdr/valid.txt");
    }

    //CONS TESTS
    @org.junit.Test
    public void project3ConsValidTest(){
        setUp();
        interpreterTest("data/input/project3/cons/valid.lisp", "data/expected/project3/cons/valid.txt");
    }

    //EQ TESTS
    @org.junit.Test
    public void project3EqValidTest(){
        setUp();
        interpreterTest("data/input/project3/eq/valid.lisp", "data/expected/project3/eq/valid.txt");
    }

    //GREATER TESTS
    @org.junit.Test
    public void project3GreaterValidTest(){
        setUp();
        interpreterTest("data/input/project3/greater/valid.lisp", "data/expected/project3/greater/valid.txt");
    }

    //INT TESTS
    @org.junit.Test
    public void project3IntValidTest(){
        setUp();
        interpreterTest("data/input/project3/int/valid.lisp", "data/expected/project3/int/valid.txt");
    }

    //LESS TESTS
    @org.junit.Test
    public void project3LessValidTest(){
        setUp();
        interpreterTest("data/input/project3/less/valid.lisp", "data/expected/project3/less/valid.txt");
    }

    //MINUS TESTS
    @org.junit.Test
    public void project3MinusValidTest(){
        setUp();
        interpreterTest("data/input/project3/minus/valid.lisp", "data/expected/project3/minus/valid.txt");
    }

    //NULL TESTS
    @org.junit.Test
    public void project3NullValidTest(){
        setUp();
        interpreterTest("data/input/project3/null/valid.lisp", "data/expected/project3/null/valid.txt");
    }

    //PLUS TESTS
    @org.junit.Test
    public void project3PlusValidTest(){
        setUp();
        interpreterTest("data/input/project3/plus/valid.lisp", "data/expected/project3/plus/valid.txt");
    }

    //TIMES TESTS
    @org.junit.Test
    public void project3TimesValidTest(){
        setUp();
        interpreterTest("data/input/project3/times/valid.lisp", "data/expected/project3/times/valid.txt");
    }


    /*
    private static void tokenizerTest(String programFile, String expectedFile){
        Tokenizer tokenizer = Tokenizer.getTokenizer();
        String actual = tokenizer.testTokenizer(programFile);
        String expected = scanExpected(expectedFile);
        Assert.assertEquals(expected, actual);
    }

    private static void parserTest(String programFile, String expectedFile){
        Interpreter interpreter = Interpreter.getInterpreter();
        String actual = interpreter.testParser(programFile);
        String expected = scanExpected(expectedFile);
        Assert.assertEquals(expected, actual);
    }
    */

    private static void interpreterTest(String programFile, String expectedFile){
        Interpreter interpreter = Interpreter.getInterpreter();
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
