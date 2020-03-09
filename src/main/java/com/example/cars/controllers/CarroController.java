package com.example.cars.controllers;

import com.example.cars.domain.Carro;
import com.example.cars.services.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/cars")
public class CarroController {

    @Autowired
    private CarroService carroService;

    @GetMapping
    public List<Carro> getAll() {
        return carroService.findAll();
    }

    @GetMapping("{id}")
    public Optional<Carro> getCarro(@PathVariable Long id) {
        return carroService.get(id);
    }

    @GetMapping("tipo/{tipo}")
    public List<Carro> getCarrosByTipo(@PathVariable String tipo) {
        return carroService.getByTipo(tipo);
    }

    @PostMapping
    public void save(@RequestBody Carro carro) {
        carroService.save(carro);
    }

    @PutMapping("{id}")
    public void update(@PathVariable Long id, @RequestBody Carro carro) {
        carroService.update(id, carro);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        carroService.delete(id);
    }
}
