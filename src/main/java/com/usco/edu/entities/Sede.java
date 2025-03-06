package com.usco.edu.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Sede {

    private Long codigo;
    private String nombre;
    private String nombreCorto;
    private String estado;

}
