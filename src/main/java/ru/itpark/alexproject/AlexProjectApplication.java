package ru.itpark.alexproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.itpark.alexproject.entity.MTBSize;
import ru.itpark.alexproject.entity.ProductEntity;
import ru.itpark.alexproject.entity.ProductType;
import ru.itpark.alexproject.repository.ProductRepository;

import java.util.List;

import static ru.itpark.alexproject.entity.MTBSize.*;
import static ru.itpark.alexproject.entity.MTBSize.M;

@SpringBootApplication
public class AlexProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlexProjectApplication.class, args);
    }

}

