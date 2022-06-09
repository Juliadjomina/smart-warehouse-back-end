package net.group.warehouse.services;

import net.group.warehouse.api.request.ProductRequest;
import net.group.warehouse.api.respone.ProductResponse;
import net.group.warehouse.persistence.model.Product;
import net.group.warehouse.persistence.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private final ProductRepository repository;

    public ProductService(final ProductRepository repository) {
        this.repository = repository;
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> products = repository.findAll();
        return products.stream().map(product-> {
            ProductResponse productResponse = new ProductResponse();
            productResponse.setId(product.getId());
            productResponse.setName(product.getName());
            productResponse.setSerialnumber(product.getSerialNumber());
            productResponse.setDateofpurchase(product.getDateOfPurchase());
            productResponse.setLength(product.getLength());
            productResponse.setWidth(product.getWidth());
            productResponse.setDepth(product.getDepth());
            productResponse.setMaterial(product.getMaterial());
            productResponse.setStorageId(product.getStorageId());
            return productResponse;
        }).collect(Collectors.toList());
    }


    public ProductResponse getTheProductById(Long id) {
        Product product = repository.findById(id).orElse(null);
        ProductResponse productResponse = new ProductResponse();
        if (product != null) {
            productResponse.setId(product.getId());
            productResponse.setName(product.getName());
            productResponse.setSerialnumber(product.getSerialNumber());
            productResponse.setDateofpurchase(product.getDateOfPurchase());
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
        product.setSerialNumber(productRequest.getSerialNumber());
        product.setDateOfPurchase(productRequest.getDateOfPurchase());
        product.setLength(productRequest.getLength());
        product.setWidth(productRequest.getWidth());
        product.setDepth(productRequest.getDepth());
        product.setStorageId(productRequest.getStorageId());
        product.setMaterial(productRequest.getMaterial());
        Product save = repository.save(product);
        return save.getId();
    }
}
