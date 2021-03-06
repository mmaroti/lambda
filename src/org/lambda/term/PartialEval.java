/**
 * Copyright (C) Miklos Maroti, 2016-2017
 */

package org.lambda.term;

import org.lambda.eval.*;

public class PartialEval<LIT> extends Evaluator<Term<LIT>, LIT> {
	public final Evaluator<LIT, LIT> interpreter;

	public PartialEval(Evaluator<LIT, LIT> interpreter) {
		this.interpreter = interpreter;
	}

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
		if (func instanceof Lambda) {
			Term<LIT> b = ((Lambda<LIT>) func).body;
			if (b.getOccurences(0) <= 1 || arg instanceof Variable || arg instanceof Literal) {
				Context<Term<LIT>> c = null;
				for (int i = 0; i < b.getExtent() - 1; i++)
					c = new Context<Term<LIT>>(new Variable<LIT>(i), c);
				c = new Context<Term<LIT>>(arg, c);

				return b.evaluate(this, c);
			}
		} else if (func instanceof Literal && arg instanceof Literal) {
			Literal<LIT> f = (Literal<LIT>) func;
			Literal<LIT> a = (Literal<LIT>) arg;
			return new Literal<LIT>(interpreter.apply(f.value, a.value));
		}
		return new Apply<LIT>(func, arg);
	}

	@Override
	public Term<LIT> literal(LIT value) {
		return new Literal<LIT>(value);
	}

	@Override
	public Term<LIT> primitive(String prim) {
		return new Literal<LIT>(interpreter.primitive(prim));
	}
}
