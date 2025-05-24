package org.lessons.java.spring_la_mia_pizzeria_crud.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/")
public class homeController {

    @GetMapping
    public String home() {
        return "home";
    }

}
