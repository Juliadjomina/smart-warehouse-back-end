package net.group.warehouse.api.controller;

import net.group.warehouse.api.request.ProductRequest;
import net.group.warehouse.api.respone.ProductResponse;
import net.group.warehouse.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping({"/products"})
@RestController
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping(produces = "application/json")
    public List<ProductResponse> allProducts() {
        return service.getAllProducts();
    }

    @GetMapping("{id}")
    public ProductResponse productById(@PathVariable Long id) {
        return service.getTheProductById(id);
    }

    @PostMapping
    private Long saveProduct(@RequestBody ProductRequest productRequest) {
        return service.saveProduct(productRequest);
    }

    @PutMapping
    private Long updateProduct(@RequestBody ProductRequest productRequest) {
        return service.saveProduct(productRequest);
    }

    @DeleteMapping("{id}")
    private void deleteProduct(@PathVariable("id") Long id) {
        service.deleteProduct(id);
    }
}
