/**
 * Copyright (C) Miklos Maroti, 2016
 */

package org.lambda.term;

public class Rewriter extends Executor<Term> {
	@Override
	public Term closure(Function function, Context<Term> context) {
		Context<Term> c = new Context<Term>(new Variable(0), increment(context));
		return new Lambda(function.evaluate(this, c));
	}

	private Context<Term> increment(Context<Term> context) {
		if (context == null)
			return context;

		return new Context<Term>(context.data.increment(0),
				increment(context.parent));
	}

	@Override
	public Term integer(int value) {
		return new Integer(value);
	}

	@Override
	public Term apply(Term func, Term arg) {
		if (func instanceof Lambda) {
			Term b = ((Lambda) func).body;
			if (b.getOccurences(0) <= 1) {
				Function f = b.compile();

				Context<Term> c = null;
				for (int i = 0; i < f.extent - 1; i++)
					c = new Context<Term>(new Variable(i), c);
				if (f.extent >= 1)
					c = new Context<Term>(arg, c);

				return f.evaluate(this, c);
			}
		}
		return new Apply(func, arg);
	}

	@Override
	public Term addition(Term left, Term right) {
		if (left instanceof Integer && right instanceof Integer) {
			int a = ((Integer) left).value;
			int b = ((Integer) right).value;
			return new Integer(a + b);
		} else
			return new Addition(left, right);
	}

	public static Rewriter INSTANCE = new Rewriter();
}
