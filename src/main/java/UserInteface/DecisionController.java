package UserInteface;

import Model.Company;
import Model.Invoice;
import Model.Product;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DecisionController {
    private DecisionView decisionView;
    private List<Company> companyList;
    private List<Product> productList;
    private List<Invoice> invoiceList;

    public DecisionController(DecisionView decisionView, List<Company> companyList, List<Product> productList, List<Invoice> invoiceList) {
        this.decisionView = decisionView;
        this.companyList = companyList;
        this.productList = productList;
        this.invoiceList = invoiceList;
        decisionView.addButtonActionListener(new DecisionActionListener());
    }

    class DecisionActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {

        }
    }
}
