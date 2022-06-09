package net.group.warehouse.service;

import net.group.warehouse.api.request.ProductRequest;
import net.group.warehouse.api.respone.ProductResponse;
import net.group.warehouse.persistence.model.Product;
import net.group.warehouse.persistence.repository.ProductRepository;
import net.group.warehouse.util.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public List<ProductResponse> getAllProducts() {
        final List<Product> products = repository.findAll();
        return products
                .stream()
                .map(ProductMapper::getProductResponse)
                .collect(Collectors.toList());
    }

    public ProductResponse getTheProductById(final Long id) {
        final Product product = repository.findById(id).orElse(null);

        if (product != null) {
            return ProductMapper.getProductResponse(product);
        } else {
            return new ProductResponse();
        }
    }

    public void deleteProduct(final Long id) {
        repository.deleteById(id);
    }

    public Long saveProduct(final ProductRequest productRequest) {
        final Product product = new Product();
        product.setId(productRequest.getId());
        product.setName(productRequest.getName());
        product.setSerialNumber(productRequest.getSerialNumber());
        product.setDateOfPurchase(productRequest.getDateOfPurchase());
        product.setLength(productRequest.getLength());
        product.setWidth(productRequest.getWidth());
        product.setDepth(productRequest.getDepth());
        product.setStorageId(productRequest.getStorageId());
        product.setMaterial(productRequest.getMaterial());
        return repository.save(product).getId();
    }
}
