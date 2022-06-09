package net.group.warehouse.service;

import net.group.warehouse.api.request.StorageRequest;
import net.group.warehouse.api.respone.ProductResponse;
import net.group.warehouse.api.respone.StorageResponse;
import net.group.warehouse.persistence.model.Product;
import net.group.warehouse.persistence.model.Storage;
import net.group.warehouse.persistence.repository.StorageRepository;
import net.group.warehouse.util.ProductMapper;
import net.group.warehouse.util.StorageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StorageService {

    @Autowired
    private StorageRepository repository;

    public List<StorageResponse> getAll() {
        List<Storage> storages = repository.findAll();
        return storages
                .stream()
                .map(StorageUtil::getStorageResponse)
                .collect(Collectors.toList());
    }

    public void deleteById(Long storageId) {
        repository.deleteById(storageId);
    }

    public StorageResponse getStorageById(final Long id) {
        final Storage storage = repository.findById(id).orElse(null);
        if (storage != null) {
            final StorageResponse storageResponse = new StorageResponse();
            storageResponse.setId(storage.getId());
            storageResponse.setName(storage.getName());
            storageResponse.setCapacity(storage.getCapacity());
            return storageResponse;
        } else {
            return new StorageResponse();
        }
    }

    public Long saveStorage(final StorageRequest storageRequest) {
        final Storage storage = new Storage();
        storage.setId(storageRequest.getId());
        storage.setName(storageRequest.getName());
        storage.setCapacity(storageRequest.getCapacity());
        return repository.save(storage).getId();
    }

    public List<ProductResponse> getStorageProducts(final StorageResponse storage) {
        final List<Product> products = repository.getStorageProducts(storage.getId());
        return products
                .stream()
                .map(ProductMapper::getProductResponse)
                .collect(Collectors.toList());
    }
}
