package ru.itpark.alexproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LotDto {
    private int id;
    private String name;
    private String description;
    private int price;
    private MultipartFile file; // name="file"
}
