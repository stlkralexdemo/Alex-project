package ru.itpark.alexproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.itpark.alexproject.entity.ProductEntity;
import ru.itpark.alexproject.entity.ProductType;
import ru.itpark.alexproject.repository.ProductRepository;

import java.util.List;

@SpringBootApplication
public class AlexProjectApplication {

    public static void main(String[] args) {
        var context = SpringApplication.run(AlexProjectApplication.class, args);

        var repository = context.getBean(ProductRepository.class);

        repository.saveAll(List.of(
                new ProductEntity(0,"Peltonen Supra X",13499,null,null,null,193, ProductType.SKATE,null),
                new ProductEntity(0,"Peltonen Infra X",13499,null,null,null,207, ProductType.CLASSIC,null),
                new ProductEntity(0,"Tisa Race Cup Skating",3799,null,null,null,197, ProductType.SKATE,null),
                new ProductEntity(0,"Giant ",53499,null,null,60,null, ProductType.ROAD,null),
                new ProductEntity(0,"Radon",33499,null,"XL",null,null, ProductType.MTB,null)
        ));
    }

}

