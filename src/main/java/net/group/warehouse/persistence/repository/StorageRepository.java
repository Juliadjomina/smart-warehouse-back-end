package net.group.warehouse.persistence.repository;

import net.group.warehouse.persistence.model.Product;
import net.group.warehouse.persistence.model.Storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StorageRepository extends JpaRepository<Storage, Long> {
    @Query("select p from Product p where p.storageId=:storageId")
    List<Product> getStorageProducts(@Param("storageId") Long storageId);
}
