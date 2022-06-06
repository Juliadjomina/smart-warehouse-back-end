package net.group.warehouse.api.request;

import lombok.Data;

@Data
public class StorageRequest {
    private Long id;
    private String name;
    private int capacity;
}
