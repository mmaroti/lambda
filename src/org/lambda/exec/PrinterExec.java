/**
 * Copyright (C) Miklos Maroti, 2016-2017
 */

package org.lambda.exec;

public class PrinterExec<LIT> extends Executor<PrinterExec.Data, LIT> {
	public static PrinterExec<Object> INSTANCE = new PrinterExec<Object>();

	public static class Data {
		public final static int ATOM = 0;
		public final static int APPLY = 1;
		public final static int PROD = 2;
		public final static int SUM = 3;
		public final static int LAMBDA = 4;

		public final int precedence;
		public final int extent;
		public final String value;

		public Data(int precedence, int extent, String value) {
			this.precedence = precedence;
			this.extent = extent;
			this.value = value;
		}

		public String format(int prec) {
			if (precedence <= prec)
				return value;
			else
				return "(" + value + ")";
		}
	}

	public static Data variable(int index) {
		String name = Character.toString((char) ('a' + index));
		return new Data(Data.ATOM, index + 1, name);
	}

	private int getExtent(Context<Data> context) {
		int a = 0;
		while (context != null) {
			a = Math.max(a, context.data.extent);
			context = context.parent;
		}
		return a;
	}

	@Override
	public Data evaluate(Evaluable<LIT> evaluable) {
		Context<Data> c = null;
		for (int i = 0; i < evaluable.getExtent(); i++)
			c = new Context<PrinterExec.Data>(variable(i), c);

		return evaluable.evaluate(this, c);
	}

	@Override
	public Data closure(Evaluable<LIT> function, Context<Data> context) {
		int ext = getExtent(context);
		Data var = variable(ext);
		Data body = function.evaluate(this, new Context<Data>(var, context));

		return new Data(Data.LAMBDA, ext, var.value + " -> "
				+ body.format(Data.LAMBDA));
	}

	@Override
	public Data integer(int value) {
		return new Data(Data.ATOM, 0, java.lang.Integer.toString(value));
	}

	@Override
	public Data apply(Data func, Data arg) {
		return new Data(Data.APPLY, Math.max(func.extent, arg.extent),
				func.format(Data.APPLY) + " " + arg.format(Data.APPLY - 1));
	}

	@Override
	public Data pair(Data left, Data right) {
		return new Data(Data.ATOM, Math.max(left.extent, right.extent), "("
				+ left.format(Data.ATOM) + "," + right.format(Data.ATOM) + ")");
	}

	@Override
	public Data addition(Data left, Data right) {
		return new Data(Data.SUM, Math.max(left.extent, right.extent),
				left.format(Data.SUM) + " + " + right.format(Data.SUM));
	}

	@Override
	public Data literal(LIT value) {
		return new Data(Data.ATOM, 0, value.toString());
	}
}
