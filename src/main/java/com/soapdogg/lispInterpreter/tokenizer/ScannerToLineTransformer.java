package com.soapdogg.lispInterpreter.tokenizer;

import lombok.AllArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@AllArgsConstructor(staticName = "newInstance")
public class ScannerToLineTransformer {

    public List<String> transformScannerInputToLines(
        Scanner in
    ) {
        return StreamSupport.stream(
            Spliterators.spliteratorUnknownSize(in, Spliterator.ORDERED),
            true
        ).collect(Collectors.toList());
    }
}
