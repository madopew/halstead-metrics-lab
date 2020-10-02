package by.lexer;

import java.util.ArrayList;

import by.lexer.Enums.Type;

public class Lexer {
	private ArrayList<Token> tokens;
	private char[] inputStream;
	private int currentIndex = 0;
	public Lexer(String unparsedText) {
		tokens = new ArrayList<Token>();
		this.inputStream = unparsedText.toCharArray();
		parse();
	}
	
	public ArrayList<Token> getTokens() {
		return tokens;
	}
	
	private void parse() {
		while(currentIndex < inputStream.length) {
			readNext();
			currentIndex++;
		}
	}
	
	public String toString() {
		StringBuilder toReturn = new StringBuilder("");
		for(int i = 0; i < tokens.size(); i++)
			toReturn.append(tokens.get(i).toString() + "\n");
		return toReturn.toString();
	}
	
	private void readNext() {
		char ch = inputStream[currentIndex];
		if(ch == ' ' || ch == '\n' || ch == '\t') {
			return;
		}
		if(ch == '/' && (inputStream[currentIndex+1] == '/' || inputStream[currentIndex+1] == '*')) {
			skipComment();
			return;
		}
		if(ch == '"') {
			readString();
			return;
		}
		if(ch == '\'') {
			readChar();
			return;
		}
		if(Character.isDigit(ch)) {
			readNumber();
			return;
		}
		if(Character.isLetter(ch) || ch == '_') {
			readIdent();
			return;
		}
		if(";,(){}[]".indexOf(ch) >= 0) {
			Token t = new Token(Type.PUNC, ch + "");
			tokens.add(t);
			return;
		}
		if(isOp(ch)) {
			readOp();
			return;
		}
		System.err.printf("Cannot read character '%c' [%d]\n",ch,currentIndex);
	}
	
	private void skipComment() {
		if(inputStream[currentIndex+1] == '/') {
			while(inputStream[currentIndex] != '\n')
				currentIndex++;
		} else if(inputStream[currentIndex+1] == '*') {
			currentIndex += 3;
			while(inputStream[currentIndex-1] != '*' || inputStream[currentIndex] != '/') {
				currentIndex++;
			}
		}
	}

	private void readString() {
		if(inputStream[currentIndex] == '"' &&
			inputStream[currentIndex + 1] == '"' &&
			inputStream[currentIndex + 2] == '"') {
			readMultilineString();
		} else {
			StringBuilder lexem = new StringBuilder("\"");
			currentIndex++;
			while(inputStream[currentIndex] != '"') {
				if(inputStream[currentIndex] == '\\')
					lexem.append(inputStream[currentIndex++]);
				lexem.append(inputStream[currentIndex++]);
			}
			lexem.append("\"");
			tokens.add(new Token(Type.STR, lexem.toString()));
		}
	}
	
	private void readMultilineString() {
		StringBuilder lexem = new StringBuilder("\"");
		currentIndex += 3;
		while(!(inputStream[currentIndex] == '"' &&
				inputStream[currentIndex + 1] == '"' &&
				inputStream[currentIndex + 2] == '"')) {
			if(inputStream[currentIndex] == '\n' || 
					inputStream[currentIndex] == '\r' || 
					inputStream[currentIndex] == '\t') {
				currentIndex++;
				continue;
			}
			lexem.append(inputStream[currentIndex++]);
		}
		lexem.append("\"");
		currentIndex += 2;
		tokens.add(new Token(Type.STR, lexem.toString()));
	}
	
	private void readChar() {
		StringBuilder lexem = new StringBuilder("'");
		currentIndex++;
		while(inputStream[currentIndex] != '\'') {
			if(inputStream[currentIndex] == '\\')
				lexem.append(inputStream[currentIndex++]);
			lexem.append(inputStream[currentIndex++]);
		}
		lexem.append("'");
		tokens.add(new Token(Type.CHAR, lexem.toString()));
	}
	
	private void readNumber() {
		StringBuilder lexem = new StringBuilder("");
		while(isDigit(inputStream[currentIndex]) || 
				(inputStream[currentIndex] == '.' && isDigit(inputStream[currentIndex + 1]))) {
			if(inputStream[currentIndex] == 'e')
				lexem.append(inputStream[currentIndex++]);
			lexem.append(inputStream[currentIndex++]);
		}
		currentIndex--;
		tokens.add(new Token(Type.NUM, lexem.toString()));
	}
	
	private boolean isDigit(char ch) {
		return "0123456789ABCDEFbefLx_".indexOf(ch) >= 0;
	}
	
	private void readIdent() {
		StringBuilder lexem = new StringBuilder("");
		while(Character.isDigit(inputStream[currentIndex]) ||
				Character.isLetter(inputStream[currentIndex]) ||
				inputStream[currentIndex] == '_') {
			lexem.append(inputStream[currentIndex++]);
		}
		currentIndex--;
		Type type = isHardKeyword(lexem.toString()) ? Type.HKW : isSoftKeyword(lexem.toString()) ? Type.SKW : Type.VAR;
		tokens.add(new Token(type, lexem.toString()));
	}
	
	private boolean isHardKeyword(String text) {
		String keywords = " as break class continue do else false for fun if in interface is null object package return super this throw true try typealias typeof val var when while ";
		return keywords.indexOf(" " + text + " ") >= 0;
	}
	
	private boolean isSoftKeyword(String text) {
		String keywords = " until downTo by catch constructor delegate dynamic field file finally get import init param property receiver set setparam where actual abstract annotation companion const crossinline data enum expect external final inflix inline inner internal lateinit noinline open operator out override private protected public reified sealed suspend tailrec vararg field it ";
		return keywords.indexOf(" " + text + " ") >= 0;
	}
	
	private boolean isOp(char ch) {
		return "+-/*=%&|<>!@:?.".indexOf(ch) >= 0;
	}
	
	private boolean isCombOp(String op, char next) {
		return " += -= *= /= %= ++ -- && || == != === !== >= <= !! ?. ?: :: .. -> ".indexOf(op + next) >= 0;
	}
	
	private void readOp() {
		StringBuilder lexem = new StringBuilder("");
		while(isOp(inputStream[currentIndex]) && isCombOp(lexem.toString(), inputStream[currentIndex])) {
			lexem.append(inputStream[currentIndex++]);
		}
		currentIndex--;
		tokens.add(new Token(Type.OP, lexem.toString()));
	}
}
