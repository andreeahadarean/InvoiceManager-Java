package Generators;

import Model.Product;

import java.util.*;

public class ProductGenerator {

    private String generateName() {
        int leftLimit = 97;
        int rightLimit = 122;
        int targetStringLength = 5;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();
        return generatedString;
    }

    private float generatePrice() {
        float leftLimit = (float)0.1;
        float rightLimit = (float)999.9;
        Random r = new Random();
        float price = leftLimit + r.nextFloat() * (rightLimit - leftLimit);
        return price;
    }

    private boolean checkUniqueNames(List<String> names) {
        Set<String> s = new HashSet<String>(names);
        return (s.size() == names.size());
    }

    public List<Product> generateProducts(int nrOfProducts) {
        List<String> nameList = new ArrayList<String>();
        for(int i = 0; i < nrOfProducts; i++) {
            nameList.add(generateName());
        }
        List<Product> products = new ArrayList<Product>();
        int productNumber = 1;
        boolean done = false;
        while(!done) {
            if (checkUniqueNames(nameList)) {
                done = true;
            }
            for (int i = 0; i < nrOfProducts; i++) {
                float price = generatePrice();
                products.add(new Product(productNumber, nameList.get(i), price));
                productNumber++;
            }
        }
        return products;
    }
}
