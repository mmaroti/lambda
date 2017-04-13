/**
 *	Copyright (C) Miklos Maroti, 2014
 */

package org.mmaroti.lambda1;

import java.util.*;

import org.mmaroti.lambda1.Context.*;

public class Calculus {
	public static abstract class Term {
		public final Set<Variable> variables = new HashSet<Variable>();

		public Variable findVariable(String name) {
			for (Variable v : variables) {
				if (name.equals(v.name))
					return v;
			}
			return null;
		}

		@Override
		public abstract String toString();

		public abstract Data evaluate(Context context);
	}

	public static class Variable extends Term {
		public final String name;

		public Variable(String name) {
			this.name = name;
			variables.add(this);
		}

		@Override
		public String toString() {
			return name;
		}

		@Override
		public Data evaluate(Context context) {
			if (context == null)
				throw new IllegalArgumentException();

			return context.getValue(this);
		}
	}

	public static class Literal extends Term {
		public final Data data;

		public Literal(Data data) {
			this.data = data;
		}

		@Override
		public String toString() {
			return data.toString();
		}

		@Override
		public Data evaluate(Context context) {
			return context.localize(this);
		}
	}
}
