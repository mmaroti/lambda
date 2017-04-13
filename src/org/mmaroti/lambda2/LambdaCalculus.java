/**
 *	Copyright (C) Miklos Maroti, 2014
 */

package org.mmaroti.lambda2;

import java.util.*;

public class LambdaCalculus extends Calculus {
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

		public abstract <DATA> DATA evaluate(Context<DATA> context,
				Runtime<DATA> runtime);
	}

	public static class Literal extends Term {
		public final Object data;

		public Literal(Object data) {
			this.data = data;
		}

		@Override
		public String toString() {
			return data.toString();
		}

		@Override
		public <DATA> DATA evaluate(Context<DATA> context, Runtime<DATA> runtime) {
			return runtime.lift(data);
		}
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
		public <DATA> DATA evaluate(Context<DATA> context, Runtime<DATA> runtime) {
			return context.getValue(this);
		}
	}

	public static class Lambda extends Term {
		public final Variable variable;
		public final Term expression;

		public Lambda(Variable variable, Term expression) {
			this.variable = variable;
			this.expression = expression;

			variables.addAll(expression.variables);
			variables.remove(variable);
		}

		@Override
		public String toString() {
			return "(lambda " + variable + " " + expression + ")";
		}

		@Override
		public <DATA> DATA evaluate(Context<DATA> context, Runtime<DATA> runtime) {
			return runtime.lambda(new Closure<DATA>(context, this));
		}
	}

	public static class Apply extends Term {
		public final Term function;
		public final Term argument;

		public Apply(Term function, Term argument) {
			this.function = function;
			this.argument = argument;

			variables.addAll(function.variables);
			variables.addAll(argument.variables);
		}

		@Override
		public String toString() {
			return "(apply " + function + " " + argument + ")";
		}

		@Override
		public <DATA> DATA evaluate(Context<DATA> context, Runtime<DATA> runtime) {
			DATA fun = function.evaluate(context, runtime);
			return runtime.apply(fun, new Thunk<DATA>(context, argument));
		}
	}

	public static class Context<DATA> {
		public final Context<DATA> parent;
		public final Variable variable;
		public final DATA data;

		public Context(Context<DATA> parent, Variable variable, DATA data) {
			this.parent = parent;
			this.variable = variable;
			this.data = data;
		}

		DATA getValue(Variable var) {
			Context<DATA> context = this;

			do {
				if (variable == var)
					return data;

				context = context.parent;
			} while (context != null);

			throw new IllegalArgumentException();
		}
	}

	public static class Thunk<DATA> extends Calculus.Thunk<DATA> {
		public final Term expression;
		public final Context<DATA> context;

		public Thunk(Context<DATA> context, Term expression) {
			this.expression = expression;
			this.context = context;
		}

		@Override
		public DATA execute(Runtime<DATA> runtime) {
			return expression.evaluate(context, runtime);
		}
	}

	public static class Closure<DATA> extends Calculus.Closure<DATA> {
		public final Lambda lambda;
		public final Context<DATA> context;

		public Closure(Context<DATA> context, Lambda lambda) {
			this.lambda = lambda;
			this.context = context;
		}

		@Override
		public DATA execute(Runtime<DATA> runtime, DATA argument) {
			Context<DATA> context = new Context<DATA>(this.context, lambda.variable,
					argument);
			return lambda.expression.evaluate(context, runtime);
		}
	}
}
