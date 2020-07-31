package regression;

import edu.osu.cse6341.lispInterpreter.Interpreter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class TokenizerTest
{
    //Tokenizer Tests
    @Test
    public void project1ValidTest(){
        tokenizerTest("data/input/project1/valid.lisp", "data/expected/project1/valid.txt");
    }

    @Test
    public void project1Valid2Test(){
        tokenizerTest("data/input/project1/valid2.lisp", "data/expected/project1/valid2.txt");
    }

    @Test
    public void project1Valid3Test(){
        tokenizerTest("data/input/project1/valid3.lisp", "data/expected/project1/valid3.txt");
    }

    @Test
    public void project1Valid4Test(){
        tokenizerTest("data/input/project1/valid4.lisp", "data/expected/project1/valid4.txt");
    }

    @Test
    public void project1Valid5Test(){
        tokenizerTest("data/input/project1/valid5.lisp", "data/expected/project1/valid5.txt");
    }

    @Test
    public void project1Valid6Test(){
        tokenizerTest("data/input/project1/valid6.lisp", "data/expected/project1/valid6.txt");
    }

    @Test
    public void project1Valid7Test(){
        tokenizerTest("data/input/project1/valid7.lisp", "data/expected/project1/valid7.txt");
    }

    @Test
    public void project1Valid8Test(){
        tokenizerTest("data/input/project1/valid8.lisp", "data/expected/project1/valid8.txt");
    }

    @Test
    public void project1Invalid1Test() {
        tokenizerTest("data/input/project1/invalid1.lisp", "data/expected/project1/invalid1.txt");
    }

    @Test
    public void project1Invalid2Test(){
        tokenizerTest("data/input/project1/invalid2.lisp", "data/expected/project1/invalid2.txt");
    }

    @Test
    public void project1Invalid3Test(){
        tokenizerTest("data/input/project1/invalid3.lisp", "data/expected/project1/invalid3.txt");
    }




    //Helpers
    private static void tokenizerTest(String programFile, String expectedFile){
        Interpreter interpreter = new Interpreter();
        String actual;
        try {
            Scanner in = getScannerFromFilePath(programFile);
            interpreter.interpret(
                in,
                true,
                false
            );
            actual = getTokenizedResults(
                interpreter
            );
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

    public static String getTokenizedResults(
        Interpreter interpreter
    ) {
        StringBuilder sb = new StringBuilder();
        sb.append("LITERAL ATOMS: ");
        sb.append(interpreter.literalAtoms.size());
        for (String s : interpreter.literalAtoms) {
            sb.append(',');
            sb.append(' ');
            sb.append(s);
        }
        sb.append('\n');
        sb.append("NUMERIC ATOMS: ");
        sb.append(interpreter.numericAtomsCount);
        sb.append(',');
        sb.append(interpreter.numericAtomsSum);
        sb.append('\n');
        sb.append("OPEN PARENTHESES: ");
        sb.append(interpreter.openCount);
        sb.append('\n');
        sb.append("CLOSING PARENTHESES: ");
        sb.append(interpreter.closingCount);
        sb.append('\n');
        return sb.toString();
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