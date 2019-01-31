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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Integer price;
    @Column(length = 2550)
    private String description;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "MTBSIZE")
    private MTBSize mtbSize;
    @Column(name = "ROADBIKESIZE")
    private Integer roadBikeSize;
    @Column(name = "SKISIZE")
    private Integer skiSize;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "PRODUCTTYPE")
    private ProductType productType;
    private String image;

}
