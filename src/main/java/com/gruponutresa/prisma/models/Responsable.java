package com.gruponutresa.prisma.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.gruponutresa.prisma.constants.Valores;

@Entity
@Table(name="responsable")
@NamedQuery(name = Valores.ENCONTRAR_RESPONSABLE, query = "SELECT r FROM Responsable r")

public class Responsable {
	
	@Id
	private int id;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="usuario")
	private String usuario;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

}
