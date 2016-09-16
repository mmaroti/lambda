/**
 *	Copyright (C) Miklos Maroti, 2016
 */

package org.mmaroti.lambda5.term;

import org.mmaroti.lambda5.data.*;

public abstract class Term {
	public abstract int getExtent();

	public boolean isClosed() {
		return getExtent() == 0;
	}

	public abstract Data evaluate(Context<Data> context);

	protected abstract int precedence();

	protected abstract void format(StringBuilder builder, Context<String> scope);

	protected void format(StringBuilder builder, Context<String> context, int prec) {
		boolean b = prec > precedence();
		if (b)
			builder.append('(');

		format(builder, context);

		if (b)
			builder.append(')');
	}

	@Override
	public String toString() {
		assert isClosed();

		StringBuilder builder = new StringBuilder();
		format(builder, null);
		return builder.toString();
	}

	public String toString(Context<String> context) {
		assert getExtent() <= Context.getExtent(context);

		StringBuilder builder = new StringBuilder();
		format(builder, context);
		return builder.toString();
	}

	public static void main(String[] args) {
		Term i = new Lambda("x", new Variable(0));
		System.out.println(i);
		System.out.println(i.evaluate(null));

		Term k = new Lambda("x", new Lambda("y", new Variable(1)));
		System.out.println(k);

		Term s = new Lambda("x", new Lambda("y", new Lambda("z", new Apply(new Apply(new Variable(2), new Variable(0)),
				new Apply(new Variable(1), new Variable(0))))));
		System.out.println(s);

		Term f = new IntLiteral(5);
		System.out.println(f);
		System.out.println(f.evaluate(null));

		Term i5 = new Apply(i, f);
		System.out.println(i5);
		System.out.println(i5.evaluate(null));

		Term kf = new Apply(k, f);
		System.out.println(kf);
		System.out.println(kf.evaluate(null));

		Term sf = new Apply(s, f);
		System.out.println(sf);
		System.out.println(sf.evaluate(null));
	}
}
