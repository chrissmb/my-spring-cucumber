package com.example.myspringcucumber.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("calc")
public class CalculatorController {

    @GetMapping("sum/{x}/{y}")
    public Double sum(@PathVariable Double x, @PathVariable Double y) {
        return x + y;
    }

    @GetMapping("divide/{x}/{y}")
    public Double divide(@PathVariable Double x, @PathVariable Double y) {
        if (y == 0) {
            throw new RuntimeException("/ by zero");
        }
        return x/y;
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handlerRuntimeException(RuntimeException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
