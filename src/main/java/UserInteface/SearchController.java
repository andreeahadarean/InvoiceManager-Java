package UserInteface;

import DomainLogic.InvoiceTableModule;
import Model.Invoice;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SearchController {

    private SearchView searchView;
    private List<Invoice> invoiceList;

    public SearchController(SearchView searchView, List<Invoice> invoiceList) {
        this.searchView = searchView;
        this.invoiceList = invoiceList;
        searchView.addButtonActionListener(new SearchActionListener());
    }

    class SearchActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(invoiceList == null) {
                InvoiceTableModule invoiceTableModule = new InvoiceTableModule();
                String text = searchView.getTextField1().getText();
                List<String> found = invoiceTableModule.testSearch(true, text);
                for(String s : found) {
                    searchView.getTextArea1().append(s);
                }
            } else {
                InvoiceTableModule invoiceTableModule = new InvoiceTableModule(invoiceList);
                String text = searchView.getTextField1().getText();
                List<String> found = invoiceTableModule.testSearch(false, text);
                for(String s : found) {
                    searchView.getTextArea1().append(s);
                }
            }
        }
    }
}
