package Generators;

import Model.Company;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CompanyGenerator {

    private static final String[] names = {"Romanian", "European", "Italian", "Food", "Electricity", "Incorporated", "Motion", "Premium"};

    private List<String> generateCompanyWithTwoNames(String[] companyNames) {
        String[] companies;
        if(companyNames == null){
            companies = names;
        } else
            companies = companyNames;
        List<String> results = new ArrayList<String>();
        for(int i = 0; i < companies.length; i++) {
            for(int j = i + 1; j < companies.length - 1 ; j++) {
                StringBuilder sb = new StringBuilder();
                sb.append(companies[i]);
                sb.append(" ");
                sb.append(companies[j]);
                results.add(sb.toString());
            }
        }
        return results;
    }

    private List<String> generateCompanyWithThreeNames(String[] companyNames) {
        String[] companies;
        if(companyNames == null){
            companies = names;
        } else
            companies = companyNames;
        List<String> results = new ArrayList<String>();
        for(int i = 0; i < companies.length; i++) {
            for(int j = i + 1; j < companies.length - 1; j++) {
                if((j + 1) <= companies.length - 1) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(companies[i]);
                    sb.append(" ");
                    sb.append(companies[j]);
                    sb.append(" ");
                    sb.append(companies[j + 1]);
                    results.add(sb.toString());
                }
            }
        }
        return results;
    }

    private String generatePhoneNumber() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for(int i = 0; i < 15; i++) {
            int nr = random.nextInt(10);
            sb.append(nr);
        }
        return sb.toString();
    }

    public List<Company> generateCompanies(int nrOfCompanies, String[] companyNames) {
        List<Company> companies = new ArrayList<Company>();
        int id = 1;
        List<String> companiesWithTwoNames = generateCompanyWithTwoNames(companyNames);
        List<String> companiesWithThreeNames = generateCompanyWithThreeNames(companyNames);
        for(int i = 0; i < 3; i++) {
            companies.add(new Company(id, companiesWithThreeNames.get(i), generatePhoneNumber()));
            id++;
        }
        int remaining = nrOfCompanies - 3;
        if(remaining <= companiesWithTwoNames.size()) {
            for(int i = 0; i < remaining; i++) {
                companies.add(new Company(id,companiesWithTwoNames.get(i), generatePhoneNumber()));
                id++;
            }
        } else {
            for(int i = 0; i < companiesWithTwoNames.size(); i++) {
                companies.add(new Company(id, companiesWithTwoNames.get(i), generatePhoneNumber()));
                id++;
            }
            remaining-= companiesWithTwoNames.size();
            for(int j = 0; j < remaining; j++) {
                companies.add(new Company(id, companiesWithThreeNames.get(j), generatePhoneNumber()));
                id++;
            }
        }
        return companies;
    }
}
