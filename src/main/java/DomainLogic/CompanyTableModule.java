package DomainLogic;

import DataSource.CompanyGateway;
import Model.Company;

import java.util.List;

public class CompanyTableModule {

    private CompanyGateway companyGateway = new CompanyGateway();

    public List<Company> getPersistentCompanies() {
        List<Company> companies = companyGateway.selectAll();
        return companies;
    }

}
