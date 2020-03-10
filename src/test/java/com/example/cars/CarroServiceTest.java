package com.example.cars;

import com.example.cars.domain.Carro;
import com.example.cars.domain.dto.CarroDTO;
import com.example.cars.services.CarroService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CarroServiceTest {

	@Autowired
	private CarroService carroService;

	@Test
	public void findAllCarsTest() {
		List<CarroDTO> carroList = carroService.findAll();

		assertEquals(31, carroList.size());
	}

	@Test
	public void findOneCarTest() {
		Optional<CarroDTO> optional = carroService.get(11L);

		assertTrue(optional.isPresent());
	}

	@Test
	public void findAllByTipoTest() {
		assertEquals(10, carroService.getByTipo("classicos").size());
		assertEquals(10, carroService.getByTipo("esportivos").size());
		assertEquals(10, carroService.getByTipo("luxo").size());
	}

	@Test
	public void saveCarTest() {
		Carro carro = new Carro();
		carro.setNome("Ferrari");
		carro.setTipo("Esportivos");

		CarroDTO carroDTO = carroService.save(carro);

		Optional<CarroDTO> optional = carroService.get(carroDTO.getId());
		assertTrue(optional.isPresent());

		CarroDTO carroLoaded = optional.get();

		assertEquals(carro.getNome(), carroLoaded.getNome());
		assertEquals(carro.getTipo(), carroLoaded.getTipo());
	}
}
