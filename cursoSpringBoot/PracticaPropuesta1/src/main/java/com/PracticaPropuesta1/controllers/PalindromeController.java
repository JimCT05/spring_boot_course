package com.PracticaPropuesta1.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controlador para validar si una palabra es un palíndromo.
 */
@RestController
public class PalindromeController {
    /**
     * Endpoint para validar si una palabra es un palíndromo.
     * @param word La palabra a validar
     * @return Un mensaje indicando si la palabra es un palíndromo o no
     */
    @GetMapping("/validar-palindromo/{word}")
    public String Paindrome(@PathVariable String word){
        if (isPalindrome(word)) {
            return "La palabra " + word + " es un palíndromo";
        }else{
            return "La palabra " + word + " NO es un palíndromo";
        }
    }

    /**
     * Método para verificar si una palabra es un palíndromo.
     * @param word La palabra a verificar
     * @return true si la palabra es un palíndromo, false en caso contrario
     */
    private boolean isPalindrome(String word){
        int length = word.length();
        for(int i = 0; i < length / 2; i++){
            if(word.charAt(i) != word.charAt(length -i -1)){
                return false;
            }
        }
        return true;
    }
}