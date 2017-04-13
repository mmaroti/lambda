/**
 *	Copyright (C) Miklos Maroti, 2014
 */

package org.mmaroti.lambda4;

public class Calculus implements Runtime<Calculus.Term> {
	public abstract static class Term {
		protected Term apply(Term argument) {
			return new Application(this, argument);
		}

		protected abstract Term substitute(Variable variable, Term expression);

		protected abstract <TARGET> TARGET lift(Runtime<TARGET> runtime,
				Context<TARGET> context);
	}

	private static class Application extends Term {
		final Term function;
		final Term argument;

		Application(Term function, Term argument) {
			this.function = function;
			this.argument = argument;
		}

		@Override
		protected <TARGET> TARGET lift(Runtime<TARGET> runtime,
				Context<TARGET> context) {
			TARGET f = function.lift(runtime, context);
			TARGET a = argument.lift(runtime, context);
			return runtime.apply(f, a);
		}

		@Override
		protected Term substitute(Variable variable, Term expression) {
			Term f = function.substitute(variable, expression);
			Term a = argument.substitute(variable, expression);
			return f.apply(a);
		}
		
		@Override
		public String toString() {
			return function + "(" + argument + ")";
		}
	}

	private static class Boolean extends Term {
		final boolean value;

		Boolean(boolean value) {
			this.value = value;
		}

		@Override
		protected Term apply(Term argument) {
			throw new IllegalArgumentException("Boolean is not applicable");
		}

		@Override
		protected <TARGET> TARGET lift(Runtime<TARGET> runtime,
				Context<TARGET> context) {
			return runtime.literal(toString());
		}

		@Override
		protected Term substitute(Variable variable, Term expression) {
			return this;
		}

		@Override
		public String toString() {
			return value ? "true" : "false";
		}
	};

	private static Term FALSE = new Boolean(false);
	private static Term TRUE = new Boolean(true);

	private static Term NOT = new Term() {
		@Override
		protected Term apply(Term argument) {
			if (argument instanceof Boolean)
				return ((Boolean) argument).value ? FALSE : TRUE;
			else
				return new Application(this, argument);
		}

		@Override
		protected <TARGET> TARGET lift(Runtime<TARGET> runtime,
				Context<TARGET> context) {
			return runtime.literal("not");
		}

		@Override
		protected Term substitute(Variable variable, Term expression) {
			return this;
		}

		@Override
		public String toString() {
			return "not";
		}
	};

	private static class Literal extends Term {
		final String name;

		Literal(String name) {
			this.name = name;
		}

		@Override
		protected <TARGET> TARGET lift(Runtime<TARGET> runtime,
				Context<TARGET> context) {
			return runtime.literal(name);
		}

		@Override
		protected Term substitute(Variable variable, Term expression) {
			return this;
		}
		
		@Override
		public String toString() {
			return name.toUpperCase();
		}
	}

	@Override
	public Term literal(String name) {
		if (name.equals("true"))
			return TRUE;
		else if (name.equals("false"))
			return FALSE;
		else if (name.equals("not"))
			return NOT;
		else
			return new Literal(name);
	}

	@Override
	public <TARGET> TARGET lift(Runtime<TARGET> runtime, Term data) {
		return data.lift(runtime, null);
	}

	@Override
	public Term apply(Term function, Term argument) {
		return function.apply(argument);
	}

	private static class Variable extends Term {
		final String name;

		Variable(String name) {
			this.name = name;
		}

		@Override
		protected <TARGET> TARGET lift(Runtime<TARGET> target,
				Context<TARGET> context) {
			while (context != null) {
				if (context.variable == this)
					return context.value;

				context = context.parent;
			}
			throw new IllegalArgumentException("Unbound variable");
		}

		@Override
		protected Term substitute(Variable variable, Term expression) {
			return this == variable ? expression : this;
		}
		
		@Override
		public String toString() {
			return name;
		}
	}

	private static class Lambda extends Term {
		final Variable variable;
		final Term expression;

		Lambda(Variable variable, Term expression) {
			this.variable = variable;
			this.expression = expression;
		}

		@Override
		protected Term apply(Term argument) {
			// TODO: should we always do this?
			return expression.substitute(variable, argument);
		}

		@Override
		protected <TARGET> TARGET lift(Runtime<TARGET> runtime,
				Context<TARGET> context) {
			return runtime.closure(variable.name, new Closure2<TARGET>(context,
					variable, expression));
		}

		@Override
		protected Term substitute(Variable var, Term exp) {
			assert (var != variable);
			Term e = expression.substitute(var, exp);

			return new Lambda(variable, e);
		}
		
		@Override
		public String toString() {
			return "(fn " + variable + " = " + expression + ")";
		}
	}

	private static class Context<TARGET> {
		final Context<TARGET> parent;
		final Variable variable;
		final TARGET value;

		Context(Context<TARGET> parent, Variable variable, TARGET value) {
			this.parent = parent;
			this.variable = variable;
			this.value = value;
		}
	};

	private static class Closure2<TARGET> implements Closure<TARGET> {
		final Context<TARGET> context;
		final Variable variable;
		final Term expression;

		Closure2(Context<TARGET> context, Variable variable, Term expression) {
			this.context = context;
			this.variable = variable;
			this.expression = expression;
		}

		@Override
		public TARGET evaluate(Runtime<TARGET> runtime, TARGET argument) {
			return expression.lift(runtime, new Context<TARGET>(context,
					variable, argument));
		}
	};

	@Override
	public Term closure(String var, Closure<Term> closure) {
		Variable variable = new Variable(var);
		Term expression = closure.evaluate(this, variable);
		return new Lambda(variable, expression);
	}

	@Override
	public Term conditional(Term test, Conditional<Term> conditional) {
		return null;
	}
}
