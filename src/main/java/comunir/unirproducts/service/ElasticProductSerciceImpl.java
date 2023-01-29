package comunir.unirproducts.service;
import comunir.unirproducts.model.ElasticProduct;
import comunir.unirproducts.data.ElasticsearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ElasticProductSerciceImpl extends ElasticProductService {

    private final ElasticsearchRepository repo;

    @Override
    public ElasticProduct getProductByIdent(String productId) {
        return repo.getById(productId);
    }

    @Override
    public ElasticProduct getProductByName(String productName) {
        return repo.getById(productName);
    }

    @Override
    public List<ElasticProduct> getProductsByCountry(String country) {
        return repo.getByCountry(country);
    }

    @Override
    public List<ElasticProduct> searchByDescription(String productDescription) {
        return repo.searchByDescription(productDescription);
    }

    @Override
    public List<ElasticProduct> searchByName(String productName) {
        return repo.searchByName(productName);
    }

    @Override
    public List<ElasticProduct> getAviableProducts(){
        return repo.getVisible();
    }

    @Override
    public ElasticProduct createProduct(CreateProductRequest request) {
        if (request != null && StringUtils.hasLength(request.getName().trim())
            && StringUtils.hasLength(request.getDescription().trim())
            && StringUtils.hasLength(request.getCompany().trim())
            && request.getVisible() != null) {

        ElasticProduct product = ElasticProduct.builder().idString.value(request.getName().hashCode()))
            .name(request.getName()).description(request.getDescription)))
            .country(request.getCountry()).visible(request.getVisible()).build();

        return repo.saveProduct(product);
        }else {
            return null;
        }
    }
}
