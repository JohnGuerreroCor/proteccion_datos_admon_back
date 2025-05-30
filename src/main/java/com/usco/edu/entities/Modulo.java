package com.usco.edu.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Modulo {
	
	private Integer codigo;
	private String nombre;
	private String descripcion;
	private String url;
	private Integer sistemaCodigo;
	private String sistemaNombre;
	private Integer estado;

}
