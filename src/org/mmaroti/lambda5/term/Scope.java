/**
 *	Copyright (C) Miklos Maroti, 2016
 */

package org.mmaroti.lambda5.term;

public class Scope {
	public final String name;
	public final Scope parent;

	public Scope(String name, Scope parent) {
		this.name = name;
		this.parent = parent;
	}

	public String lookup(int index) {
		Scope c = this;
		while (index-- > 0) {
			c = c.parent;
			assert c != null;
		}

		return c.name;
	}

	public static int getExtent(Scope scope) {
		int d = 0;
		while (scope != null) {
			d++;
			scope = scope.parent;
		}

		return d;
	}
}
