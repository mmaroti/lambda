package org.mmaroti.lambda6;

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
	public <DATA> Function<DATA> compile(Compiler<DATA> compiler) {
		return compiler.variable(index);
	}

	@Override
	public String toString() {
		return Character.toString((char) ('x' + index));
	}
}
