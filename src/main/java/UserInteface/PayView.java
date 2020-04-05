package UserInteface;

import javax.swing.*;
import java.awt.event.ActionListener;

public class PayView extends JFrame {
    private JPanel panel;
    private JTextField textField1;
    private JButton payButton;
    private JTextArea textArea1;

    public PayView () {
        this.setSize(500, 500);
        this.add(panel);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
    }

    public void addButtonActionListener(ActionListener a) {
        payButton.addActionListener(a);
    }
}
