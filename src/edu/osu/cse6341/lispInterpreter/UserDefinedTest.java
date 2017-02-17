package edu.osu.cse6341.lispInterpreter;

import org.junit.Assert;
import org.junit.Test;

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
