package UserInteface;

import DomainLogic.InvoiceTableModule;
import Model.Invoice;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PayController {

    private PayView payView;
    private List<Invoice> invoiceList;

    public PayController(PayView payView, List<Invoice> invoiceList) {
        this.payView = payView;
        this.invoiceList = invoiceList;
        payView.addButtonActionListener(new PayActionListener());
    }

    class PayActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(invoiceList == null) {
                InvoiceTableModule invoiceTableModule = new InvoiceTableModule();
                int id = Integer.parseInt(payView.getTextField1().getText());
                invoiceTableModule.payInvoice(true, id);
                Invoice invoice = invoiceTableModule.getCertainInvoice(id);
                payView.getTextArea1().append(invoice.displayInvoice());
            } else {
                InvoiceTableModule invoiceTableModule = new InvoiceTableModule(invoiceList);
                int id = Integer.parseInt(payView.getTextField1().getText());
                invoiceTableModule.payInvoice(false, id);
                for(Invoice i : invoiceList) {
                    if(i.getId() == id) {
                        payView.getTextArea1().append(i.displayInvoice());
                        break;
                    }
                }
            }
        }
    }
}
