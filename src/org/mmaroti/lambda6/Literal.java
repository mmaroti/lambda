package org.mmaroti.lambda6;

public abstract class Literal extends Term {
	@Override
	public int getExtent() {
		return 0;
	}

	@Override
	public int getOccurences(int index) {
		return 0;
	}

	@Override
	public Term increment(int limit) {
		return this;
	}
}
