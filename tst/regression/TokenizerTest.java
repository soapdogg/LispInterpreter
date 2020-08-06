package regression;

import edu.osu.cse6341.lispInterpreter.datamodels.ProcessedTokensResult;
import edu.osu.cse6341.lispInterpreter.tokenizer.Tokenizer;
import edu.osu.cse6341.lispInterpreter.tokens.Token;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Queue;
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
        Tokenizer tokenizer = new Tokenizer();
        String actual;
        try {
            Scanner in = getScannerFromFilePath(programFile);
            Queue<Token> tokens = tokenizer.tokenize(in);
            ProcessedTokensResult processedTokensResult = processTokens(tokens);
            actual = getTokenizedResults(
                processedTokensResult
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

    private static ProcessedTokensResult processTokens(Queue<Token> tokens) throws Exception {
        Queue<String> literalAtoms = new LinkedList<>();
        int openCount = 0;
        int closingCount = 0;
        int numericAtomsSum = 0;
        int numericAtomsCount = 0;

        for (Token token : tokens) {
            switch (token.getTokenKind()) {
                case OPEN_TOKEN -> ++openCount;
                case CLOSE_TOKEN -> ++closingCount;
                case LITERAL_TOKEN -> literalAtoms.add(token.getValue());
                case NUMERIC_TOKEN -> {
                    ++numericAtomsCount;
                    numericAtomsSum += Integer.parseInt(token.getValue());
                }
                case ERROR_TOKEN -> {
                    String errorMessage = "Error! Invalid token: " + token.getValue() + "\n";
                    throw new Exception(errorMessage);
                }
                default -> {
                }
            }
        }
        return ProcessedTokensResult.newInstance(
            literalAtoms,
            openCount,
            closingCount,
            numericAtomsSum,
            numericAtomsCount
        );
    }

    private static String getTokenizedResults(
        ProcessedTokensResult processedTokensResult
    ) {
        StringBuilder sb = new StringBuilder();
        sb.append("LITERAL ATOMS: ");
        sb.append(processedTokensResult.getLiteralAtoms().size());
        for (String s : processedTokensResult.getLiteralAtoms()) {
            sb.append(',');
            sb.append(' ');
            sb.append(s);
        }
        sb.append('\n');
        sb.append("NUMERIC ATOMS: ");
        sb.append(processedTokensResult.getNumericAtomsCount());
        sb.append(',');
        sb.append(processedTokensResult.getNumericAtomsSum());
        sb.append('\n');
        sb.append("OPEN PARENTHESES: ");
        sb.append(processedTokensResult.getOpenCount());
        sb.append('\n');
        sb.append("CLOSING PARENTHESES: ");
        sb.append(processedTokensResult.getCloseCount());
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
