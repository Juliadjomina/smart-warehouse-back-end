package net.group.warehouse.api.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductRequest {

    private Long id;
    private String name;
    private String serialNumber;
    private LocalDateTime dateOfPurchase;
    private float length;
    private float width;
    private float depth;
    private String material;
    private Long storageId;

}
