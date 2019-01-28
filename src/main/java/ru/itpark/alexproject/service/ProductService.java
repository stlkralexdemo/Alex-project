package ru.itpark.alexproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itpark.alexproject.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    private String findMtbBySize(int height) {
        if (height > 150 && height <= 160) {
            return "XS";
        } else if (height > 160 && height <= 170) {
            return "S";
        } else if (height > 170 && height <= 180) {
            return "M";
        } else throw new NoSizeException();
    }


}
