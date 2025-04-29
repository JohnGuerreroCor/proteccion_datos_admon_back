package com.usco.edu.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Item {
	
	private Integer codigo;
	private String contenido;
	private Integer seccionCodigo;
	private String seccionNombre;
	private Integer estado;

}
