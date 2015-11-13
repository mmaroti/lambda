/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.haskell.typeclass;

public class Elem<TYPE extends Type, VALUE> {
	public final TYPE type;
	public final VALUE value;

	public Elem(TYPE type, VALUE value) {
		this.type = type;
		this.value = value;
	}
}
