package DomainLogic;

import DataSource.ProductGateway;
import Model.Product;

import java.util.List;

public class ProductTableModule {

    private ProductGateway productGateway = new ProductGateway();
    private List<Product> generatedProducts;

    public ProductTableModule(List<Product> generatedProducts) {
        this.generatedProducts = generatedProducts;
    }

    public List<Product> getPersistentProducts() {
        List<Product> productList = productGateway.selectAll();
        return productList;
    }

    public void persistData() {
        for(Product p : generatedProducts) {
            productGateway.insert(p.getId(), p.getName(), (int) p.getPrice());
        }
    }
}
