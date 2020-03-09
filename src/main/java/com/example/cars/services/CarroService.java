package com.example.cars.services;

import com.example.cars.domain.Carro;
import com.example.cars.repositories.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarroService {

    @Autowired
    private CarroRepository carroRepository;

    public List<Carro> findAll() {
        return carroRepository.findAll();
    }

    public Optional<Carro> get(Long id) {
        return carroRepository.findById(id);
    }

    public List<Carro> getByTipo(String tipo) {
        return carroRepository.findByTipo(tipo);
    }

    public void save(Carro carro) {
        carroRepository.save(carro);
    }

    public void update(Long id, Carro carro) {
        carro.setId(id);

        carroRepository.save(carro);
    }

    public void delete(Long id) {
        carroRepository.deleteById(id);
    }
}
