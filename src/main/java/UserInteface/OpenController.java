package UserInteface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpenController {

    private OpenView openView;

    public OpenController(OpenView openView) {
        this.openView = openView;
        openView.addButtonActionListener(new OpenActionListener());
    }

    class OpenActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(openView.getGenerateNewDataCheckBox().isSelected()) {
                SelectView selectView = new SelectView();
                selectView.setVisible(true);
                SelectController selectController = new SelectController(selectView);
                openView.setVisible(false);
            } else {
                DecisionView decisionView = new DecisionView();
                decisionView.setVisible(true);
                DecisionController decisionController = new DecisionController(decisionView, null, null, null );
            }
            openView.setVisible(false);
        }
    }
}
