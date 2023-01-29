package comunir.unirproducts.data;

import comunir.unirproducts.model.ElasticProduct;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;
import java.util.Optional;

public interface ElasticProductRepository extends ElasticsearchRepository<ElasticProduct, String> {
    Optional<ElasticProduct> findByName(String name);
    List<ElasticProduct> findByCountry(String country);
    List<ElasticProduct> findByVisible(Boolean visible);

    ElasticProduct saveProduct(ElasticProduct product);
}
