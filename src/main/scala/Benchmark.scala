import scala.concurrent.duration._

object Benchmark {
  def apply(block: ⇒ Any) = {
    val measures = 0 to 100 map (_ ⇒ measure(block))
    s"""*** Benchmark finished ***
       |Worst result: ${measures.max}
       |Best result: ${measures.min}
       |Mean: ${measures.reduce(_ + _) / measures.length}
       |""".stripMargin
  }

  def measure(block: ⇒ Any):Duration = {
    val startTime = System.nanoTime()
    block
    val endTime = System.nanoTime()
    (endTime - startTime).nanoseconds
  }
}
