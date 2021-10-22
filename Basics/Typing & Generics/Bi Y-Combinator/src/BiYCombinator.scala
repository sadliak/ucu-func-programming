object BiYCombinator {

	def Y[A, B](f: (A => B) => A => B): A => B = f(Y(f))

	def fix[In1, In2, Out](biFnFactory: ((In1, In2) => Out) => (In1, In2) => Out): (In1, In2) => Out =
		(x: In1, y: In2) => biFnFactory(fix(biFnFactory))(x, y)
}