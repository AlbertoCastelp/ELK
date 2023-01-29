package comunir.unirproducts.data;
import comunir.unirproducts.model.ElasticProduct;

import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ElasticsearchRepository {

    private final String[] nameSarchFields = {"name.search", "name.search._2gram", "name.search._3gram"};

    private final ElasticProductRepository productRepository;

    private final ElasticsearchOperations elasticClient;

    public ElasticProduct getById(String id) {return productRepository.findById(id).orElse(null);}

    public ElasticProduct getByName(String name) {
        return productRepository.findByName(name).orElse(null);
    }

    public List<ElasticProduct> getByCountry(String country) {
        return productRepository.findByCountry(country);
    }

    public List<ElasticProduct> getVisible(){
        return productRepository.findByVisible(true);
    }

    public List<ElasticProduct> searchByName(String namePart){
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        boolQuery.must(QueryBuilders.multiMatchQuery(namePart, nameSarchFields).type(MultiMatchQueryBuilder.Type.BOOL_PREFIX));
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder().withQuery(boolQuery);
        NativeSearchQuery query = nativeSearchQueryBuilder.build();
        SearchHits<ElasticProduct> result = elasticClient.search(query, ElasticProduct.class);
        return result.getSearchHits().stream().map(SearchHit::getContent).collect(Collectors.toList());
    }
    public List<ElasticProduct> searchByDescription(String description) {
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        boolQuery.must(QueryBuilders.matchQuery("description", description));
        NativeSearchQueryBuilder nativeSearchQueryBuilder =
                new NativeSearchQueryBuilder().withQuery(boolQuery);
        NativeSearchQuery query = nativeSearchQueryBuilder.build();
        SearchHits<ElasticProduct> result = elasticClient.search(query, ElasticProduct.class);
        return result.getSearchHits().stream().map(SearchHit::getContent).collect(Collectors.toList());
    }
    public ElasticProduct saveProduct(ElasticProduct product) {
        return productRepository.saveProduct(product);
    }
}
