package Model;

import java.util.*;

public class Invoice {
    private int id;
    private String seller;
    private String products;
    private float total;
    private Date dueDate;
    private Date payDate;
    private boolean duplicate = false;
    private long remainingDays;

    public Invoice() {

    }

    public Invoice(int id, String seller, String products, float total, Date dueDate, Date payDate, boolean duplicate, long remainingDays) {
        this.id = id;
        this.seller = seller;
        this.products = products;
        this.total = total;
        this.dueDate = dueDate;
        this.payDate = payDate;
        this.duplicate = duplicate;
        this.remainingDays = remainingDays;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getProducts() { return products; }

    public void setProducts(String products) {
        this.products = products;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public boolean isDuplicate() {
        return duplicate;
    }

    public void setDuplicate(boolean duplicate) {
        this.duplicate = duplicate;
    }

    public void setRemainingDays(long remainingDays) {
        this.remainingDays = remainingDays;
    }

    public long getRemainingDays() {
        return remainingDays;
    }



    public void displayInvoice() {
        StringBuilder sb = new StringBuilder();
        sb.append("Invoice " + id);
        sb.append(" from seller " + seller);
        sb.append(" with due date " + dueDate.toString());
        if(payDate == null) {
            sb.append(" to be paied in " + remainingDays + " days,");
        } else sb.append(" paied on " + payDate.toString());
        sb.append(" containing ");
        sb.append(products);
        sb.append(" with a total of " + total);
        if(duplicate == false) {
            sb.append(", not duplicated.");
        } else sb.append(", duplicated.");
        System.out.println(sb.toString());
    }

    @Override
    public String toString() {
        return "Invoice " + id + " from " + seller + " with products " + products + " with a total of " + total +
                " due on " + dueDate + " paied on " + payDate + " ,duplicate " + duplicate + ".\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Invoice)) return false;
        Invoice invoice = (Invoice) o;
        return (invoice.getSeller().equals(this.getSeller()) &&
                invoice.getProducts().equals(this.getProducts()) &&
                invoice.getTotal() == this.getTotal() &&
                invoice.getDueDate().equals(this.getDueDate()));
    }

}
