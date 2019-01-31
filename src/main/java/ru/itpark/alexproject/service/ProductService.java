package ru.itpark.alexproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itpark.alexproject.entity.ProductEntity;
import ru.itpark.alexproject.entity.MTBSize;
import ru.itpark.alexproject.entity.ProductType;
import ru.itpark.alexproject.exception.ProductNotFoundException;
import ru.itpark.alexproject.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    private MTBSize findMtbBySize(int height) {
        if (height > 150 && height <= 160) {
            return MTBSize.XS;
        } else if (height > 160 && height <= 170) {
            return MTBSize.S;
        } else if (height > 170 && height <= 180) {
            return MTBSize.M;
        } else if (height > 180 && height <= 190) {
            return MTBSize.L;
        } else if (height > 190 && height <= 200) {
            return MTBSize.XL;
        } else throw new ProductNotFoundException();
    }

    public List<ProductEntity> findAllMtbByHeight(int height) {
        return repository.findAllByMtbSizeOrderByPriceDesc(findMtbBySize(height));
    }

    private int findRoadBikeBySize(int height) {
        if (height > 150 && height <= 160) {
            return 52;
        } else if (height > 160 && height <= 170) {
            return 54;
        } else if (height > 170 && height <= 180) {
            return 56;
        } else if (height > 180 && height <= 190) {
            return 58;
        } else if (height > 190 && height <= 200) {
            return 60;
        } else throw new ProductNotFoundException();
    }

    public List<ProductEntity> findAllRoadBikeByHeight(int height) {
        return repository.findAllByRoadBikeSizeOrderByPriceDesc(findRoadBikeBySize(height));
    }

    public List<ProductEntity> getAll() {
        return repository.findAll();
    }

    public ProductEntity getById(int id) {
        return repository.findById(id)
                .orElseThrow(ProductNotFoundException::new);
    }

    public List<ProductEntity> findByName(String name) {
        return repository.findAllByNameContainsIgnoreCaseOrderByPriceDesc(name);
    }

    public List<ProductEntity> findByType(ProductType type) {
        return repository.findProductEntitiesByProductTypeOrderByPriceDesc(type);
    }
}
