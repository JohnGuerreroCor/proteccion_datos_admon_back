package com.usco.edu.entities;

import java.sql.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Normativa {
	
	private Integer codigo;
	private String numero;
	private String nombre;
	private Integer normativaExpideCodigo;
	private Integer normativaEntidadTipoCodigo;
	private String normativaEntidadTipoNombre;
	private Integer normativaEntidadCodigo;
	private String normativaEntidadNombre;
	private Integer normativaTipoCodigo;
	private String normativaTipoNombre;
	private Integer normativaMedioCodigo;
	private String normativaMedioNombre;
	private String url;
	private String anexo;
	private Date fechaCreacion;
	private Date fechaVigencia;
	private Integer deroga;
	private String observacion;
	private Integer estado;

}
