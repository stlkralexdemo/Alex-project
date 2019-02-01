package ru.itpark.alexproject.advice;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.itpark.alexproject.exception.IdNotFoundException;
import ru.itpark.alexproject.exception.ProductNotFoundException;


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
}
