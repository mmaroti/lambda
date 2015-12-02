{-# LANGUAGE MultiParamTypeClasses, FlexibleInstances, UndecidableInstances #-}

module Lambda where

import Prelude ()
import qualified Prelude

-- TYPE CLASSES

class Logic a where
	true :: a
	false :: a
	not :: a -> a
	and :: a -> a -> a
	or :: a -> a -> a

class Logic b => Equality a b where
	equ :: a -> a -> b
	neq :: a -> a -> b

class Ring a where
	zero :: a
	unit :: a
	negate :: a -> a
	plus :: a -> a -> a
	prod :: a -> a -> a

-- GENERIC INSTANCES

instance Logic a => Ring a where
	zero = false
	unit = true
	negate = Prelude.id
	plus = \x y -> or (and x (not y)) (and (not x) y)
	prod = and

-- HASKELL INSTANCES

instance Logic Prelude.Bool where
	true = Prelude.True
	false = Prelude.False
	not = Prelude.not
	and = (Prelude.&&)
	or = (Prelude.||)

instance Ring Prelude.Int where
	zero = 0
	unit = 1
	negate = (Prelude.negate)
	plus = (Prelude.+)
	prod = (Prelude.*)

instance Prelude.Eq a => Equality a Prelude.Bool where
	equ = (Prelude.==)
	neq = (Prelude./=)

-- DATA DECLARATIONS

data Bool
	= False
	| True
	deriving Prelude.Show

data List a
	= Cons a (List a)
	| Nill
	deriving Prelude.Show

-- DATA INSTANCES

instance Logic Bool where
	true = True
	false = False
	not = \x -> case x of
		False -> True
		True -> False
	and = \x -> case x of
		False -> \_ -> False
		True -> \y -> case y of
			False -> False
			True -> True
	or = \x -> case x of
		False -> \y -> case y of
			False -> False
			True -> True
		True -> \_ -> True
