package com.inmobiliaria.InmoGestion.modelo;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Inmueble {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private Integer ambientes;
    private String descripcion;
    private String direccion;
    private String ciudad;
    private String provincia;
    @ManyToOne
    @JoinColumn(name = "fk_tipo_operacion")
    private TipoOperacion tipoOperacion;                   // alquiler / venta

    @ManyToOne
    @JoinColumn(name = "fk_categoria")
    private Categoria categoria;                       // casa - departamento / terreno - lote - chacra

    @OneToMany
    private List<Servicios> servicios;                 // Agua - Luz - Asfalto - Wifi - etc
    @OneToMany
    private List<Caracteristicas> caracteristicas;           // 2 dormitorios - 1 baño - cocina - comedor - living - lavadero - garage - piscina - patio - 2 depositos - canil para perros
    private BigDecimal precio;                      // mensual caso alquiler / contado precio compra

    @ElementCollection
    private List<String> listaImagenes;

}
