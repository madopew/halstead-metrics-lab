package by.lexer;

import java.util.ArrayList;

public class Metric {
	private ArrayList<ArgumentToken> argTokens;
	private ArrayList<Argument> argOperators;
	private ArrayList<Argument> argOperands;
	private int nu1;
	private int nu2;
	private int n1;
	private int n2;
	private int nu;
	private int n;
	private int v;
	public Metric(ArrayList<ArgumentToken> argTokens) {
		this.argTokens = argTokens;
		argOperators = new ArrayList<Argument>();
		argOperands = new ArrayList<Argument>();
		countMetrics();
	}
	
	public Metric(String raw) {
		this(new Parser(new Lexer(raw).getTokens()).getArgTokens());
	}
	
	public ArrayList<Argument> getOperatorsArgs() {
		return argOperators;
	}
	
	public ArrayList<Argument> getOperandsArgs() {
		return argOperands;
	}
	
	public int getNu1() {
		return nu1;
	}
	
	public int getNu2() {
		return nu2;
	}
	
	public int getN1() {
		return n1;
	}
	
	public int getN2() {
		return n2;
	}
	
	public int getNu() {
		return nu;
	}
	
	public int getN() {
		return n;
	}
	
	public int getV() {
		return v;
	}
	
	private void countMetrics() {
		argTokens.forEach(at -> countArg(at.op == Op.OPERATOR ? 
				argOperators : argOperands, at));
		argOperators.sort((t1, t2) -> Integer.compare(t2.amount, t1.amount));
		argOperands.sort((t1, t2) -> Integer.compare(t2.amount, t1.amount));
		nu1 = argOperators.size();
		nu2 = argOperands.size();
		argOperators.forEach(a -> n1 += a.amount);
		argOperands.forEach(a -> n2 += a.amount);
		nu = nu1 + nu2;
		n = n1 + n2;
		v = (int) Math.ceil(n * (Math.log(nu) / Math.log(2)));
	}
	
	private void countArg(ArrayList<Argument> args, ArgumentToken at) {
		int index = getIndexOf(args, at);
		if(index == -1) {
			args.add(new Argument(at.value));
		} else {
			args.get(index).incAmount();
		}
	}
	
	private int getIndexOf(ArrayList<Argument> args, ArgumentToken at) {
		for(int i = 0; i < args.size(); i++) {
			if(at.value.equals(args.get(i).value))
				return i;
		}
		return -1;
	}
}
