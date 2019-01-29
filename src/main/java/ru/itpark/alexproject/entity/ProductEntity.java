package ru.itpark.alexproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;

@Data
@Entity
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Integer price;
    private String description;
    private String mtbSize;
    private Integer roadBikeSize;
    private Integer skiSize;
    private ProductType productType;
    private String image;

}
