package Generators;

import Model.Company;
import Model.Invoice;
import Model.Product;
import java.util.*;

public class InvoiceGenerator {

    private static List<Company> getSellers(int nrOfInvoices, String[] companyNames){
        CompanyGenerator companyGenerator = new CompanyGenerator();
        List<Company> companies = new ArrayList<Company>();
        int nrOfTimes = nrOfInvoices/24;
        int generated = nrOfTimes * 24;
        int remaining = nrOfInvoices - generated;
        for(int i = 0; i < nrOfTimes; i++) {
            List<Company> companyList = companyGenerator.generateCompanies(24, companyNames);
            companies.addAll(companyList);
        }
        List<Company> companyList = companyGenerator.generateCompanies(24, companyNames);
        for(int i = 0; i < remaining; i++) {
            companies.add(companyList.get(i));
        }
        return companies;
    }

    private static List<Product> getProducts(int nrOfInvoices) {
        ProductGenerator productGenerator = new ProductGenerator();
        List<Product> products = new ArrayList<>();
        int nrOfTimes = nrOfInvoices/48;
        int generated = nrOfTimes * 48;
        int remaining = nrOfInvoices - generated;
        for(int i = 0; i < nrOfTimes; i++) {
            List<Product> productList = productGenerator.generateProducts(48);
            products.addAll(productList);
        }
        List<Product> productList = productGenerator.generateProducts(48);
        for(int i = 0; i < remaining; i++) {
            products.add(productList.get(i));
        }
        return products;
    }

    public static List<Invoice> generateInvoice(int nrOfInvoices, String[] companyNames) {
        List<Invoice> invoices = new ArrayList<Invoice>();
        List<Company> companies = getSellers(nrOfInvoices, companyNames);
        List<Product> products = getProducts(nrOfInvoices);
        int nrOfUnpaiedInvoices = (int)(0.1 * nrOfInvoices);
        int nrOfPaiedInvoices = nrOfInvoices - nrOfUnpaiedInvoices;
        int id = 1;
        for(int i = 0; i < nrOfInvoices; i++) {
            List<Product> productList = new ArrayList<Product>();
            float total = 0;
            Random random = new Random();
            for(int j = 0; j < 3; j++) {
                int index = random.nextInt(products.size());
                productList.add(products.get(index));
                total+=products.get(index).getPrice();
            }
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            int time = random.nextInt(5);
            calendar.add(Calendar.DATE, time);
            Date dueDate = calendar.getTime();
            List<String> productNames = new ArrayList<>();
            StringBuilder sb = new StringBuilder();
            for(Product p : productList) {
                sb.append(p.getName() + ", ");
            }
            Invoice invoice = new Invoice(id, companies.get(i).getName(), sb.toString(), total, dueDate, null, false, 0);
            invoices.add(invoice);
            id++;
        }
        Random random = new Random();
        for(int i = 0; i < nrOfPaiedInvoices; i++) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            int time = random.nextInt(5);
            calendar.add(Calendar.DATE, (-1) * time);
            Date payDate = calendar.getTime();
            invoices.get(i).setPayDate(payDate);
        }
        return invoices;
    }
}
