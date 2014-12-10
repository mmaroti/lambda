/**
 *	Copyright (C) Miklos Maroti, 2014
 */

package org.mmaroti.lambda;

import org.mmaroti.lambda.Context.*;

public class LambdaCalculus extends Calculus {
	public static class Abstraction extends Term {
		public final Variable variable;
		public final Term expression;

		public Abstraction(Variable variable, Term expression) {
			this.variable = variable;
			this.expression = expression;
			
			variables.addAll(expression.variables);
			variables.remove(variable);
		}

		public String toString() {
			return "(fun " + variable + " " + expression + ")";
		}

		public Data evaluate(Context context) {
			return context.closure(variable, expression);
		}
	}

	public static class Application extends Term {
		public final Term function;
		public final Term argument;

		public Application(Term function, Term argument) {
			this.function = function;
			this.argument = argument;

			variables.addAll(function.variables);
			variables.addAll(argument.variables);
		}

		public String toString() {
			return "(call " + function + " " + argument + ")";
		}

		public Data evaluate(Context context) {
			Data fun = function.evaluate(context);
			Data arg = argument.evaluate(context);
			return fun.call(arg);
		}
	}
}
