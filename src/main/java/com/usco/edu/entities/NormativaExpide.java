package com.usco.edu.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NormativaExpide {
	
	private Integer codigo;
	private Integer normativaEntidaCodigo;
	private String normativaEntidaNombre;
	private Integer normativaTipoCodigo;
	private String normativaTipoNombre;
	private Integer estado;

}
