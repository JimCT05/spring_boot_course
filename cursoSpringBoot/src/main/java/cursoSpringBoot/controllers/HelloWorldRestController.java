package cursoSpringBoot.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldRestController {

    // @GetMapping("/")
    // public String root() {
    //     return "Hello World desde root";
    // }

    @GetMapping({"/hello", "/hw", "/hola"})
    public String helloWorld(){
        return "Hello World";
    }
}
