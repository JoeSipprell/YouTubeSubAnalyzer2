package MainPackage;

import javafx.scene.control.TableCell;

import java.text.DecimalFormat;

public class MoneyFormatCell extends TableCell<Subscription, Number> {
    public MoneyFormatCell() {    }

    @Override protected void updateItem(Number item, boolean empty) {
        // calling super here is very important - don't skip this!
        super.updateItem(item, empty);

        DecimalFormat formatter = new DecimalFormat("$#,###");

        // format the number as if it were a monetary value using the
        // formatting relevant to the current locale. This would format
        // 43.68 as "$43.68", and -23.67 as "-$23.67"
        setText(item == null ? "" : formatter.format(item));

        // change the text fill based on whether it is positive (green)
        // or negative (red). If the cell is selected, the text will
        // always be white (so that it can be read against the blue
        // background), and if the value is zero, we'll make it black.
        /*if (item != null) {
            double value = item.doubleValue();
            setTextFill(isSelected() ? Color.WHITE :
                    value == 0 ? Color.BLACK :
                            value < 0 ? Color.RED : Color.GREEN);
        }*/
    }
}
