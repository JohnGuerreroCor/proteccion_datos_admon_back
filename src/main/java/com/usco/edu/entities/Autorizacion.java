package com.usco.edu.entities;

import java.sql.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Autorizacion {
	
	private Integer codigo;
	private String titulo;
	private Integer normativaCodigo;
	private String normativaNumero;
	private String normativaTipoNombre;
	private Date normativaFechaCreacion;
	private String version;
	private String descripcion;
	private Date fechaCreacion;
	private Integer anexo;
	private Integer estado;

}
