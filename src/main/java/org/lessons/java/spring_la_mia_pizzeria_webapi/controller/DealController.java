package org.lessons.java.spring_la_mia_pizzeria_webapi.controller;

import org.lessons.java.spring_la_mia_pizzeria_webapi.model.Deal;
import org.lessons.java.spring_la_mia_pizzeria_webapi.repository.DealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/deals")
public class DealController {

    @Autowired
    private DealRepository dealRepository;

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("deal") Deal newDeal,
            BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            return "deals/create-or-edit";
        }
        dealRepository.save(newDeal);
        return "redirect:/pizzas/" + newDeal.getPizza().getId();
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        Deal deal = dealRepository.findById(id).get();
        model.addAttribute("deal", deal);
        model.addAttribute("edit", true);
        return "deals/create-or-edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("deal") Deal newDeal,
            BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            return "deals/create-or-edit";
        }

        dealRepository.save(newDeal);
        return "redirect:/pizzas/" + newDeal.getPizza().getId();
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        Deal deal = dealRepository.findById(id).get();
        dealRepository.delete(deal);

        return "redirect:/pizzas/" + deal.getPizza().getId();
    }

}
