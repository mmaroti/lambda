/**
 *	Copyright (C) Miklos Maroti, 2014
 */

package org.mmaroti.lambda1;

import org.mmaroti.lambda1.Calculus.*;

public class RuntimeContext extends Context {
	public RuntimeContext() {
	}

	public Data getValue(Variable var) {
		throw new IllegalArgumentException();
	}

	public Data closure(Variable variable, Term expression) {
		return new Closure(this, variable, expression);
	}

	public Data localize(Literal literal) {
		return literal.data;
	}

	public String toString() {
		return "runtime";
	}

	public RuntimeContext push(Variable variable, Data value) {
		return new Frame(this, variable, value);
	}

	public static class Frame extends RuntimeContext {
		public final RuntimeContext parent;
		public final Variable variable;
		public final Data value;

		public Frame(RuntimeContext parent, Variable variable, Data value) {
			this.parent = parent;
			this.variable = variable;
			this.value = value;
		}

		public Data getValue(Variable var) {
			if (var == variable)
				return value;
			else
				return parent.getValue(var);
		}

		public String toString() {
			Context c = this;
			String s = ")";

			for (;;) {
				if (c instanceof Frame) {
					Frame f = (Frame) c;
					s = " " + f.variable + "=" + f.value + s;
					c = f.parent;
				} else {
					s = c.toString() + s;
					break;
				}
			}

			return "(" + s;
		}

		public Data closure(Variable variable, Term expression) {
			return new Closure(this, variable, expression);
		}
	}

	public static class Closure extends Data {
		public final RuntimeContext context;
		public final Variable variable;
		public final Term expression;

		public Closure(RuntimeContext context, Variable variable,
				Term expression) {
			this.context = context;
			this.variable = variable;
			this.expression = expression;
		}

		public Data call(Data argument) {
			return expression.evaluate(context.push(variable, argument));
		}

		public String toString() {
			return "(closure (" + context + ") " + variable + " " + expression
					+ ")";
		}
	}

	public static class Number extends Data {
		public final int value;

		public Number(int value) {
			this.value = value;
		}

		public String toString() {
			return Integer.toString(value);
		}
	}

	public static final Data ADD = new Data() {
		public Data call(Data arg1) {
			if (!(arg1 instanceof Number))
				throw new IllegalArgumentException();

			final int val1 = ((Number) arg1).value;

			return new Data() {
				public Data call(Data arg2) {
					if (!(arg2 instanceof Number))
						throw new IllegalArgumentException();

					final int val2 = ((Number) arg2).value;
					return new Number(val1 + val2);
				}

				public String toString() {
					return "ADD" + val1;
				}
			};
		}

		public String toString() {
			return "ADD";
		}
	};

	public static final RuntimeContext INSTANCE = new RuntimeContext();

	public static Data evaluate(Term term) {
		return term.evaluate(INSTANCE);
	}
}
