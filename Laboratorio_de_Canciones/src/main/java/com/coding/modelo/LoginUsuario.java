package com.coding.modelo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


public class LoginUsuario {
	@NotBlank(message="Por favor proporciona tu correo.")
	@Email(message="Por favor proporciona tu correo")
	private String usuarioCorreo;
	@NotBlank(message="Por favor proporciona tu contraseña.")
	private String usuarioContraseña;
	
	public LoginUsuario() {}

	public String getUsuarioCorreo() {
		return usuarioCorreo;
	}

	public void setUsuarioCorreo(String usuarioCorreo) {
		this.usuarioCorreo = usuarioCorreo;
	}

	public String getUsuarioContraseña() {
		return usuarioContraseña;
	}

	public void setUsuarioContraseña(String usuarioContraseña) {
		this.usuarioContraseña = usuarioContraseña;
	}
}
