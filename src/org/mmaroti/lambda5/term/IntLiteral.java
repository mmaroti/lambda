package org.mmaroti.lambda5.term;

import org.mmaroti.lambda5.data.*;

public class IntLiteral extends Term {
	public final int value;

	public IntLiteral(int value) {
		this.value = value;
	}

	@Override
	public int getExtent() {
		return 0;
	}

	@Override
	public Data evaluate(Context context) {
		return new IntData(value);
	}
}
