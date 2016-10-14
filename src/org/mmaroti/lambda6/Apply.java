package org.mmaroti.lambda6;

public class Apply extends Binary {
	public Apply(Term left, Term right) {
		super(left, right);
	}

	@Override
	public Binary create(Term left, Term right) {
		return new Apply(left, right);
	}

	@Override
	public <DATA> Function<DATA> compile(final Compiler<DATA> compiler) {
		return compiler.apply(left, right);
	}

	@Override
	public String toString() {
		return "(" + left + ")(" + right + ")";
	}
}
