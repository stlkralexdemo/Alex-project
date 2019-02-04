package ru.itpark.alexproject.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.itpark.alexproject.dto.LotDto;
import ru.itpark.alexproject.entity.LotEntity;
import ru.itpark.alexproject.entity.ProductEntity;
import ru.itpark.alexproject.exception.IdNotFoundException;
import ru.itpark.alexproject.exception.UnsupportedFileContentTypeException;
import ru.itpark.alexproject.exception.UploadFileException;
import ru.itpark.alexproject.repository.LotRepository;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;

@Service
public class LotService {
    private final LotRepository repository;
    private final Path uploadPath;

    public LotService(LotRepository repository, @Value("${spring.resources.static-locations}") String uploadPath) {
        this.repository = repository;
        this.uploadPath = Path.of(URI.create(uploadPath)).resolve("media");
        try {
            Files.createDirectories(this.uploadPath);
//            Files.writeString(this.uploadPath.resolve("demo.txt"), "Hello world");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save(LotDto item) {
        LotEntity entity = getByIdOrEmpty(item.getId());
        entity.setName(item.getName());
        entity.setPrice(item.getPrice());
        entity.setDescription(item.getDescription());

        MultipartFile file = item.getFile();
        if (!file.isEmpty() && file.getContentType() != null) {
            String ext;
            if (file.getContentType().equals(MediaType.IMAGE_PNG_VALUE)) {
                ext = ".png";
            } else if (file.getContentType().equals(MediaType.IMAGE_JPEG_VALUE)) {
                ext = ".jpg";
            } else {
                throw new UnsupportedFileContentTypeException();
            }

            String name = UUID.randomUUID().toString() + ext;

            try {
                file.transferTo(uploadPath.resolve(name));

                // Удаляем старый, если был
                if (entity.getPath() != null) {
                    Files.deleteIfExists(uploadPath.resolve(entity.getPath()));
                }
            } catch (IOException e) {
                throw new UploadFileException(e); // rethrowing
            }

            entity.setPath(name);
        }

        repository.save(entity);
    }

    public LotEntity getByIdOrEmpty(int id) {

        return repository.findById(id)
                .orElse(new LotEntity());
    }

    public List<LotEntity> getAll() {
        return repository.findAll();
    }

    public LotEntity getById(int id) {
        return repository.findById(id)
                .orElseThrow(IdNotFoundException::new);
    }
}