package com.usco.edu.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Item {
	
	private Integer codigo;
	private String nombre;
	private Integer estado;

}
