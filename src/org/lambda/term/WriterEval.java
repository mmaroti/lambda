/**
 * Copyright (C) Miklos Maroti, 2016-2017
 */

package org.lambda.term;

import org.lambda.eval.*;

public class WriterEval<LIT> extends Evaluator<Term<LIT>, LIT> {
	@Override
	public Term<LIT> evaluate(Evaluable<LIT> evaluable) {
		Context<Term<LIT>> c = null;
		for (int i = evaluable.getExtent() - 1; i >= 0; i--)
			c = new Context<Term<LIT>>(new Variable<LIT>(i), c);

		return evaluable.evaluate(this, c);
	}

	@Override
	public Term<LIT> closure(Context<Term<LIT>> context, Term<LIT> type, Evaluable<LIT> body) {
		Context<Term<LIT>> c = new Context<Term<LIT>>(new Variable<LIT>(0), increment(context));
		return new Lambda<LIT>(type, body.evaluate(this, c));
	}

	private Context<Term<LIT>> increment(Context<Term<LIT>> context) {
		if (context == null)
			return context;

		return new Context<Term<LIT>>(context.data.increment(0), increment(context.parent));
	}

	@Override
	public Term<LIT> apply(Term<LIT> func, Term<LIT> arg) {
		return new Apply<LIT>(func, arg);
	}

	@Override
	public Term<LIT> literal(LIT value) {
		return new Literal<LIT>(value);
	}

	@Override
	public Term<LIT> primitive(String prim) {
		return new Primitive<LIT>(prim);
	}
}
