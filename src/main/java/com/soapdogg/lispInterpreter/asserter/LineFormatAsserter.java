package com.soapdogg.lispInterpreter.asserter;

import com.soapdogg.lispInterpreter.exceptions.InvalidTokenException;
import lombok.AllArgsConstructor;

import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@AllArgsConstructor(staticName = "newInstance")
public class LineFormatAsserter {

    private final Pattern errorStatePattern;

    public void assertLineFormat(String line) throws Exception {
        Matcher matcher = errorStatePattern.matcher(line);
        boolean hasErrorState = matcher.find();
        if (hasErrorState) {
            MatchResult matchResult = matcher.toMatchResult();
            int start = matchResult.start();
            int end = matchResult.end();

            String errorMessage = "Error! Invalid token: " + line.substring(start, end) + "\n";
            throw new InvalidTokenException(errorMessage);
        }
    }
}
