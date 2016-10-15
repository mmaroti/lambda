/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.mmaroti.lambda7;

import java.util.*;

public class Variable extends Expression {
	public final String name;

	public static boolean isValid(String name) {
		return name != null && !name.isEmpty();
	}

	public Variable(String name) {
		assert isValid(name);

		this.name = name;
	}

	@Override
	public int precedence() {
		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Variable) {
			Variable var = (Variable) obj;
			return name.equals(var.name);
		} else
			return false;
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public void collect(Set<String> vars) {
		vars.add(name);
	}

	@Override
	public Expression substitute(String var, Expression term) {
		if (name.equals(var))
			return term;
		else
			return this;
	}

	@Override
	public boolean contains(String var) {
		return name.equals(var);
	}
}
