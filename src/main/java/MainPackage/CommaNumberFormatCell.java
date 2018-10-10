package MainPackage;

import javafx.scene.control.TableCell;

import java.text.DecimalFormat;

public class CommaNumberFormatCell extends TableCell<Subscription, Number> {
    public CommaNumberFormatCell () {}

    @Override protected void updateItem(Number item, boolean empty) {
        super.updateItem(item, empty);

        DecimalFormat formatter = new DecimalFormat("#,###");

        setText(item == null ? "" : formatter.format(item));
    }

}
