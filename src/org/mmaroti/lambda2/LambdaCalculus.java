/**
 *	Copyright (C) Miklos Maroti, 2014
 */

package org.mmaroti.lambda2;

import java.util.*;

public class LambdaCalculus {
	public static abstract class Term {
		public final Set<Variable> variables = new HashSet<Variable>();

		public Variable findVariable(String name) {
			for (Variable v : variables) {
				if (name.equals(v.name))
					return v;
			}
			return null;
		}

		public abstract String toString();

		public abstract <DATA> DATA evaluate(Context<DATA> context,
				Runtime<DATA> runtime);
	}

	public static class Variable extends Term {
		public final String name;

		public Variable(String name) {
			this.name = name;
			variables.add(this);
		}

		public String toString() {
			return name;
		}

		public <DATA> DATA evaluate(Context<DATA> context, Runtime<DATA> runtime) {
			return context.getValue(this);
		}
	}

	public static class Literal extends Term {
		public final Object data;

		public Literal(Object data) {
			this.data = data;
		}

		public String toString() {
			return data.toString();
		}

		public DATA evaluate(Context<DATA> context, Runtime<DATA> runtime) {
			return data;
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

		public String toString() {
			return "(lambda " + variable + " " + expression + ")";
		}

		public <DATA> DATA evaluate(Context<DATA> context, Runtime<DATA> runtime) {
			return runtime.compile(new MyClosure<DATA>(this, context));
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

		public String toString() {
			return "(apply " + function + " " + argument + ")";
		}

		public <DATA> DATA evaluate(Context<DATA> context, Runtime<DATA> runtime) {
			DATA fun = function.evaluate(context, runtime);
			DATA arg = argument.evaluate(context, runtime);
			return runtime.apply(fun, arg);
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

	public static class MyClosure<DATA> extends Closure<DATA> {
		public final Lambda lambda;
		public final Context<DATA> context;

		public MyClosure(Lambda lambda, Context<DATA> context) {
			this.lambda = lambda;
			this.context = context;
		}

		public DATA execute(Runtime<DATA> runtime, DATA argument) {
			Context<DATA> c = new Context<DATA>(context, lambda.variable,
					argument);
			return lambda.expression.evaluate(c, runtime);
		}
	}
}
