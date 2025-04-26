package com.usco.edu.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Seccion {
	
	private Integer codigo;
	private String nombre;
	private Integer orden;
	private Integer seccionTipoCodigo;
	private String seccionTipoNombre;
	private Integer estado;

}