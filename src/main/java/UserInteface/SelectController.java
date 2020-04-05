package UserInteface;

import Generators.CompanyGenerator;
import Generators.InvoiceGenerator;
import Generators.ProductGenerator;
import Model.Company;
import Model.Invoice;
import Model.Product;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class SelectController {

    private SelectView selectView;

    public SelectController(SelectView selectView) {
        this.selectView = selectView;
        selectView.addButtonActionListener(new SelectActionListener());
    }

    class SelectActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            List<Company> companyList = new ArrayList<Company>();
            List<Product> productList = new ArrayList<Product>();
            List<Invoice> invoiceList = new ArrayList<Invoice>();
            CompanyGenerator companyGenerator = new CompanyGenerator();
            ProductGenerator productGenerator = new ProductGenerator();
            InvoiceGenerator invoiceGenerator = new InvoiceGenerator();
            if(selectView.getPredefinedCheckBox().isSelected()) {
                if(selectView.getPredefinedCheckBox1().isSelected()) {
                    companyList = companyGenerator.generateCompanies(24, null);
                } else {
                    int nrOfCompanies = Integer.parseInt(selectView.getTextField2().getText());
                    companyList = companyGenerator.generateCompanies(nrOfCompanies, null);
                }
                if(selectView.getPredefinedCheckBox3().isSelected()) {
                    invoiceList = invoiceGenerator.generateInvoice(50, null);
                } else if (!selectView.getPredefinedCheckBox3().isSelected()) {
                    int nrOfInvoices = Integer.parseInt(selectView.getTextField4().getText());
                    invoiceList = invoiceGenerator.generateInvoice(nrOfInvoices, null);
                }
            } else if (!selectView.getPredefinedCheckBox().isSelected()){
                String input = selectView.getTextField1().getText();
                String[] names = input.split(", ");
                if(selectView.getPredefinedCheckBox1().isSelected()) {
                    companyList = companyGenerator.generateCompanies(24, names);
                } else {
                    int nrOfCompanies = Integer.parseInt(selectView.getTextField2().getText());
                    companyList = companyGenerator.generateCompanies(nrOfCompanies, names);
                }
                if(selectView.getPredefinedCheckBox3().isSelected()) {
                    invoiceList = invoiceGenerator.generateInvoice(50, names);
                } else if (!selectView.getPredefinedCheckBox3().isSelected()) {
                    int nrOfInvoices = Integer.parseInt(selectView.getTextField4().getText());
                    invoiceList = invoiceGenerator.generateInvoice(nrOfInvoices, names);
                }
            }
            if(selectView.getPredefinedCheckBox2().isSelected()) {
                productList = productGenerator.generateProducts(48);
            } else if(!selectView.getPredefinedCheckBox2().isSelected()) {
                int nrOfProducts = Integer.parseInt(selectView.getTextField3().getText());
                productList = productGenerator.generateProducts(nrOfProducts);
            }
            DecisionView decisionView = new DecisionView();
            decisionView.setVisible(true);
            DecisionController decisionController = new DecisionController(decisionView, invoiceList, companyList, productList);
            selectView.setVisible(false);
        }
    }

}
