/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.mmaroti.lambda6;

import java.util.*;

public class Lambda extends Expression {
	public final String name;
	public final Expression body;

	public Lambda(String name, Expression body) {
		assert Variable.isValid(name) && body != null;

		this.name = name;
		this.body = body;
	}

	@Override
	public int precedence() {
		return 2;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Lambda) {
			Lambda lam = (Lambda) obj;
			return name.equals(lam.name) && body.equals(lam.body);
		} else
			return false;
	}

	@Override
	public String toString() {
		String ret = "\\";

		Lambda lam = this;
		for (;;) {
			ret += lam.name;
			ret += " ";

			if (lam.body instanceof Lambda)
				lam = (Lambda) lam.body;
			else
				break;
		}

		ret += "-> ";

		boolean par = lam.body.precedence() > precedence();
		if (par)
			ret += "(";
		ret += lam.body;
		if (par)
			ret += ")";

		return ret;
	}

	@Override
	public void collect(Set<String> vars) {
		boolean b = vars.contains(name);
		body.collect(vars);
		if (!b)
			vars.remove(name);
	}

	@Override
	public Expression substitute(String var, Expression term) {
		if (!name.equals(var)) {
			if (term.contains(name))
				throw new IllegalArgumentException("captured variable");

			Expression b = body.substitute(var, term);
			if (b != body)
				return new Lambda(name, b);
		}
		return this;
	}

	@Override
	public boolean contains(String var) {
		if (name.equals(var))
			return false;
		else
			return body.contains(var);
	}

	@Override
	public Expression simplify() {
		Expression b = body.simplify();
		if (b == body)
			return this;
		else
			return new Lambda(name, b);
	}
}
