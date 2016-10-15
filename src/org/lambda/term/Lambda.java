package org.lambda.term;

public class Lambda extends Term {
	public final Term body;

	public Lambda(Term body) {
		assert body != null;
		this.body = body;
	}

	@Override
	public int getExtent() {
		return 1 + body.getExtent();
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
	public <DATA> Function<DATA> compile(final Compiler<DATA> compiler) {
		return compiler.lambda(body);
	}

	@Override
	public String toString() {
		return "\\" + body;
	}
}
