import UserInteface.OpenController;
import UserInteface.OpenView;

public class Main {
    public static void main(String[] args) {
        OpenView openView = new OpenView();
        openView.setVisible(true);
        OpenController openController = new OpenController(openView);
    }
}
