package com.abdulazizpr.rediwork.web;

import com.abdulazizpr.rediwork.entity.Fruits;
import com.abdulazizpr.rediwork.repository.FruitsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@Controller
public class FruitUIController {
    @Autowired
    private FruitsRepository repository;

    @GetMapping("/fruits")
    public String getFruitsPage() {
        return "fruits";
    }

    @GetMapping("/fruits/create")
    public String createFruitForm(Model model) {
        model.addAttribute("fruit", new Fruits());
        return "fruit-form";
    }

    @GetMapping("/fruits/{id}/edit")
    public String editFruitForm(@PathVariable(name = "id") UUID id, Model model) {
        Fruits fruit = new Fruits();
        fruit.setId(id);

        model.addAttribute("fruit", fruit);

        return "fruit-form";
    }
}
