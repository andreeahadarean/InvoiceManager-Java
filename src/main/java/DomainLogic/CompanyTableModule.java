package DomainLogic;

import DataSource.CompanyGateway;
import Model.Company;

import java.util.List;

public class CompanyTableModule {

    private CompanyGateway companyGateway = new CompanyGateway();
    private List<Company> generatedCompanies;

    public CompanyTableModule(List<Company> generatedCompanies) {
        this.generatedCompanies = generatedCompanies;
    }

    public List<Company> getPersistentCompanies() {
        List<Company> companies = companyGateway.selectAll();
        return companies;
    }

    public void persistData() {
        for(Company c : generatedCompanies)
        companyGateway.insert(c.getId(), c.getName(), c.getPhoneNr());
    }

}
