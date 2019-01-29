package ru.itpark.alexproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itpark.alexproject.service.ProductService;

@Controller
@RequestMapping("/")
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("items", service.getAll());
        return "all";
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable int id, Model model) {
        model.addAttribute("item", service.getById(id));

        return "view";
    }
}
