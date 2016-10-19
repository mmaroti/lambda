/**
 * Copyright (C) Miklos Maroti, 2016
 */

package org.lambda.term;

public class Printer extends Executor<Printer.Data> {
	public static class Data {
		public final static int ATOM = 0;
		public final static int APPLY = 1;
		public final static int PROD = 2;
		public final static int SUM = 3;
		public final static int LAMBDA = 4;

		public final int precedence;
		public final String value;

		public Data(int precedence, String value) {
			this.precedence = precedence;
			this.value = value;
		}

		public Data(char c) {
			this(ATOM, Character.toString(c));
		}

		public Data(int n) {
			this(ATOM, java.lang.Integer.toString(n));
		}

		public String format(int prec) {
			if (precedence <= prec)
				return value;
			else
				return "(" + value + ")";
		}
	}

	@Override
	public Data closure(Function function, Context<Data> context) {
		Data v = new Data((char) ('a' + Context.length(context)));
		Context<Data> c = new Context<Data>(v, context);
		Data d = function.evaluate(this, c);

		return new Data(Data.LAMBDA, "\\" + v.value + " -> "
				+ d.format(Data.LAMBDA));
	}

	@Override
	public Data integer(int value) {
		return new Data(value);
	}

	@Override
	public Data apply(Data func, Data arg) {
		return new Data(Data.APPLY, func.format(Data.APPLY) + " "
				+ arg.format(Data.APPLY - 1));
	}

	@Override
	public Data addition(Data left, Data right) {
		return new Data(Data.SUM, left.format(Data.SUM) + " + "
				+ right.format(Data.SUM));
	}

	public static Printer INSTANCE = new Printer();
}
