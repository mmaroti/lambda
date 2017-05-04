/**
 * Copyright (C) Miklos Maroti, 2016-2017
 */

package org.lambda.term;

import org.lambda.exec.*;

public class RewriterExec<LIT> extends Executor<Term<LIT>, LIT> {
	@Override
	public Term<LIT> evaluate(Evaluable<LIT> evaluable) {
		Context<Term<LIT>> c = null;
		for (int i = evaluable.getExtent() - 1; i >= 0; i--)
			c = new Context<Term<LIT>>(new Variable<LIT>(i), c);

		return evaluable.evaluate(this, c);
	}

	@Override
	public Term<LIT> closure(Evaluable<LIT> function, Context<Term<LIT>> context) {
		Context<Term<LIT>> c = new Context<Term<LIT>>(new Variable<LIT>(0),
				increment(context));
		return new Lambda<LIT>(function.evaluate(this, c));
	}

	private Context<Term<LIT>> increment(Context<Term<LIT>> context) {
		if (context == null)
			return context;

		return new Context<Term<LIT>>(context.data.increment(0),
				increment(context.parent));
	}

	@Override
	public Term<LIT> integer(int value) {
		return new Integer<LIT>(value);
	}

	@Override
	public Term<LIT> apply(Term<LIT> func, Term<LIT> arg) {
		if (func instanceof Lambda) {
			Term<LIT> b = ((Lambda<LIT>) func).body;
			if (b.getOccurences(0) <= 1 || arg instanceof Variable
					|| arg instanceof Literal || arg instanceof Integer) {
				Context<Term<LIT>> c = null;
				for (int i = 0; i < b.getExtent() - 1; i++)
					c = new Context<Term<LIT>>(new Variable<LIT>(i), c);
				if (b.getExtent() >= 1)
					c = new Context<Term<LIT>>(arg, c);

				return b.evaluate(this, c);
			}
		}
		return new Apply<LIT>(func, arg);
	}

	@Override
	public Term<LIT> addition(Term<LIT> left, Term<LIT> right) {
		if (left instanceof Integer && right instanceof Integer) {
			int a = ((Integer<LIT>) left).value;
			int b = ((Integer<LIT>) right).value;
			return new Integer<LIT>(a + b);
		} else
			return new Addition<LIT>(left, right);
	}

	@Override
	public Term<LIT> literal(LIT value) {
		return new Literal<LIT>(value);
	}
}
