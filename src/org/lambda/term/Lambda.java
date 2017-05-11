/**
 * Copyright (C) Miklos Maroti, 2016-2017
 */

package org.lambda.term;

import org.lambda.eval.*;

public class Lambda<LIT> extends Term<LIT> {
	public final Term<LIT> type;
	public final Term<LIT> body;

	public Lambda(Term<LIT> type, Term<LIT> body) {
		this.type = type;
		this.body = body;
	}

	@Override
	public int getExtent() {
		return Math.max(type.getExtent(), body.getExtent() - 1);
	}

	@Override
	public int getOccurences(int index) {
		return type.getOccurences(index) + body.getOccurences(index + 1);
	}

	@Override
	public Term<LIT> increment(int limit) {
		Term<LIT> t = type.increment(limit);
		Term<LIT> b = body.increment(limit + 1);
		if (t == type && b == body)
			return this;
		else
			return new Lambda<LIT>(t, b);
	}

	@Override
	public <DATA> DATA evaluate(Evaluator<DATA, LIT> evaluator,
			Context<DATA> context) {
		DATA t = type.evaluate(evaluator, context);
		return evaluator.closure(t, body, context);
	}
}
