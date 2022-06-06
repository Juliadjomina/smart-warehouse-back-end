package net.group.warehouse.api.respone;

import lombok.Data;

@Data
public class StorageResponse {
    private Long id;
    private String name;
    private int capacity;
}
