/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.haskell.datainst;

import org.haskell.data.*;
import org.haskell.typeclass.*;

public class FuncCallable<ARG, RET> extends Callable<Func<ARG, RET>, ARG, RET> {
	@Override
	public RET call(Func<ARG, RET> func, ARG arg) {
		return func.apply(arg);
	}
}
