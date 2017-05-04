/**
 * Copyright (C) Miklos Maroti, 2016-2017
 */

package org.lambda.term;

import org.lambda.exec.*;

public class Lambda<LIT> extends Term<LIT> {
	public final Term<LIT> body;

	public Lambda(Term<LIT> body) {
		assert body != null;
		this.body = body;
	}

	@Override
	public int getExtent() {
		return Math.max(body.getExtent() - 1, 0);
	}

	@Override
	public int getOccurences(int index) {
		return body.getOccurences(index + 1);
	}

	@Override
	public Term<LIT> increment(int limit) {
		Term<LIT> b = body.increment(limit + 1);
		if (b == body)
			return this;
		else
			return new Lambda<LIT>(b);
	}

	@Override
	public Term<LIT> decrement(int limit) {
		Term<LIT> b = body.decrement(limit + 1);
		if (b == body)
			return this;
		else
			return new Lambda<LIT>(b);
	}

	@Override
	public <DATA> DATA evaluate(Executor<DATA, LIT> executor,
			Context<DATA> context) {
		return executor.closure(body, context);
	}
}
