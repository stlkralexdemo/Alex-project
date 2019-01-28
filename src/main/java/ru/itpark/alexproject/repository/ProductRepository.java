package ru.itpark.alexproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itpark.alexproject.entity.ProductEntity;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

    List<ProductEntity> findAllByMtbSizeOrderByPriceDesc(String mtbSize);
}
