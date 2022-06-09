package net.group.warehouse.util;

import net.group.warehouse.api.respone.StorageResponse;
import net.group.warehouse.persistence.model.Storage;

public class StorageUtil {

    public static StorageResponse getStorageResponse(Storage storage) {
        StorageResponse storageResponse = new StorageResponse();
        storageResponse.setId(storage.getId());
        storageResponse.setName(storage.getName());
        storageResponse.setCapacity(storage.getCapacity());
        return storageResponse;
    }
}
