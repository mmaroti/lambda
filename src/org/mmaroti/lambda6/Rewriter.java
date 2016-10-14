/**
 * Copyright (C) Miklos Maroti, 2016
 */

package org.mmaroti.lambda6;

public class Rewriter extends Compiler<Term> {
	@Override
	public Term doLambda(Function<Term> function, Context<Term> context) {
		Context<Term> c = new Context<Term>(new Variable(0), increase(context));
		return new Lambda(function.evaluate(c));
	}

	private Context<Term> increase(Context<Term> context) {
		return context;
	}

	@Override
	public Term doInteger(int value) {
		return new Integer(value);
	}

	@Override
	public Term doApply(Term func, Term arg) {
		return new Apply(func, arg);
	}

	@Override
	public Term doAddition(Term left, Term right) {
		if (left instanceof Integer && right instanceof Integer) {
			int a = ((Integer) left).value;
			int b = ((Integer) right).value;
			return new Integer(a + b);
		} else
			return new Addition(left, right);
	}
}
