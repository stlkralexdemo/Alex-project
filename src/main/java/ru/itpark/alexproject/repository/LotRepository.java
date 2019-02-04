package ru.itpark.alexproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itpark.alexproject.entity.LotEntity;


public interface LotRepository extends JpaRepository<LotEntity, Integer> {

}
