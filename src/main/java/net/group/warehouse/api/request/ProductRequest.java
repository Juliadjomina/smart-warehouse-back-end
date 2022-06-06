package net.group.warehouse.api.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductRequest {

    private Long id;
    private String name;
    private String serialnumber;
    private LocalDateTime dateofpurchase;
    private float length;
    private float width;
    private float depth;
    private String material;
    private Long storageId;

}
