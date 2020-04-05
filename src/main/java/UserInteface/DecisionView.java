package UserInteface;

import javax.swing.*;
import java.awt.event.ActionListener;

public class DecisionView extends JFrame {
    private JPanel panel;
    private JCheckBox viewOrderedInvoiceListCheckBox;
    private JCheckBox duplicateAnInvoiceCheckBox;
    private JCheckBox searchListByTextCheckBox;
    private JCheckBox markAnInvoiceAsCheckBox;
    private JCheckBox persistCurrentDataCheckBox;
    private JButton doneButton;

    public DecisionView () {
        this.setSize(500, 500);
        this.add(panel);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void addButtonActionListener(ActionListener a) {
        doneButton.addActionListener(a);
    }

    public JCheckBox getViewOrderedInvoiceListCheckBox() {
        return viewOrderedInvoiceListCheckBox;
    }

    public JCheckBox getDuplicateAnInvoiceCheckBox() {
        return duplicateAnInvoiceCheckBox;
    }

    public JCheckBox getSearchListByTextCheckBox() {
        return searchListByTextCheckBox;
    }

    public JCheckBox getMarkAnInvoiceAsCheckBox() {
        return markAnInvoiceAsCheckBox;
    }

    public JCheckBox getPersistCurrentDataCheckBox() {
        return persistCurrentDataCheckBox;
    }
}
