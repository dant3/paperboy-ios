package rx.lang.scala

import java.util.concurrent.CopyOnWriteArraySet
import java.util.concurrent.atomic.AtomicReference

import scala.collection.JavaConverters._

final class RxVar[T] private(private val currentValue:AtomicReference[T]) extends RxVal[T] {
  import ImplicitFunctionConversions._

  private def this(startValue:T) = this(new AtomicReference[T](startValue))

  override def get = currentValue.get()

  def update(newValue:T):Unit = {
    val oldValue = currentValue.getAndSet(newValue)
    notifySubscribers(oldValue, newValue)
  }
  def := (newValue:T):Unit = update(newValue)


  private val subscribers = new CopyOnWriteArraySet[Subscriber[T]].asScala
  override private[scala] val asJavaObservable = rx.Observable.create { subscriber: Subscriber[T] ⇒
    subscribers.retain(!_.isUnsubscribed)
    if (subscribers.add(subscriber)) {
      subscriber.onStart()
      subscriber.onNext(get)
    }
  }

  private def notifySubscribers(oldValue:T, newValue:T) = {
    subscribers.retain(!_.isUnsubscribed)
    subscribers.view.filter(!_.isUnsubscribed).foreach(_.onNext(newValue))
  }
}

object RxVar {
  def apply[T](initialValue:T):RxVar[T] = new RxVar[T](initialValue)
}
