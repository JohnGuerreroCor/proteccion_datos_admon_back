package com.usco.edu.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Uaa {
	
    private Long codigo;
    private UaaTipo uaaTipo;
    private String nombre;
    private String nombreCorto;
    private String nombreImpresion;
    private Sede sede;
    private int uaa_dependencia;
    private String telefono;
    private String email;
    private String direccion;
    private String pagina;
    private String jefe;
    private String acronimo;
    private String centro_costos;
    private int estado;

}
