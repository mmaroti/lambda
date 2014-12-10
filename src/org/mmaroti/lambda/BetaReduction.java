/**
 *	Copyright (C) Miklos Maroti, 2014
 */

package org.mmaroti.lambda;

import org.mmaroti.lambda.Calculus.*;
import org.mmaroti.lambda.LambdaCalculus.*;

public class BetaReduction extends Context {
	public final BetaReduction parent;
	public final Variable variable;
	public final Term replacement;

	public BetaReduction(BetaReduction parent, Variable variable, Term data) {
		this.parent = parent;
		this.variable = variable;
		this.replacement = data;
	}

	public Data getValue(Variable var) {
		BetaReduction c = this;
		do {
			if (c.variable == var)
				return new Fragment(c.replacement);

			c = c.parent;
		} while (c != null);

		return new Fragment(var);
	}

	public Data closure(Variable var, Term expression) {
		BetaReduction context = new BetaReduction(this, var, var);
		expression = ((Fragment) expression.evaluate(context)).term;
		return new Fragment(new Abstraction(var, expression));
	}

	public String toString() {
		BetaReduction c = this;
		String s = ")";

		while (c != null) {
			s = " " + c.variable + "=" + c.replacement + s;
			c = c.parent;
		}

		return "(beta " + s;
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
			return "(frag " + term.toString() + ")";
		}

		public Data call(Data argument) {
			Term arg = ((Fragment) argument).term;
			return new Fragment(new Application(term, arg));
		}
	}

	public static Term evaluate(Term term, Variable var, Term replacement) {
		BetaReduction beta = new BetaReduction(null, var, replacement);
		Data data = term.evaluate(beta);
		return ((Fragment) data).term;
	}
}
