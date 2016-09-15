/**
 *	Copyright (C) Miklos Maroti, 2016
 */

package org.mmaroti.lambda5.term;

public class Scope {
	public final String variable;
	public final Scope parent;

	public Scope(String variable, Scope parent) {
		this.variable = variable;
		this.parent = parent;
	}

	public String lookup(int index) {
		Scope c = this;
		while (index-- > 0) {
			c = c.parent;
			assert c != null;
		}

		return c.variable;
	}
}
