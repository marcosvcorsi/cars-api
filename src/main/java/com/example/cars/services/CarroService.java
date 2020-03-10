package com.example.cars.services;

import com.example.cars.domain.Carro;
import com.example.cars.domain.dto.CarroDTO;
import com.example.cars.repositories.CarroRepository;
import javassist.NotFoundException;
import org.modelmapper.internal.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarroService {

    @Autowired
    private CarroRepository carroRepository;

    public List<CarroDTO> findAll() {
        return carroRepository.findAll().stream().map(CarroDTO::create).collect(Collectors.toList());
    }

    public Optional<CarroDTO> get(Long id) {
        return carroRepository.findById(id).map(CarroDTO::create);
    }

    public List<CarroDTO> getByTipo(String tipo) {
        return carroRepository.findByTipo(tipo).stream().map(CarroDTO::create).collect(Collectors.toList());
    }

    public CarroDTO save(Carro carro) {
        Assert.isNull(carro.getId());

        carroRepository.save(carro);

        return CarroDTO.create(carro);
    }

    public CarroDTO update(Long id, Carro carro) {
        Assert.notNull(id);

        carro.setId(id);

        carroRepository.save(carro);

        return CarroDTO.create(carro);
    }

    public void delete(Long id) {
        Optional<Carro> carro = carroRepository.findById(id);

        if(!carro.isPresent()) {
            throw new IllegalArgumentException();
        }

        carroRepository.deleteById(id);
    }
}
