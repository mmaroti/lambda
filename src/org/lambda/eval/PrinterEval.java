/**
 * Copyright (C) Miklos Maroti, 2016-2017
 */

package org.lambda.eval;

public class PrinterEval<LIT> extends Evaluator<PrinterEval.Data, LIT> {
	public static PrinterEval<Object> INSTANCE = new PrinterEval<Object>();

	private final static int ATOM = 0;
	private final static int APPLY = 1;
	private final static int LAMBDA = 4;

	public static class Data {
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
		return new Data(ATOM, index + 1, name);
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
			c = new Context<PrinterEval.Data>(variable(i), c);

		return evaluable.evaluate(this, c);
	}

	@Override
	public Data closure(Evaluable<LIT> function, Context<Data> context) {
		int ext = getExtent(context);
		Data var = variable(ext);
		Data body = function.evaluate(this, new Context<Data>(var, context));

		return new Data(LAMBDA, ext, var.value + " -> " + body.format(LAMBDA));
	}

	@Override
	public Data apply(Data func, Data arg) {
		return new Data(APPLY, Math.max(func.extent, arg.extent),
				func.format(APPLY) + " " + arg.format(APPLY - 1));
	}

	@Override
	public Data literal(LIT value) {
		return new Data(ATOM, 0, value.toString());
	}
}
