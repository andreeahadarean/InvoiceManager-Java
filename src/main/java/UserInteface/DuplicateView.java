package UserInteface;

import javax.swing.*;
import java.awt.event.ActionListener;

public class DuplicateView extends JFrame {
    private JPanel panel;
    private JTextField textField1;
    private JButton button1;
    private JTextArea textArea1;

    public DuplicateView () {
        this.setSize(500, 500);
        this.add(panel);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
    }

    public void addButtonActionListener(ActionListener a) {
        button1.addActionListener(a);
    }
}
