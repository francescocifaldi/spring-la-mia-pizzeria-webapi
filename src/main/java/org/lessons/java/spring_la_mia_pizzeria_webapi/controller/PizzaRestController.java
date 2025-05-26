package org.lessons.java.spring_la_mia_pizzeria_webapi.controller;

import java.util.List;

import org.lessons.java.spring_la_mia_pizzeria_webapi.model.Pizza;
import org.lessons.java.spring_la_mia_pizzeria_webapi.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

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

    @PostMapping("/create")
    public Pizza create(@RequestBody Pizza pizza) {
        Pizza newPizza = pizzaService.create(pizza);
        return newPizza;
    }

    @PutMapping("/{id}")
    public Pizza update(@PathVariable Integer id, @RequestBody Pizza pizza) {
        pizza.setId(id);
        Pizza updatedPizza = pizzaService.update(pizza);

        return updatedPizza;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        pizzaService.deleteById(id);
    }

}
