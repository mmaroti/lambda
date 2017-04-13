/**
 *	Copyright (C) Miklos Maroti, 2014
 */

package org.mmaroti.lambda3;

public abstract class LambdaCalculus extends Calculus {
	public abstract static class Term {
	}

	private static class Variable extends Term {
		final String name;

		Variable(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return name;
		}
	}

	@Override
	public Term variable(String namehint) {
		return new Variable(namehint);
	}

	@SuppressWarnings("unused")
	private static class Lambda extends Term {
		final Variable variable;
		final Term expression;

		Lambda(Variable variable, Term expression) {
			this.variable = variable;
			this.expression = expression;
		}
	}

	public Term lambda(Term variable, Term expression) {
		Variable var = (Variable) variable;
		return new Lambda(var, expression);
	}

	@SuppressWarnings("unused")
	private static class Apply extends Term {
		final Term function;
		final Term argument;

		Apply(Term function, Term argument) {
			this.function = function;
			this.argument = argument;
		}
	}

	public Term apply(Term function, Term argument) {
		return new Apply(function, argument);
	}
}
