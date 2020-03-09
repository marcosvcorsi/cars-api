package com.example.cars.domain.dto;

import com.example.cars.domain.Carro;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;

@Data
public class CarroDTO {

    private Long id;
    private String nome;
    private String descricao;
    private String urlFoto;
    private String urlVideo;
    private String tipo;

    public static CarroDTO create(Carro carro) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(carro, CarroDTO.class);
    }
}
