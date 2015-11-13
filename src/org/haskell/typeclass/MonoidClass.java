/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.haskell.typeclass;

public interface MonoidClass<TYPE extends Type, VALUE> {
	Elem<TYPE, VALUE> unit();

	Elem<TYPE, VALUE> prod(Elem<TYPE, VALUE> a, Elem<TYPE, VALUE> b);
}
