package org.lessons.java.spring_la_mia_pizzeria_webapi.controller;

import java.util.List;

import org.lessons.java.spring_la_mia_pizzeria_webapi.model.Pizza;
import org.lessons.java.spring_la_mia_pizzeria_webapi.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/pizzas")
public class PizzaRestController {

    @Autowired
    private PizzaService pizzaService;

    @GetMapping
    public List<Pizza> index() {
        List<Pizza> pizzas = pizzaService.findAll();
        return pizzas;
    }

    @GetMapping("/{id}")
    public Pizza show(@PathVariable Integer id) {
        Pizza pizza = pizzaService.getById(id);
        return pizza;
    }

}
