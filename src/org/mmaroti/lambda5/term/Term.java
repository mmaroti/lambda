package org.mmaroti.lambda5.term;

import org.mmaroti.lambda5.data.*;

public abstract class Term {
	public abstract int getExtent();

	public boolean isClosed() {
		return getExtent() == 0;
	}

	public abstract Data evaluate(Context context);
}
