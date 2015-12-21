/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.mmaroti.lexer;

public abstract class Symbol extends Token {
	public final String name;

	public Symbol(String name) {
		this.name = name;
	}
}
