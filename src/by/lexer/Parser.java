package by.lexer;

import java.util.ArrayList;

public class Parser {
	private ArrayList<Token> tokens;
	private ArrayList<ArgumentToken> args;
	private int currentIndex = 0;
	public Parser(ArrayList<Token> tokens) {
		args = new ArrayList<ArgumentToken>();
		this.tokens = tokens;
		parse();
	}
	
	public Parser(String raw) {
		this(new Lexer(raw).getTokens());
	}
	
	public ArrayList<ArgumentToken> getArgTokens() {
		return args;
	}
	
	private void parse() {
		while(currentIndex < tokens.size()) {
			readNext();
			currentIndex++;
		}
	}
	
	public String toString() {
		StringBuilder toReturn = new StringBuilder("");
		for(int i = 0; i < args.size(); i++)
			toReturn.append(args.get(i).toString() + "\n");
		return toReturn.toString();
	}
	
	public void readNext() {
		Token t = tokens.get(currentIndex);
		if(t.type == Type.HKW) {
			readHKW();
			return;
		}
		if(t.type == Type.SKW) {
			readSKW();
			return;
		}
		if(t.type == Type.VAR) {
			args.add(new ArgumentToken(Op.OPERAND, t.value));
			return;
		}
		if(t.type == Type.OP) {
			readOP();
			return;
		}
		if(t.type == Type.NUM || t.type == Type.CHAR || t.type == Type.STR) {
			args.add(new ArgumentToken(Op.OPERAND, t.value));
			return;
		}
		if(t.type == Type.PUNC) {
			readPunc();
			return;
		}
		System.err.printf("Cannot read character '%s' [%s]\n",t.type,t.value);
	}
	
	private void readHKW() {
		if(tokens.get(currentIndex).value.equals("true") || tokens.get(currentIndex).value.equals("false") || tokens.get(currentIndex).value.equals("null")) {
			args.add(new ArgumentToken(Op.OPERAND, tokens.get(currentIndex).value));
			return;
		}
		if(tokens.get(currentIndex).value.equals("do")) {
			int amount = 1;
			int temp = currentIndex;
			temp += 2;
			while(amount != 0) {
				if(tokens.get(temp).value.equals("{"))
					amount++;
				else if(tokens.get(temp).value.equals("}"))
					amount--;
				temp++;
			}
			tokens.remove(temp);
			tokens.remove(temp);
			args.add(new ArgumentToken(Op.OPERAND, "do..while()"));
			return;
		}
		args.add(new ArgumentToken(Op.OPERATOR, tokens.get(currentIndex).value));
	}
	
	private void readSKW() {
		if(tokens.get(currentIndex).value.equals("catch")) {
			int index = 0;
			for(index = args.size() - 1; !args.get(index).value.equals("try"); index--);
			args.get(index).value = "try..catch()";
			currentIndex++;
			return;
		}
		if(tokens.get(currentIndex).value.equals("finally")) {
			int index = 0;
			for(index = args.size() - 1; !args.get(index).value.equals("try..catch()"); index--);
			args.get(index).value = "try..catch()..finally";
			return;
		}
		args.add(new ArgumentToken(Op.OPERATOR, tokens.get(currentIndex).value));
	}
	
	private void readOP() {
		if(tokens.get(currentIndex).value.equals(".") && tokens.get(currentIndex + 1).type == Type.NUM) {
			tokens.get(currentIndex + 1).value = '.' + tokens.get(currentIndex + 1).value;
			return;
		} 
		if (tokens.get(currentIndex).value.equals(".")) {
			args.get(args.size() - 1).value += "." +  tokens.get(currentIndex + 1).value;
			currentIndex++;
			return;
		} 
		if (tokens.get(currentIndex).value.equals(">")) {
			int index;
			for(index = args.size() - 1; args.get(index).op != Op.OPERATOR; index--);
			if(args.get(index).value.equals("<")) {
				args.get(index).value = "<>";
				return;
			}
		}
		args.add(new ArgumentToken(Op.OPERATOR, tokens.get(currentIndex).value));
	}
	
	private void readPunc() {
		if(tokens.get(currentIndex).value.equals("(") && (tokens.get(currentIndex - 1).type == Type.HKW || tokens.get(currentIndex - 1).type == Type.SKW)) {
			args.get(args.size() - 1).value += "()" ;
			return;
		}
		if(tokens.get(currentIndex).value.equals("(") && tokens.get(currentIndex - 1).type == Type.VAR) {
			args.get(args.size() - 1).value += "()" ;
			args.get(args.size() - 1).op = Op.OPERATOR ;
			return;
		}
		if(tokens.get(currentIndex).value.equals("(")) {
			args.add(new ArgumentToken(Op.OPERATOR, "()"));
			return;
		}
		if(tokens.get(currentIndex).value.equals("{")) {
			args.add(new ArgumentToken(Op.OPERATOR, "{}"));
			return;
		}
		if(tokens.get(currentIndex).value.equals("[")) {
			args.add(new ArgumentToken(Op.OPERATOR, "[]"));
			return;
		}
		if(tokens.get(currentIndex).value.equals(";")) {
			args.add(new ArgumentToken(Op.OPERATOR, ";"));
			return;
		}
	}
}
