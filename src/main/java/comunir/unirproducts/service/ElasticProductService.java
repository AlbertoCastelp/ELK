package comunir.unirproducts.service;

import comunir.unirproducts.model.ElasticProduct;

import java.util.List;

public interface ElasticProductService {

    ElasticProduct createProduct(CreateProductRequest request) {
        return null;
    }

    ElasticProduct getProductById(String productId) {
        return null;
    }

    ElasticProduct getProductByIdent(String productId);

    ElasticProduct getProductByName(String productName) {
        return null;
    }

    List<ElasticProduct> getProductsByCountry(String country) {
        return null;
    }

    List<ElasticProduct> searchByDescription(String productDescription) {
        return null;
    }

    List<ElasticProduct> searByName(String productName) {
        return null;
    }

    List<ElasticProduct> getAvailableProducts() {
        return null;
    }
}
