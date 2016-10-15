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

	public static void main(String[] args) {
		Term a = new Addition(new Variable(1), new Variable(0));
		System.out.println(a);

		Term b = new Lambda(new Variable(0));
		System.out.println(b);

		Term c = new Lambda(new Variable(1));
		System.out.println(c);

		Term d = new Apply(b, new Integer(1));
		System.out.println(d);

		Rewriter rewriter = new Rewriter();
		System.out.println(c.evaluate(rewriter, a));

		System.out
				.println(a.evaluate(rewriter, new Integer(1), new Integer(2)));

		System.out.println(a.evaluate(rewriter, new Variable(0), b));
	}
}
