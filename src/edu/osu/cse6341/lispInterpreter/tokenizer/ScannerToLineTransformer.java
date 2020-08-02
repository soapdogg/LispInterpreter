package edu.osu.cse6341.lispInterpreter.tokenizer;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ScannerToLineTransformer {

    public static List<String> transformScannerInputToLines(
        Scanner in
    ) {
        return StreamSupport.stream(
            Spliterators.spliteratorUnknownSize(in, Spliterator.ORDERED),
            true
        ).collect(Collectors.toList());
    }
}
