package Checker;

import org.antlr.runtime.*;

public class Checker {
	public static void main(String[] args) throws Exception {
		CheckerLexer lexer = new CheckerLexer(new ANTLRInputStream(System.in));
		CheckerParser parser = new CheckerParser(new CommonTokenStream(lexer));
		try {
			System.out.print(parser.check());	
		} catch (RuntimeException e) {
			System.out.print(e.getMessage());
		}
	}
}
		
