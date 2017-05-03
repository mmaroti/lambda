/**
 * Copyright (C) Miklos Maroti, 2016-2017
 */

package org.lambda.term;

public class WriterExec<LIT> extends Executor<Term<LIT>, LIT> {
	@Override
	public Term<LIT> closure(Evaluable<LIT> function, Context<Term<LIT>> context) {
		Context<Term<LIT>> c = new Context<Term<LIT>>(new Variable<LIT>(0), increment(context));
		return new Lambda<LIT>(function.evaluate(this, c));
	}

	private Context<Term<LIT>> increment(Context<Term<LIT>> context) {
		if (context == null)
			return context;

		return new Context<Term<LIT>>(context.data.increment(0), increment(context.parent));
	}

	@Override
	public Term<LIT> integer(int value) {
		return new Integer<LIT>(value);
	}

	@Override
	public Term<LIT> apply(Term<LIT> func, Term<LIT> arg) {
		return new Apply<LIT>(func, arg);
	}

	@Override
	public Term<LIT> addition(Term<LIT> left, Term<LIT> right) {
		return new Addition<LIT>(left, right);
	}

	@Override
	public Term<LIT> literal(LIT value) {
		return new Literal<LIT>(value);
	}
}
