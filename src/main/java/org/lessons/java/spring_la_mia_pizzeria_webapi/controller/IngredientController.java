package org.lessons.java.spring_la_mia_pizzeria_webapi.controller;

import org.lessons.java.spring_la_mia_pizzeria_webapi.model.Ingredient;
import org.lessons.java.spring_la_mia_pizzeria_webapi.model.Pizza;
import org.lessons.java.spring_la_mia_pizzeria_webapi.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {

    @Autowired
    private IngredientRepository ingredientRepository;

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("ingredients", ingredientRepository.findAll());
        return "ingredients/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("ingredient", new Ingredient());
        return "ingredients/create-or-edit";
    }

    @PostMapping("/create")
    public String store(
            @Valid @ModelAttribute("ingredient") Ingredient ingredient,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            return "ingredients/create-or-edit";
        }

        ingredientRepository.save(ingredient);
        return "redirect:/ingredients";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        Ingredient ingredient = ingredientRepository.findById(id).get();
        model.addAttribute("ingredient", ingredient);
        model.addAttribute("edit", true);
        return "ingredients/create-or-edit";
    }

    @PostMapping("/edit/{id}")
    public String update(
            @Valid @ModelAttribute("ingredient") Ingredient ingredient,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            return "ingredients/create-or-edit";
        }

        ingredientRepository.save(ingredient);
        return "redirect:/ingredients";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        Ingredient ingredient = ingredientRepository.findById(id).get();
        for (Pizza pizza : ingredient.getPizzas()) {
            pizza.getIngredients().remove(ingredient);
        }
        ingredientRepository.deleteById(id);
        return "redirect:/ingredients";
    }

}
