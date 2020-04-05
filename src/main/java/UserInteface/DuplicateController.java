package UserInteface;

import DomainLogic.InvoiceTableModule;
import Model.Invoice;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DuplicateController {

    private DuplicateView duplicateView;
    private List<Invoice> invoiceList;

    public DuplicateController(DuplicateView duplicateView, List<Invoice> invoiceList) {
        this.duplicateView = duplicateView;
        this.invoiceList = invoiceList;
        duplicateView.addButtonActionListener(new DuplicateActionListener());
    }

    class DuplicateActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(invoiceList == null){
                InvoiceTableModule invoiceTableModule = new InvoiceTableModule();
                int id = Integer.parseInt(duplicateView.getTextField1().getText());
                Invoice invoice = invoiceTableModule.getCertainInvoice(id);
                invoiceTableModule.addDuplicateInvoice(true, invoice);
                List<Invoice> invoices = invoiceTableModule.getPersistentInvoices();
                for(Invoice i : invoices) {
                    duplicateView.getTextArea1().append(i.displayInvoice() + "\n");
                }
            } else {
                InvoiceTableModule invoiceTableModule = new InvoiceTableModule(invoiceList);
                int id = Integer.parseInt(duplicateView.getTextField1().getText());
                for(Invoice i:invoiceList) {
                    if(i.getId() == id) {
                        Invoice invoice = new Invoice(i.getId(), i.getSeller(), i.getProducts(), i.getTotal(), i.getDueDate(), i.getPayDate(), i.isDuplicate(), i.getRemainingDays());
                        invoiceTableModule.addDuplicateInvoice(false, invoice);
                        break;
                    }
                }
                for(Invoice i : invoiceList) {
                    duplicateView.getTextArea1().append(i.displayInvoice() + "\n");
                }
            }
        }
    }
}
