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
    public ResponseEntity<ElasticProduct> getProductById(@PathVariable String productId) {

        ElasticProduct product = service.getProductById(productId);

        if  (product != null) {
            return ResponseEntity.ok(product);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/elastic/products")
    public ResponseEntity<List<ElasticProduct>> getProducts() {

        List<ElasticProduct> product = service.getAvailableProducts();

        if  (product != null) {
            return ResponseEntity.ok((product));
        }else {
            return ResponseEntity.notFound().build();
        }
    }
/*
    @GetMapping("/elastic/products/match/{value}")
    public ResponseEntity<ElasticProduct> getProductByName(@PathVariable String value) {

        ElasticProduct product = service.getProductByName(value);

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
*/
    @GetMapping("/elastic/products/search/full-text/{value}")
    public ResponseEntity<List<ElasticProduct>> searchByDescription(@PathVariable String value) {

        List<ElasticProduct> product = service.searchByDescription(value);

        if  (product != null) {
            return ResponseEntity.ok(product);
        }else {
            return ResponseEntity.notFound().build();
        }
    }
/*
    @PostMapping("/elastic/products")
    public ResponseEntity<ElasticProduct> getProduct(@RequestBody CreateProductRequest request) {

        ElasticProduct createProduct = service.createProduct(request);

        if  (createProduct != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createProduct);
        }else {
            return ResponseEntity.badRequest().build();
        }
    }
*/
}
