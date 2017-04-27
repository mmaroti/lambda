/**
 * Copyright (C) Miklos Maroti, 2017
 */

package org.setth.core;

public abstract class Term {
	public final Type type;

	public Term(Type type) {
		this.type = type;
	}

	public static class Lambda extends Term {
		public final Term body;

		public Lambda(Type type, Term body) {
			super(new Type.Arrow(type, body.type));
			this.body = body;

			// TODO: type check occurrences of variable
		}
	}

	public static class Variable extends Term {
		public final int index;

		public Variable(Type type, int index) {
			super(type);
			assert index >= 0;
			this.index = index;
		}
	}

	public static class Apply extends Term {
		public final Term function;
		public final Term argument;

		public Apply(Term function, Term argument) {
			super(getResultType(function.type, argument.type));

			this.function = function;
			this.argument = argument;
		}

		private static Type getResultType(Type function, Type argument) {
			if (function instanceof Type.Arrow) {
				Type.Arrow arrow = (Type.Arrow) function;
				if (arrow.argument.equals(argument))
					return arrow.result;
				else
					throw new IllegalArgumentException();
			} else
				throw new IllegalArgumentException();
		}
	}
}
