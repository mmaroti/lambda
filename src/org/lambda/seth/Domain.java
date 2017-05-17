/**
 * Copyright (C) Miklos Maroti, 2016-2017
 */

package org.lambda.seth;

public abstract class Domain extends Data {
	/**
	 * @return if the domain has few elements (no powers)
	 */
	public abstract boolean isSmall();

	/**
	 * @return the size of the small closed domain
	 * @throws InvalidStateException
	 *             if the domain it not small or not closed
	 */
	public abstract int getSize();

	/**
	 * @return true if the two domains are structurally the same and the domain
	 *         variables are named the same
	 */
	@Override
	public abstract boolean equals(Object other);

	@Override
	public abstract String toString();

	public static class Product extends Domain {
		public final Domain[] factors;

		public Product(Domain... factors) {
			this.factors = factors;
		}

		@Override
		public boolean isSmall() {
			for (Domain factor : factors)
				if (!factor.isSmall())
					return false;
			return true;
		}

		@Override
		public int getSize() {
			int s = 1;
			for (Domain factor : factors)
				s *= factor.getSize();
			return s;
		}

		@Override
		public boolean equals(Object other) {
			if (other instanceof Product) {
				Domain[] f = ((Product) other).factors;
				if (factors.length == f.length) {
					for (int i = 0; i < factors.length; i++)
						if (!factors[i].equals(f[i]))
							return false;
					return true;
				}
			}
			return false;
		}

		@Override
		public String toString() {
			String s = "prod [";
			for (int i = 0; i < factors.length; i++) {
				if (i > 0)
					s += ", ";
				s += factors[i].toString();
			}
			s += "]";
			return s;
		}
	}

	public static class Union extends Domain {
		public final Domain[] factors;

		public Union(Domain... factors) {
			this.factors = factors;
		}

		@Override
		public boolean isSmall() {
			for (Domain factor : factors)
				if (!factor.isSmall())
					return false;
			return true;
		}

		@Override
		public int getSize() {
			int s = 0;
			for (Domain factor : factors)
				s += factor.getSize();
			return s;
		}

		@Override
		public boolean equals(Object other) {
			if (other instanceof Union) {
				Domain[] f = ((Union) other).factors;
				if (factors.length == f.length) {
					for (int i = 0; i < factors.length; i++)
						if (!factors[i].equals(f[i]))
							return false;
					return true;
				}
			}
			return false;
		}

		@Override
		public String toString() {
			String s = "union [";
			for (int i = 0; i < factors.length; i++) {
				if (i > 0)
					s += ", ";
				s += factors[i].toString();
			}
			s += "]";
			return s;
		}
	}

	public static class Arrow extends Domain {
		public final Domain argument;
		public final Domain result;

		public Arrow(Domain argument, Domain result) {
			this.argument = argument;
			this.result = result;
		}

		@Override
		public boolean isSmall() {
			return false;
		}

		@Override
		public int getSize() {
			throw new IllegalStateException();
		}

		@Override
		public boolean equals(Object other) {
			if (other instanceof Arrow) {
				Arrow o = (Arrow) other;
				return argument.equals(o.argument) && result.equals(o.result);
			} else
				return false;
		}

		@Override
		public String toString() {
			return "arrow " + argument.toString() + " " + result.toString();
		}
	}
}
