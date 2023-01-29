package comunir.unirproducts.service;

import comunir.unirproducts.model.ElasticProduct;

import java.util.List;

public interface ElasticProductService {
    List<ElasticProduct> searchByName(String productName);

    List<ElasticProduct> getAviableProducts();

    /*
            default ElasticProduct createProduct(CreateProductRequest request) {
                return null;
            }
        */
    default ElasticProduct getProductById(String productId) {
        return null;
    }

    ElasticProduct getProductByIdent(String productId);

    default ElasticProduct getProductByName(String productName) {
        return null;
    }

    default List<ElasticProduct> getProductsByCountry(String country) {
        return null;
    }

    default List<ElasticProduct> searchByDescription(String productDescription) {
        return null;
    }

    default List<ElasticProduct> searByName(String productName) {
        return null;
    }

    default List<ElasticProduct> getAvailableProducts() {
        return null;
    }
}
