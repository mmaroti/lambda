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
			Term body = ((Lambda) func).body;
			int n = body.getOccurences(0);

			if (n == 0)
				return body.decrement(0);
			else if (n == 1)
				return body.rewrite(arg); // TODO: need a decrement?
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

	public static void main(String[] args) {
		Term a = new Addition(new Variable(2), new Variable(1));
		System.out.println(a);
		System.out.println(a.rewrite());
		System.out.println(a.rewrite(new Integer(1)));
		System.out.println(a.rewrite(new Integer(1), new Integer(2)));

		Term b = new Lambda(a);
		System.out.println(b);
		System.out.println(b.rewrite());
		System.out.println(b.rewrite(new Integer(1)));
		System.out.println(b.rewrite(new Integer(1), new Integer(2)));

		Term c = new Lambda(b);
		System.out.println(c);
		System.out.println(c.rewrite());
		System.out.println(c.rewrite(new Integer(1)));
		System.out.println(c.rewrite(new Integer(1), new Integer(2)));

		Term d = new Apply(b, new Integer(3));
		System.out.println(d);
		System.out.println(d.rewrite());
		System.out.println(d.rewrite(new Integer(1)));
		System.out.println(d.rewrite(new Integer(1), new Integer(2)));

		Term e = new Apply(c, new Integer(4));
		System.out.println(e);
		System.out.println(e.rewrite());
		System.out.println(e.rewrite(new Integer(1)));
		System.out.println(e.rewrite(new Integer(1), new Integer(2)));
	}
}
