package net.group.warehouse.persistence.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "product")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Size(max = 50)
    private String name;
    @Size(max = 50)
    private String serialNumber;
    private LocalDateTime dateOfPurchase;
    private float length;
    private float width;
    private float depth;
    @Size(max = 50)
    private String material;
    @JoinColumn(name = "storage_id")
    private Long storageId;
}
