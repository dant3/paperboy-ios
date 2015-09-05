package com.dant3.paperboy.util

import org.robovm.apple.foundation.NSIndexPath
import org.robovm.apple.uikit.{UITableViewCell, UITableView, UITableViewDataSourceAdapter}
import rx.lang.scala.RxVar

class RxSeqDataSource[T, U <: UITableViewCell](cellBinder: CellBinder[T, U]) extends UITableViewDataSourceAdapter {
  private val data = RxVar(Seq.empty[T])

  private val cellIdentifier = "default"

  override def getNumberOfRowsInSection(view: UITableView, section: Long) = data.get.size
  override def getCellForRow(tableView: UITableView, indexPath: NSIndexPath): UITableViewCell = {
    val existingCell = Option(tableView.dequeueReusableCell(cellIdentifier))
    val cell = existingCell.getOrElse(cellBinder.create).asInstanceOf[U]

    val item = data.get(indexPath.getRow)
    cellBinder.bind(item, cell)
  }

  def update(data: Seq[T]) = {
    this.data := data
  }
  def observe() = data.map(_ ⇒ this)
}
