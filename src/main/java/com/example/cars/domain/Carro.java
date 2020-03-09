package com.example.cars.domain;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
public class Carro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nome;

    @Column
    private String descricao;

    @Column(name = "url_foto")
    private String urlFoto;

    @Column(name = "url_video")
    private String urlVideo;

    @Column
    private String tipo;

}
