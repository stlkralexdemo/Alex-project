package ru.itpark.alexproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itpark.alexproject.dto.LotDto;
import ru.itpark.alexproject.service.LotService;

@RequestMapping("/lot")
@Controller
public class LotController {
    private final LotService service;

    public LotController(LotService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public String getAll(Model model) {
        model.addAttribute("items", service.getAll());
        return "allLots";
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable int id, Model model) {
        model.addAttribute("item", service.getById(id));

        return "viewLot";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("item", service.getByIdOrEmpty(id));
        return "edit";
    }

    @PostMapping("/{id}/edit")
    public String edit(
            @PathVariable int id,
            @ModelAttribute LotDto dto
    ) {
        service.save(dto);

        return "redirect:/";
    }

    @GetMapping("/{id}/remove")
    public String remove(
            @PathVariable int id,
            Model model
    ) {
        model.addAttribute("item", service.getById(id));
        return "remove";
    }

    @PostMapping("/{id}/remove")
    public String remove(
            @PathVariable int id
    ) {
        service.removeById(id);
        return "redirect:/";
    }
}