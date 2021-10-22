object VectorTask {

  trait Vector[In] {

    /**
     * Get value of the vector at specific position
     *
     * @param position of vector value. Must be less than `size`
     * @return value at the given position */
    def apply(position: Int): Float

    /**
     * Create a new Vector instance with the given value and previous values
     *
     * @param position of vector value. Must be less than `size`
     * @param value    the value to set
     * @return new instance of this vector */
    def modify(position: Int, value: Float): Vector[In]

    /**
     * Provides the size of this vector
     *
     * @return actual size of the vector */
    def size(): Int
  }

  def sum[In](vector1: Vector[In], vector2: Vector[In]): Vector[In] = {
    if (vector1.size() != vector2.size()) {
      throw new RuntimeException("Vector size mismatch")
    }

    Range(0, vector1.size(), 1).foldLeft(vector1)((v, idx) => v.modify(idx, v(idx) + vector2(idx)))
  }

  def sub[In](vector1: Vector[In], vector2: Vector[In]): Vector[In] = {
    if (vector1.size() != vector2.size()) {
      throw new RuntimeException("Vector size mismatch")
    }

    Range(0, vector1.size(), 1).foldLeft(vector1)((v, idx) => v.modify(idx, v(idx) - vector2(idx)))
  }
}