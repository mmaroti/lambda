/**
 *	Copyright (C) Miklos Maroti, 2014
 */

package org.mmaroti.lambda1;

import org.mmaroti.lambda1.Calculus.*;
import org.mmaroti.lambda1.LambdaCalculus.*;

public class WriterContext extends Context {
	@Override
	public Data getValue(Variable var) {
		return new Fragment(var);
	}

	@Override
	public Data closure(Variable variable, Term expression) {
		expression = ((Fragment) expression.evaluate(this)).term;
		return new Fragment(new Abstraction(variable, expression));
	}

	@Override
	public String toString() {
		return "folding";
	}

	@Override
	public Data localize(Literal literal) {
		return new Fragment(literal);
	}

	public static class Fragment extends Data {
		public final Term term;

		public Fragment(Term term) {
			this.term = term;
		}

		@Override
		public String toString() {
			return term.toString();
		}

		@Override
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
