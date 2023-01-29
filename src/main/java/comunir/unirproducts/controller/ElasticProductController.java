package comunir.unirproducts.controller;

import comunir.unirproducts.model.ElasticProduct;
import comunir.unirproducts.service.ElasticProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor

public class ElasticProductController {



        private final ElasticProductService service;

        @GetMapping("/elastic/product/{productId}")
        public ResponseEntity<ElasticProduct> getProducById(@PathVariable String productId) {

            ElasticProduct product = service.getProductById(productId);

            if  (product != null) {
                return ResponseEntity.ok(product);
            }else {
                return ResponseEntity.notFound().build();
            }
        }

        @GetMapping("/elastic/products")
        public ResponseEntity<ElasticProduct> getProducts() {

            List<ElasticProduct> product = service.getAvailableProducts();

            if  (product != null) {
                return ResponseEntity.ok((ElasticProduct) product);
            }else {
                return ResponseEntity.notFound().build();
            }
        }

        @GetMapping("/elastic/products/match/{value}")
        public ResponseEntity<ElasticProduct> getProductByName(@PathVariable String value) {

            List<ElasticProduct> product = service.searchByName(value);

            if  (product != null) {
                return ResponseEntity.ok((ElasticProduct) product);
            }else {
                return ResponseEntity.notFound().build();
            }
        }

        @GetMapping("/elastic/products/search/as-you-type/{value}")
        public ResponseEntity<List<ElasticProduct>> getProductByName(@PathVariable String value) {

            List<ElasticProduct> product = service.searchByName(value);

            if  (product != null) {
                return ResponseEntity.ok(product);
            }else {
                return ResponseEntity.notFound().build();
            }
        }

        @GetMapping("/elastic/products/search/full-text/{value}")
        public ResponseEntity<List<ElasticProduct>> searchByDescription(@PathVariable String value) {

            List<ElasticProduct> product = service.searchByDescription(value);

            if  (product != null) {
                return ResponseEntity.ok(product);
            }else {
                return ResponseEntity.notFound().build();
            }
        }

        @PostMapping("/elastic/products")
        public ResponseEntity<ElasticProduct> getProduct(@RequestBody CreateElasticProductRequest request) {

            ElasticProduct createElasticProduct = service.createElasticProduct(request);

            if  (createElasticProduct != null) {
                return ResponseEntity.status(HttpStatus.CREATED).body(createElasticProduct);
            }else {
                return ResponseEntity.badRequest().build();
            }
        }
        @Autowired
        private ElasticSearchQuery elasticSearchQuery;

        @PostMapping("/createOrUpdateDocument")
        public ResponseEntity<Object> createOrUpdateDocument(@RequestBody Product product) throws IOException {
            String response = elasticSearchQuery.createOrUpdateDocument(product);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        @GetMapping("/getDocument")
        public ResponseEntity<Object> getDocumentById(@RequestParam String productId) throws IOException {
            Product product =  elasticSearchQuery.getDocumentById(productId);
            return new ResponseEntity<>(product, HttpStatus.OK);
        }

        @DeleteMapping("/deleteDocument")
        public ResponseEntity<Object> deleteDocumentById(@RequestParam String productId) throws IOException {
            String response =  elasticSearchQuery.deleteDocumentById(productId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        @GetMapping("/searchDocument")
        public ResponseEntity<Object> searchAllDocument() throws IOException {
            List<Product> products = elasticSearchQuery.searchAllDocuments();
            return new ResponseEntity<>(products, HttpStatus.OK);
        }
    }
}
