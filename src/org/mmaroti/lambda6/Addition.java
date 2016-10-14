package org.mmaroti.lambda6;

public class Addition extends Binary {
	public Addition(Term left, Term right) {
		super(left, right);
	}

	@Override
	public Binary create(Term left, Term right) {
		return new Addition(left, right);
	}

	@Override
	public <DATA> Function<DATA> compile(final Compiler<DATA> compiler) {
		return compiler.addition(left, right);
	}

	@Override
	public String toString() {
		return "(" + left + ")+(" + right + ")";
	}
}
