package UserInteface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpenController {
    private OpenView openView;
    private SelectView selectView = new SelectView();

    public OpenController(OpenView openView) {
        this.openView = openView;
        openView.addButtonActionListener(new OpenActionListener());
    }

    class OpenActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(openView.getGenerateNewDataCheckBox().isSelected()) {
                selectView.setVisible(true);
                SelectController selectController = new SelectController(selectView);
                openView.setVisible(false);
            }
        }
    }
}
