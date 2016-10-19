/**
 * Copyright (C) Miklos Maroti, 2016
 */

package org.lambda.term;

public class Lambda extends Term {
	public final Term body;

	public Lambda(Term body) {
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
	public Term increment(int limit) {
		Term b = body.increment(limit + 1);
		if (b == body)
			return this;
		else
			return new Lambda(b);
	}

	@Override
	public Term decrement(int limit) {
		Term b = body.decrement(limit + 1);
		if (b == body)
			return this;
		else
			return new Lambda(b);
	}

	@Override
	public Function compile() {
		final Function function = body.compile();
		return new Function() {
			@Override
			public <DATA> DATA evaluate(Executor<DATA> executor,
					Context<DATA> context) {
				return executor.closure(function, context);
			}
		};
	}
}
