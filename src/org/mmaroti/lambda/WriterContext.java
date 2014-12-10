/**
 *	Copyright (C) Miklos Maroti, 2014
 */

package org.mmaroti.lambda;

import org.mmaroti.lambda.Calculus.*;
import org.mmaroti.lambda.LambdaCalculus.*;

public class WriterContext extends Context {
	public Data getValue(Variable var) {
		return new Fragment(var);
	}

	public Data closure(Variable variable, Term expression) {
		expression = ((Fragment) expression.evaluate(this)).term;
		return new Fragment(new Abstraction(variable, expression));
	}

	public String toString() {
		return "folding";
	}

	public Data localize(Literal literal) {
		return new Fragment(literal);
	}

	public static class Fragment extends Data {
		public final Term term;

		public Fragment(Term term) {
			this.term = term;
		}

		public String toString() {
			return term.toString();
		}

		public Data call(Data argument) {
			Term arg = ((Fragment) argument).term;
			return new Fragment(new Application(term, arg));
		}
	}

	public static final WriterContext INSTANCE = new WriterContext();

	public static Term evaluate(Term term) {
		Data data = term.evaluate(INSTANCE);
		return ((Fragment) data).term;
	}
}
