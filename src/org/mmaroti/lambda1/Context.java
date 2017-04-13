/**
 *	Copyright (C) Miklos Maroti, 2014
 */

package org.mmaroti.lambda1;

import org.mmaroti.lambda1.Calculus.*;

public abstract class Context {
	public abstract Data getValue(Variable var);

	public abstract Data closure(Variable variable, Term expression);

	public abstract Data localize(Literal literal);

	public static abstract class Data {
		@Override
		public abstract String toString();

		public Data call(Data argument) {
			throw new IllegalStateException("data is not callable");
		}
	}
}
