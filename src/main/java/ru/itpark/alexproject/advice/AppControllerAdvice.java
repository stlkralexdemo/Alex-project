package ru.itpark.alexproject.advice;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.itpark.alexproject.exception.IdNotFoundException;
import ru.itpark.alexproject.exception.ProductNotFoundException;
import ru.itpark.alexproject.exception.UnsupportedFileContentTypeException;
import ru.itpark.alexproject.exception.UploadFileException;


@ControllerAdvice
public class AppControllerAdvice {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProductNotFoundException.class)
    public String handleProductNotFound(Model model) {
        model.addAttribute("message", "Мы не поставляем инвентарь на ваш рост");
        return "error";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(IdNotFoundException.class)
    public String handleIdNotFound(Model model) {
        model.addAttribute("message", "Товар с данным id не найден");
        return "error";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UnsupportedFileContentTypeException.class)
    public String unsupportedFile(Model model) {
        model.addAttribute("message", "Поддерживаемые форматы картинок jpg и png, размер не более 10Mb");
        return "error";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UploadFileException.class)
    public String UploadFileError(Model model) {
        model.addAttribute("message", "Не удалось загрузить файл");
        return "error";
    }
}
