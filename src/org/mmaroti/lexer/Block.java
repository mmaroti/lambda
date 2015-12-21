/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.mmaroti.lexer;

import java.util.*;

public abstract class Block extends Token {
	public final String type;
	public final List<Token> list;

	public Block(String type) {
		this.type = type;
		list = new ArrayList<Token>();
	}
}
