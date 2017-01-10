package edu.osu.cse6341.lispInterpreter.program;

import edu.osu.cse6341.lispInterpreter.tokenizer.Tokenizer;

public interface IParsable{
	void parse(Tokenizer tokenizer, Program program);
}
