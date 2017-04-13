/**
 * Copyright (C) Miklos Maroti, 2016-2017
 */

package org.lambda.term;

public abstract class Binary<LIT> extends Term<LIT> {
	public final Term<LIT> left;
	public final Term<LIT> right;

	public Binary(Term<LIT> left, Term<LIT> right) {
		assert left != null && right != null;

		this.left = left;
		this.right = right;
	}

	public abstract Binary<LIT> create(Term<LIT> left, Term<LIT> right);

	@Override
	public int getExtent() {
		return Math.max(left.getExtent(), right.getExtent());
	}

	@Override
	public int getOccurences(int index) {
		return left.getOccurences(index) + right.getOccurences(index);
	}

	@Override
	public Term<LIT> increment(int limit) {
		Term<LIT> l = left.increment(limit);
		Term<LIT> r = right.increment(limit);
		if (l == left && r == right)
			return this;
		else
			return create(l, r);
	}

	@Override
	public Term<LIT> decrement(int limit) {
		Term<LIT> l = left.increment(limit);
		Term<LIT> r = right.increment(limit);
		if (l == left && r == right)
			return this;
		else
			return create(l, r);
	}
}
