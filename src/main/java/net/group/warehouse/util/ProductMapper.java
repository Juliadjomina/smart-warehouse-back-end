package net.group.warehouse.util;

import net.group.warehouse.api.respone.ProductResponse;
import net.group.warehouse.persistence.model.Product;

public class ProductMapper {

    public static ProductResponse getProductResponse(Product product) {
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(product.getId());
        productResponse.setName(product.getName());
        productResponse.setSerialNumber(product.getSerialNumber());
        productResponse.setDateOfPurchase(product.getDateOfPurchase());
        productResponse.setLength(product.getLength());
        productResponse.setWidth(product.getWidth());
        productResponse.setDepth(product.getDepth());
        productResponse.setMaterial(product.getMaterial());
        productResponse.setStorageId(product.getStorageId());
        return productResponse;
    }
}
