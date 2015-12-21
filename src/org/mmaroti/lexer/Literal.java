/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.mmaroti.lexer;

public abstract class Literal extends Token {
	public final Object value;

	public Literal(Object value) {
		this.value = value;
	}
}
