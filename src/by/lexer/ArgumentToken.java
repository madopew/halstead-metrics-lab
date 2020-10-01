package by.lexer;

import by.lexer.Enums.Op;

public class ArgumentToken {
	public Op op;
	public String value;
	public ArgumentToken(Op op, String value) {
		this.op = op;
		this.value = value;
	}
	
	public String toString() {
		return "{type: " + op + ", value: " + value + "}";
	}
}
