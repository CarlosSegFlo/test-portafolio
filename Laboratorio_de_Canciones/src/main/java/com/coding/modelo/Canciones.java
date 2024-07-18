package com.coding.modelo;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="canciones")
public class Canciones {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message="Por favor ingresa el nombre de la cancion.")
	private String nombre;
	
	@NotBlank(message="Por favor ingresa el genero de la cancion.")
	private String genero;
	
	@Size( min=5,message="Por favor cree una contraseña.")
	private String letra;
	
	@Column(name="colaboraciones")
	private Integer colaboraciones;
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_usuario")
    private Usuario usuario;
	
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "usuarios_colaboradores", 
        joinColumns = @JoinColumn(name = "canciones_id"), 
        inverseJoinColumns = @JoinColumn(name = "usuarios_id")
    )
    private List<Usuario> colaboradores;
	
	@Temporal(TemporalType.DATE)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;
	
	@Temporal(TemporalType.DATE)
	@Column(name="fecha_actualizacion")
	private Date fechaActualizacion;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getLetra() {
		return letra;
	}

	public void setLetra(String letra) {
		this.letra = letra;
	}
	
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaActualizacion() {
		return fechaActualizacion;
	}

	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}
	public Integer getColaboraciones() {
		    if (colaboradores != null) {
		        return colaboradores.size();
		    } else {
		        return 1;
		    }
	}

	public void setColaboraciones(Integer colaboraciones) {
		this.colaboraciones = colaboraciones;
	}

	public List<Usuario> getColaboradores() {
		return colaboradores;
	}

	public void setColaboradores(List<Usuario> colaboradores) {
		this.colaboradores = colaboradores;
	}

	
}
