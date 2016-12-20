import java.util.Scanner;

class Interpreter{

	public static void main(String[] args){
		tokenize();
		
	}

	private static void tokenize(){
		Scanner inputFileScanner = new Scanner(System.in);
		ITokenizer tokenizer = Tokenizer.getTokenizer();
		tokenizer.tokenize(inputFileScanner);
		inputFileScanner.close();
	}
}

 
