package ru.itpark.alexproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itpark.alexproject.entity.ProductType;
import ru.itpark.alexproject.service.ProductService;

@Controller
@RequestMapping("/")
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping()
    public String getAll(Model model) {
        model.addAttribute("items", service.getAll());
        return "all";
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable int id, Model model) {
        model.addAttribute("item", service.getById(id));

        return "view";
    }
    @GetMapping(value = "/search", params = "height") // Mapping - определяет то, что должно быть в запросе
    public String search(@RequestParam Integer height, Model model) {
        model.addAttribute("height", height); // чтобы отображать в поле поиска
        model.addAttribute("items", service.findAllMtbByHeight(height));
        return "all";
    }

    @GetMapping(value = "/search", params = "name") // Mapping - определяет то, что должно быть в запросе
    public String search(@RequestParam String name, Model model) {
        model.addAttribute("name", name); // чтобы отображать в поле поиска
        model.addAttribute("items", service.findByName(name));
        return "all";
    }

//    @GetMapping(value = "/search",params = "type" )
//    public String getByType(@PathVariable ProductType MTB, Model model) {
//        model.addAllAttributes("type", ProductType);
//        model.addAttribute("item", service.findByType(MTB));
//
//        return "allMtb";
    }

}
