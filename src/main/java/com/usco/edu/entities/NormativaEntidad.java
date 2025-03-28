package com.usco.edu.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NormativaEntidad {
	
	private Integer codigo;
	private String nombre;
	private Integer normaEntidadTipoCodigo;
	private String normaEntidadTipoNombre;
	private Integer estado;

}
