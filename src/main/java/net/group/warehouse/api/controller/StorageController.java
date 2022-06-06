package net.group.warehouse.api.controller;

import net.group.warehouse.api.request.StorageRequest;
import net.group.warehouse.api.respone.ProductResponse;
import net.group.warehouse.api.respone.StorageResponse;
import net.group.warehouse.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping({"/storages"})
@RestController
public class StorageController {

    @Autowired
    private StorageService service;

    @GetMapping(produces = "application/json")
    public List<StorageResponse> storages() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public StorageResponse storageById(@PathVariable("id") Long id) {
        return service.getStorageById(id);
    }

    @PostMapping
    private Long saveStorage(@RequestBody StorageRequest storageRequest) {
        return service.saveStorage(storageRequest);
    }

    @PutMapping
    private Long updateStorage(@RequestBody StorageRequest storage) {
        return service.saveStorage(storage);
    }

    @GetMapping("/{id}/products/")
    public List<ProductResponse> getStorageProducts(@PathVariable Long id) {
        StorageResponse storage = service.getStorageById(id);
        return service.getStorageProducts(storage);
    }

    @DeleteMapping("delete/{id}")
    private void deleteStorage(@PathVariable("id") Long id) {
        service.deleteById(id);
    }

}
