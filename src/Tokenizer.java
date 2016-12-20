import java.util.Queue;
import java.util.LinkedList;
import java.util.Scanner;

class Tokenizer implements ITokenizer{

	private static Tokenizer singletonTokenizer;
	private Queue<IToken> tokens;

	private Tokenizer(){
		tokens = new LinkedList<>();
	}
	
	public static Tokenizer getTokenizer(){
		if(singletonTokenizer == null) singletonTokenizer = new Tokenizer();
		return singletonTokenizer;
	}

	@Override
	public void tokenize(Scanner in){
		boolean continueScanning = true;
		String line;
		while(in.hasNextLine() && continueScanning){
			line = in.nextLine().trim();
			int startingPosition;
			int currentPosition = 0;
			while (continueScanning && currentPosition >= line.length()){
	
			}
		}
	}
}
