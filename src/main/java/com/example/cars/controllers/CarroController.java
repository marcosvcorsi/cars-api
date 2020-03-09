package com.example.cars.controllers;

import com.example.cars.domain.Carro;
import com.example.cars.domain.dto.CarroDTO;
import com.example.cars.services.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/cars")
public class CarroController {

    @Autowired
    private CarroService carroService;

    @GetMapping
    public List<CarroDTO> getAll() {
        return carroService.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<CarroDTO> getCarro(@PathVariable Long id) {
        Optional<CarroDTO> carroDTO = carroService.get(id);

        return carroDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("tipo/{tipo}")
    public ResponseEntity<List<CarroDTO>> getCarrosByTipo(@PathVariable String tipo) {
        List<CarroDTO> carroList = carroService.getByTipo(tipo);

        return carroList.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(carroList);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Carro carro) {

        try {
            CarroDTO carroDTO = carroService.save(carro);

            return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path(("/{id}"))
                    .buildAndExpand(carroDTO.getId()).toUri()).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Carro carro) {
        try {
            CarroDTO carroDTO = carroService.update(id, carro);

            return ResponseEntity.ok(carroDTO);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            carroService.delete(id);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
