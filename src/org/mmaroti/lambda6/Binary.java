package org.mmaroti.lambda6;

public abstract class Binary extends Term {
	public final Term left;
	public final Term right;

	public Binary(Term left, Term right) {
		assert left != null && right != null;

		this.left = left;
		this.right = right;
	}

	public abstract Binary create(Term left, Term right);

	@Override
	public int getExtent() {
		return Math.max(left.getExtent(), right.getExtent());
	}

	@Override
	public int getOccurences(int index) {
		return left.getOccurences(index) + right.getOccurences(index);
	}

	@Override
	public Term increment(int limit) {
		Term l = left.increment(limit);
		Term r = right.increment(limit);
		if (l == left && r == right)
			return this;
		else
			return create(l, r);
	}
}
