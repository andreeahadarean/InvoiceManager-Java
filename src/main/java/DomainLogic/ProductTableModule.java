package DomainLogic;

import DataSource.ProductGateway;
import Model.Product;

import java.util.List;

public class ProductTableModule {

    private ProductGateway productGateway = new ProductGateway();

    public List<Product> getPersistentProducts() {
        List<Product> productList = productGateway.selectAll();
        return productList;
    }
}
