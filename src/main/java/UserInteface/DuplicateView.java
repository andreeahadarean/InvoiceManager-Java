package UserInteface;

import javax.swing.*;
import java.awt.event.ActionListener;

public class DuplicateView extends JFrame {
    private JPanel panel;
    private JTextField textField1;
    private JButton button1;
    private JTextArea textArea1;

    public DuplicateView () {
        this.setSize(1000, 500);
        this.add(panel);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
    }

    public void addButtonActionListener(ActionListener a) {
        button1.addActionListener(a);
    }

    public JTextField getTextField1() {
        return textField1;
    }

    public JTextArea getTextArea1() {
        return textArea1;
    }
}
