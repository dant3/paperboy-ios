import org.robovm.apple.uikit._

import scala.collection.JavaConversions._

trait MyViewControllerUi extends UIImplicits {
  lazy val layoutConstraints = NSLayoutConstraint.create(
    "V:[textField]-10-[label]",
    NSLayoutFormatOptions.None,
    null,
    Map(
      "textField" → textField,
      "label" → label
    ))

  lazy val background = new UIImageView(UIImage.create("Background.png")) {
    setFrame((0, 0, 320, 480))
    setContentMode(UIViewContentMode.Center)
    setUserInteractionEnabled(false)
  }

  lazy val textField = new UITextField((44, 32, 232, 31)) {
    setContentVerticalAlignment(UIControlContentVerticalAlignment.Center)
    setBorderStyle(UITextBorderStyle.RoundedRect)
    setFont(UIFont.getFont("Helvetica", 17))
    setClearsOnBeginEditing(true)
    setAdjustsFontSizeToFitWidth(true)
    setMinimumFontSize(17)
    setAutocapitalizationType(UITextAutocapitalizationType.Words)
    setKeyboardType(UIKeyboardType.ASCIICapable)
    setReturnKeyType(UIReturnKeyType.Done)
    setText("Enter text!")
    setDelegate(new UITextFieldDelegateAdapter() {
      override def shouldReturn(theTextField:UITextField): Boolean = {
        resignFirstResponder()
        true
      }
    })
  }

  lazy val label = new UILabel((20, 104, 280, 44)) {
    setFont(UIFont.getFont("Helvetica", 24))
    setTextColor(UIColor.white())
    setBaselineAdjustment(UIBaselineAdjustment.AlignCenters)
    setTextAlignment(NSTextAlignment.Center)
  }
}
