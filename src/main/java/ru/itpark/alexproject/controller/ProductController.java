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
    public String getIndex(){
        return "index";
    }

    @GetMapping("/all")
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
        return "allMtb";
    }

    @GetMapping(value = "/searchRoad", params = "height") // Mapping - определяет то, что должно быть в запросе
    public String searchRoad(@RequestParam Integer height, Model model) {
        model.addAttribute("height", height); // чтобы отображать в поле поиска
        model.addAttribute("items", service.findAllRoadBikeByHeight(height));
        return "allRoadBikes";
    }

    @GetMapping(value = "/searchClassic", params = "height") // Mapping - определяет то, что должно быть в запросе
    public String searchClassic(@RequestParam Integer height, Model model) {
        model.addAttribute("height", height); // чтобы отображать в поле поиска
        model.addAttribute("items", service.findAllClassicSkiBySize(height));
        return "allClassic";
    }

    @GetMapping(value = "/searchSkate", params = "height") // Mapping - определяет то, что должно быть в запросе
    public String searchSkate(@RequestParam Integer height, Model model) {
        model.addAttribute("height", height); // чтобы отображать в поле поиска
        model.addAttribute("items", service.findAllSkateSkiBySize(height));
        return "allSkate";
    }

    @GetMapping(value = "/search", params = "name") // Mapping - определяет то, что должно быть в запросе
    public String search(@RequestParam String name, Model model) {
        model.addAttribute("name", name); // чтобы отображать в поле поиска
        model.addAttribute("items", service.findByName(name));
        return "all";
    }

    @GetMapping("/index/{Mtb}")
    public String getByType(@PathVariable("Mtb") ProductType type, Model model) {
        model.addAttribute("items", service.findByType(type));
        switch (type) {
            case MTB:
                return "allMtb";
            case SKATE:
                return "allSkate";
            case ROAD:
                return "allRoadBikes";
            case CLASSIC:
                return "allClassic";
            default:
                throw new IllegalArgumentException("Unknown type");
        }
    }

}
