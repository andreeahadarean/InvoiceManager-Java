package UserInteface;

import DomainLogic.*;
import Model.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DecisionController {

    private DecisionView decisionView;
    private List<Invoice> invoiceList;
    private List<Company> companyList;
    private List<Product> productList;

    public DecisionController(DecisionView decisionView, List<Invoice> invoiceList, List<Company> companyList, List<Product> productList) {
        this.decisionView = decisionView;
        this.invoiceList = invoiceList;
        this.companyList = companyList;
        this.productList = productList;
        decisionView.addButtonActionListener(new DecisionActionListener());
    }

    class DecisionActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(decisionView.getViewOrderedInvoiceListCheckBox().isSelected()) {
                OrderedListView orderedListView = new OrderedListView();
                orderedListView.setVisible(true);
                if(invoiceList == null) {
                    InvoiceTableModule invoiceTableModule = new InvoiceTableModule();
                    List<Invoice> invoices = invoiceTableModule.orderInvoices(true);
                    for(Invoice invoice : invoices) {
                        orderedListView.getTextArea1().append(invoice.displayInvoice() + "\n");
                    }
                } else {
                    InvoiceTableModule invoiceTableModule = new InvoiceTableModule(invoiceList);
                    List<Invoice> invoices = invoiceTableModule.orderInvoices(false);
                    for(Invoice invoice : invoices) {
                        orderedListView.getTextArea1().append(invoice.displayInvoice() + "\n");
                    }
                }
            }
            if(decisionView.getDuplicateAnInvoiceCheckBox().isSelected()) {
                DuplicateView duplicateView = new DuplicateView();
                duplicateView.setVisible(true);
                DuplicateController duplicateController = new DuplicateController(duplicateView, invoiceList);
            }
            if(decisionView.getMarkAnInvoiceAsCheckBox().isSelected()) {
                PayView payView = new PayView();
                payView.setVisible(true);
                PayController payController = new PayController(payView, invoiceList);
            }
            if(decisionView.getSearchListByTextCheckBox().isSelected()) {
                SearchView searchView = new SearchView();
                searchView.setVisible(true);
                SearchController searchController = new SearchController(searchView, invoiceList);
            }
            if(decisionView.getPersistCurrentDataCheckBox().isSelected()) {
                if(invoiceList == null) {
                    PersistedView persistedView = new PersistedView();
                    persistedView.setVisible(true);
                    persistedView.getTextArea1().append("The data is already in the database!");
                } else {
                    PersistedView persistedView = new PersistedView();
                    persistedView.setVisible(true);
                    InvoiceTableModule invoiceTableModule = new InvoiceTableModule(invoiceList);
                    CompanyTableModule companyTableModule = new CompanyTableModule(companyList);
                    ProductTableModule productTableModule = new ProductTableModule(productList);
                    invoiceTableModule.persistData();
                    companyTableModule.persistData();
                    productTableModule.persistData();
                    for(Invoice i : invoiceList) {
                        persistedView.getTextArea1().append(i.displayInvoice() + "\n");
                    }
                }
            }
        }
    }
}
