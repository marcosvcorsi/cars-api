package com.example.cars;

import com.example.cars.domain.Carro;
import com.example.cars.domain.dto.CarroDTO;
import com.example.cars.services.CarroService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
		CarroDTO carroDTO = carroService.get(11L);

		assertNotNull(carroDTO);
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

		CarroDTO carroLoaded = carroService.get(carroDTO.getId());
		assertNotNull(carroLoaded);

		assertEquals(carro.getNome(), carroLoaded.getNome());
		assertEquals(carro.getTipo(), carroLoaded.getTipo());
	}
}
