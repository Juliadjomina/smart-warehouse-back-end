package net.group.warehouse.services;

import net.group.warehouse.api.request.ProductRequest;
import net.group.warehouse.api.respone.ProductResponse;
import net.group.warehouse.persistence.model.Product;
import net.group.warehouse.persistence.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private final ProductRepository repository;

    public ProductService(final ProductRepository repository) {
        this.repository = repository;
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> products = repository.findAll();
        List<ProductResponse> productResponses = new ArrayList<>();
        for (Product product : products) {
            ProductResponse productResponse = new ProductResponse();
            productResponse.setId(product.getId());
            productResponse.setName(product.getName());
            productResponse.setSerialnumber(product.getSerialnumber());
            productResponse.setDateofpurchase(product.getDateofpurchase());
            productResponse.setLength(product.getLength());
            productResponse.setWidth(product.getWidth());
            productResponse.setDepth(product.getDepth());
            productResponse.setMaterial(product.getMaterial());
            productResponse.setStorageId(product.getStorageId());
            productResponses.add(productResponse);

        }
        return productResponses;
    }

    public ProductResponse getTheProductById(Long id) {
        Product product = repository.findById(id).orElse(null);
        ProductResponse productResponse = new ProductResponse();
        if (product != null) {
            productResponse.setId(product.getId());
            productResponse.setName(product.getName());
            productResponse.setSerialnumber(product.getSerialnumber());
            productResponse.setDateofpurchase(product.getDateofpurchase());
            productResponse.setLength(product.getLength());
            productResponse.setWidth(product.getWidth());
            productResponse.setDepth(product.getDepth());
            productResponse.setMaterial(product.getMaterial());
            productResponse.setStorageId(product.getStorageId());
        }
        return productResponse;
    }

    public void deleteProduct(Long id) {
        repository.deleteById(id);
    }

    public Long saveProduct(final ProductRequest productRequest) {
        Product product = new Product();
        product.setId(productRequest.getId());
        product.setName(productRequest.getName());
        product.setSerialnumber(productRequest.getSerialnumber());
        product.setDateofpurchase(productRequest.getDateofpurchase());
        product.setLength(productRequest.getLength());
        product.setWidth(productRequest.getWidth());
        product.setDepth(productRequest.getDepth());
        product.setStorageId(productRequest.getStorageId());
        Product save = repository.save(product);
        return save.getId();
    }
}
