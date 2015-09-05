package com.dant3.paperboy.util

import org.robovm.apple.uikit.UITableViewCell

trait CellBinder[T, U <: UITableViewCell] {
  def create:U
  def bind(data: T, view: U): U
}
