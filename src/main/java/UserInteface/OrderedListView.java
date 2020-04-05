package UserInteface;

import javax.swing.*;

public class OrderedListView extends JFrame {
    private JPanel panel;
    private JTextArea textArea1;

    public OrderedListView () {
        this.setSize(500, 500);
        this.add(panel);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
    }
}
