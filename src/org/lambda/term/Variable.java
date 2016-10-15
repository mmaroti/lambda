/**
 * Copyright (C) Miklos Maroti, 2016
 */

package org.lambda.term;

public class Variable extends Term {
	public final int index;

	public Variable(int index) {
		assert index >= 0;
		this.index = index;
	}

	@Override
	public int getExtent() {
		return index + 1;
	}

	@Override
	public int getOccurences(int index) {
		return index == this.index ? 1 : 0;
	}

	@Override
	public Term increment(int limit) {
		if (index < limit)
			return this;
		else
			return new Variable(index + 1);
	}

	@Override
	public Function compile() {
		return new Function() {
			@Override
			public <DATA> DATA evaluate(Executor<DATA> executor,
					Context<DATA> context) {
				for (int i = 0; i < index; i++)
					context = context.parent;

				return context.data;
			}
		};
	}

	@Override
	public String toString() {
		return Character.toString((char) ('x' + index));
	}
}
