package com.example.cars;

import com.example.cars.domain.Carro;
import com.example.cars.domain.dto.CarroDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CarroAPITest {

    @Autowired
    private TestRestTemplate rest;

    private ResponseEntity<CarroDTO> get(String url) {
        return rest.getForEntity(url, CarroDTO.class);
    }

    private ResponseEntity<List<CarroDTO>> getAll(String url) {
        return rest.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<CarroDTO>>() {
        });
    }

    @Test
    public void getAllCarsTest() {
        ResponseEntity<List<CarroDTO>> response = getAll("/api/v1/cars");
        List<CarroDTO> carroDTOList = response.getBody();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(carroDTOList);
    }

    @Test
    public void getAllCarsByTipoTest() {
        assertEquals(10, Objects.requireNonNull(getAll("/api/v1/cars/tipo/classicos").getBody()).size());
        assertEquals(10, Objects.requireNonNull(getAll("/api/v1/cars/tipo/esportivos").getBody()).size());
        assertEquals(10, Objects.requireNonNull(getAll("/api/v1/cars/tipo/luxo").getBody()).size());
    }

    @Test
    public void getAllCarsByTipoNoContentTest() {
        assertEquals(HttpStatus.NO_CONTENT, getAll("/api/v1/cars/tipo/unknow").getStatusCode());
    }

    @Test
    public void getOneCarTest() {
        ResponseEntity<CarroDTO> response = get("/api/v1/cars/11");
        CarroDTO carroDTO = response.getBody();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assert carroDTO != null;
        assertEquals("Ferrari FF", carroDTO.getNome());
    }

    @Test
    public void getOneCarNotFoundTest() {
        ResponseEntity<CarroDTO> response = get("/api/v1/cars/1000");
        CarroDTO carroDTO = response.getBody();

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(carroDTO);
    }

    @Test
    public void postCarTest() {
        Carro carro = new Carro();
        carro.setNome("Porshe");
        carro.setTipo("esportivos");

        ResponseEntity<?> response = rest.postForEntity("/api/v1/cars", carro, null);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        String location = Objects.requireNonNull(response.getHeaders().get("location")).get(0);
        CarroDTO carroDTO = get(location).getBody();

        assertNotNull(carroDTO);
        assertEquals("Porshe", carroDTO.getNome());
        assertEquals("esportivos", carroDTO.getTipo());
    }

    @Test
    public void deleteCarTest() {
        rest.delete("/api/v1/cars/11");

        ResponseEntity<CarroDTO> response = get("/api/v1/cars/11");
        CarroDTO carroDTO = response.getBody();

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(carroDTO);
    }
}
