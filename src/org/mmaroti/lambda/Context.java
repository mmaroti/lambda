/**
 *	Copyright (C) Miklos Maroti, 2014
 */

package org.mmaroti.lambda;

import org.mmaroti.lambda.Calculus.*;

public abstract class Context {
	public abstract Data getValue(Variable var);

	public abstract Data closure(Variable variable, Term expression);

	public abstract Data localize(Literal literal);

	public static abstract class Data {
		public abstract String toString();

		public Data call(Data argument) {
			throw new IllegalStateException("data is not callable");
		}
	}
}
