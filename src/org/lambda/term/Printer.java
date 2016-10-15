/**
 * Copyright (C) Miklos Maroti, 2016
 */

package org.lambda.term;

public class Printer extends Executor<String> {
	@Override
	public String closure(Function function, Context<String> context) {
		String v = Character.toString((char) ('a' + Context.length(context)));
		Context<String> c = new Context<String>(v, context);
		return "(\\" + v + "->" + function.evaluate(this, c) + ")";
	}

	@Override
	public String integer(int value) {
		return "" + value;
	}

	@Override
	public String apply(String func, String arg) {
		return "(" + func + ")(" + arg + ")";
	}

	@Override
	public String addition(String left, String right) {
		return left + "+" + right;
	}

	public static Printer INSTANCE = new Printer();
}
