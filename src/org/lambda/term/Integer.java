package org.lambda.term;

public class Integer extends Literal {
	public final int value;

	public Integer(int value) {
		this.value = value;
	}

	@Override
	public <DATA> Function<DATA> compile(Compiler<DATA> compiler) {
		return compiler.integer(value);
	}

	@Override
	public String toString() {
		return "" + value;
	}
}
