package regression;

import edu.osu.cse6341.lispInterpreter.Interpreter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class ParserTest
{
    @Test
    public void project2ValidTest(){
        parserTest("data/input/project2/valid.lisp", "data/expected/project2/valid.txt");
    }

    @Test
    public void project2Valid2Test(){
        parserTest("data/input/project2/valid2.lisp", "data/expected/project2/valid2.txt");
    }

    @Test
    public void project2Valid3Test(){
        parserTest("data/input/project2/valid3.lisp", "data/expected/project2/valid3.txt");
    }

    @Test
    public void project2Valid4Test(){
        parserTest("data/input/project2/valid4.lisp", "data/expected/project2/valid4.txt");
    }

    @Test
    public void project2Valid5Test(){
        parserTest("data/input/project2/valid5.lisp", "data/expected/project2/valid5.txt");
    }

    @Test
    public void project2Valid6Test(){
        parserTest("data/input/project2/valid6.lisp", "data/expected/project2/valid6.txt");
    }

    @Test
    public void project2Valid7Test(){
        parserTest("data/input/project2/valid7.lisp", "data/expected/project2/valid7.txt");
    }

    @Test
    public void project2Invalid1Test(){
        parserTest("data/input/project2/invalid1.lisp", "data/expected/project2/invalid1.txt");
    }

    @Test
    public void project2Invalid2Test(){
        parserTest("data/input/project2/invalid2.lisp", "data/expected/project2/invalid2.txt");
    }

    @Test
    public void project2Invalid3Test(){
        parserTest("data/input/project2/invalid3.lisp", "data/expected/project2/invalid3.txt");
    }

    @Test
    public void project2Invalid4Test(){
        parserTest("data/input/project2/invalid4.lisp", "data/expected/project2/invalid4.txt");
    }

    @Test
    public void project2Invalid5Test(){
        parserTest("data/input/project2/invalid5.lisp", "data/expected/project2/invalid5.txt");
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
