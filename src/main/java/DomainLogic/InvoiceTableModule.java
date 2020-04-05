package DomainLogic;

import DataSource.InvoiceGateway;
import Model.Invoice;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class InvoiceTableModule {

    private InvoiceGateway invoiceGateway = new InvoiceGateway();
    private List<Invoice> generatedInvoices;

    public InvoiceTableModule(List<Invoice> generatedInvoices) {
        this.generatedInvoices = generatedInvoices;
    }

    public InvoiceTableModule() {

    }

    public List<Invoice> getGeneratedInvoices() {
        return generatedInvoices;
    }

    public List<Invoice> getPersistentInvoices() {
        List<Invoice> invoices = invoiceGateway.selectAll();
        return invoices;
    }

    public Invoice getCertainInvoice(int id) {
        return invoiceGateway.findById(id);
    }

    public void addDuplicateInvoice(boolean persisted, Invoice invoice){
        if(persisted == true) {
            Invoice foundInvoice = invoiceGateway.findById(invoice.getId());
            if(foundInvoice.equals(invoice)) {
                invoiceGateway.insert(invoice.getId(), invoice.getSeller(), invoice.getProducts(), invoice.getTotal(), invoice.getDueDate(), invoice.getPayDate(), true, (int) invoice.getRemainingDays());
            }
        } else if(persisted == false) {
            for (Invoice i : generatedInvoices) {
                if (i.equals(invoice)) {
                    invoice.setDuplicate(true);
                    generatedInvoices.add(invoice);
                    break;
                }
            }
        }
    }

    public List<Invoice> orderInvoices(boolean persisted) {
        List<Invoice> invoices = new ArrayList<>();
        if(persisted == true) {
            invoices = invoiceGateway.selectAll();
        } else {
            invoices = generatedInvoices;
        }
        List<Invoice> paiedInvoices = new ArrayList<Invoice>();
        List<Invoice> unpaiedInvoices = new ArrayList<Invoice>();
        for(Invoice i : invoices) {
            if(i.getPayDate() == null) {
                Date date = new Date();
                long diffInMillis = Math.abs(i.getDueDate().getTime() - date.getTime());
                long diff = TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS);
                i.setRemainingDays(diff);
                unpaiedInvoices.add(i);
            } else
                paiedInvoices.add(i);
        }
        Collections.sort(unpaiedInvoices, new Comparator<Invoice>() {
            @Override
            public int compare(Invoice invoice, Invoice t1) {
                if(invoice.getDueDate() == null || t1.getDueDate() == null)
                    return 0;
                return invoice.getDueDate().compareTo(t1.getDueDate());
            }
        });
        Collections.sort(paiedInvoices, new Comparator<Invoice>() {
            @Override
            public int compare(Invoice invoice, Invoice t1) {
                if(invoice.getDueDate() == null || t1.getDueDate() == null)
                    return 0;
                return invoice.getPayDate().compareTo(t1.getPayDate());
            }
        });
        List<Invoice> orderedList = new ArrayList<>();
        orderedList.addAll(unpaiedInvoices);
        orderedList.addAll(paiedInvoices);
        return orderedList;
    }

    public void payInvoice(boolean persistent, int invoiceNumber) {
        if(persistent == true) {
            Invoice invoice = invoiceGateway.findById(invoiceNumber);
            if(invoice.getPayDate() == null) {
                invoiceGateway.update("\"payDate\"", "id", new Date(), invoiceNumber);
                invoiceGateway.update("\"remainingDays\"", "id", 0, invoiceNumber);
            }
        } else {
            for (Invoice i : generatedInvoices) {
                if (i.getId() == invoiceNumber) {
                    if (i.getPayDate() == null) {
                        Date date = new Date();
                        i.setPayDate(date);
                        i.setRemainingDays(0);
                        i.displayInvoice();
                    } else System.out.println("The invoice is paied!");
                }
            }
        }
    }

    public List<String> testSearch(boolean persistent, String text) {
        List<Invoice> invoices;
        if(persistent == true) {
            invoices = invoiceGateway.selectAll();
        } else {
            invoices = generatedInvoices;
        }
        List<Invoice> firstCompany = new ArrayList<>();
        List<Invoice> secondCompany = new ArrayList<>();
        List<Invoice> restCompany = new ArrayList<>();
        List<Invoice> products = new ArrayList<>();
        List<Invoice> filtered = new ArrayList<>();
        if(text.length() < 3) {
            System.out.println("The text should contain at leas 3 characters!");
        }
        if(text.equals("Romanian") || text.equals("Eurpean") || text.equals("Italian") || text.equals("Food") || text.equals("Electricity") || text.equals("Incorporated")
                || text.equals("Motion") || text.equals("Premium")) {
            for(Invoice i : invoices) {
                if(i.getSeller().contains(text)) {
                    String[] subStrings = i.getSeller().split(" ");
                    List<String> aux = Arrays.asList(subStrings);
                    int index = aux.indexOf(text);
                    if(index == 0) {
                        firstCompany.add(i);
                    } else if (index == 1) {
                        secondCompany.add(i);
                    } else {
                        restCompany.add(i);
                    }
                }
            }
        } else if(text.length() == 5) {
            for(Invoice i : invoices) {
                if(i.getProducts().contains(text)){
                    filtered.add(i);
                }
            }
        }
        filtered.addAll(firstCompany);
        filtered.addAll(secondCompany);
        filtered.addAll(restCompany);
        filtered.addAll(products);
        List<String> toBeDisplayed = new ArrayList<String>();
        for(int i = 0; i < 10 && i < filtered.size(); i++) {
            StringBuilder sb = new StringBuilder();
            sb.append("Seller " + filtered.get(i).getSeller());
            sb.append(" with due date " + filtered.get(i).getDueDate().toString());
            sb.append(filtered.get(i).getPayDate() != null ? " and pay date " + filtered.get(i).getPayDate().toString() : " ");
            sb.append(" with products ");
            sb.append(filtered.get(i).getProducts());
            sb.append("\n");
            toBeDisplayed.add(sb.toString());
        }
        return toBeDisplayed;
    }

    public void persistData() {
        for(Invoice i : generatedInvoices) {
            invoiceGateway.insert(i.getId(), i.getSeller(), i.getProducts(), i.getTotal(), i.getDueDate(), i.getPayDate(), i.isDuplicate(), (int) i.getRemainingDays());
        }
    }
}
