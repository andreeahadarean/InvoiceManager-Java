package UserInteface;

import javax.swing.*;
import java.awt.event.ActionListener;

public class OpenView extends JFrame{
    private JPanel panel;
    private JCheckBox usePersistedDataCheckBox;
    private JCheckBox generateNewDataCheckBox;
    private JButton startButton;

    public OpenView () {
        this.setSize(350, 350);
        this.add(panel);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void addButtonActionListener(ActionListener a) {
        startButton.addActionListener(a);
    }

    public JPanel getPanel() {
        return panel;
    }

    public JCheckBox getUsePersistedDataCheckBox() {
        return usePersistedDataCheckBox;
    }

    public JCheckBox getGenerateNewDataCheckBox() {
        return generateNewDataCheckBox;
    }

    public JButton getStartButton() {
        return startButton;
    }
}
