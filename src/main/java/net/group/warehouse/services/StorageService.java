package net.group.warehouse.services;

import net.group.warehouse.api.request.StorageRequest;
import net.group.warehouse.api.respone.ProductResponse;
import net.group.warehouse.api.respone.StorageResponse;
import net.group.warehouse.persistence.model.Product;
import net.group.warehouse.persistence.model.Storage;
import net.group.warehouse.persistence.repository.StorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StorageService {

    @Autowired
    private final StorageRepository repository;

    public StorageService(StorageRepository repository) {
        this.repository = repository;
    }

    public List<StorageResponse> getAll() {
        List<Storage> storages = repository.findAll();
        return storages.stream().map(storage -> {
            StorageResponse storageResponse = new StorageResponse();
            storageResponse.setId(storage.getId());
            storageResponse.setName(storage.getName());
            storageResponse.setCapacity(storage.getCapacity());
            return storageResponse;
        }).collect(Collectors.toList());
    }


    public void deleteById(Long storageId) {
        repository.deleteById(storageId);
    }

    public StorageResponse getStorageById(Long id) {
        Storage storage = repository.findById(id).orElse(null);
        StorageResponse storageResponse = new StorageResponse();
        if (storage != null) {
            storageResponse.setId(storage.getId());
            storageResponse.setName(storage.getName());
            storageResponse.setCapacity(storage.getCapacity());
        }
        return storageResponse;
    }

    public Long saveStorage(final StorageRequest storageRequest) {
        Storage storage = new Storage();
        storage.setId(storageRequest.getId());
        storage.setName(storageRequest.getName());
        storage.setCapacity(storageRequest.getCapacity());
        Storage save = repository.save(storage);
        return save.getId();
    }

    public List<ProductResponse> getStorageProducts(StorageResponse storage) {
        List<Product> products = repository.getStorageProducts(storage.getId());
        return products.stream().map(product -> {
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
}
