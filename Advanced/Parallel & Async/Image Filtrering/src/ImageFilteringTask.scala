import java.util.concurrent.RecursiveTask

object ImageFilteringTask {
  type ImageData = Array[Array[Int]]
  type FilterFunc = (Int, Int) => Int

  def filterImage(filter: ImageData => FilterFunc, windowSize: Int)(imageData: ImageData): ImageData = {
    Task(filter(imageData), windowSize, 0, imageData.length, 0, imageData(0).length).compute()
  }

  // Fork-join recursive task.
  case class Task(filter: FilterFunc, windowSize: Int, rowStart: Int, rowEnd: Int, colStart: Int, colEnd: Int) extends RecursiveTask[ImageData] {
    override def compute(): ImageData = {
      val rowCount = rowEnd - rowStart
      val colCount = colEnd - colStart

      if (colCount <= windowSize && rowCount <= windowSize) {
        Array.tabulate(rowCount, colCount)((rowIdx, colIdx) => filter(rowStart + rowIdx, colStart + colIdx))
      } else if (rowCount > windowSize) {
        val partSize = rowCount / 2

        val task1 = Task(filter, windowSize, rowStart, rowStart + partSize, colStart, colEnd)
        val task2 = Task(filter, windowSize, rowStart + partSize, rowEnd, colStart, colEnd)

        task2.fork()

        Array.concat(
          task1.compute(),
          task2.join()
        )
      } else {
        val partSize = colCount / 2

        val task1 = Task(filter, windowSize, rowStart, rowEnd, colStart, colStart + partSize)
        val task2 = Task(filter, windowSize, rowStart, rowEnd, colStart + partSize, colEnd)

        task2.fork()

        val task1Result = task1.compute()
        val task2Result = task2.join()

        for (i <- 0 until rowCount) {
          task1Result.update(i, Array.concat(task1Result(i), task2Result(i)))
        }

        task1Result
      }
    }
  }
}