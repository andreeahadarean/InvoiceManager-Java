package UserInteface;

import javax.swing.*;
import java.awt.event.ActionListener;

public class SelectView extends JFrame {
    private JPanel panel;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JButton doneButton;
    private JCheckBox predefinedCheckBox;
    private JCheckBox predefinedCheckBox1;
    private JCheckBox predefinedCheckBox2;
    private JCheckBox predefinedCheckBox3;

    public SelectView () {
        this.setSize(500, 500);
        this.add(panel);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void addButtonActionListener(ActionListener a) {
        doneButton.addActionListener(a);
    }

    public JTextField getTextField1() {
        return textField1;
    }

    public JTextField getTextField2() {
        return textField2;
    }

    public JTextField getTextField3() {
        return textField3;
    }

    public JTextField getTextField4() {
        return textField4;
    }

    public JCheckBox getPredefinedCheckBox() {
        return predefinedCheckBox;
    }

    public JCheckBox getPredefinedCheckBox1() {
        return predefinedCheckBox1;
    }

    public JCheckBox getPredefinedCheckBox2() {
        return predefinedCheckBox2;
    }

    public JCheckBox getPredefinedCheckBox3() {
        return predefinedCheckBox3;
    }
}
