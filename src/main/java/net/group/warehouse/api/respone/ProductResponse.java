package net.group.warehouse.api.respone;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductResponse {
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
