package edu.osu.cse6341.lispInterpreter;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;


public class UserDefinedTest {


    @Test
    public void project4DiffValidTest(){
        interpreterTest("data/input/project4/diff/valid.lisp", "data/expected/project4/diff/valid.txt");
    }

    @Test
    public void project4DynamicValidTest(){
        interpreterTest("data/input/project4/dynamicscoping/valid.lisp", "data/expected/project4/dynamicscoping/valid.txt");
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
