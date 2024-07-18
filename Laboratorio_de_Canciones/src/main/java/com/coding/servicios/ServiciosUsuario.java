package com.coding.servicios;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.coding.modelo.LoginUsuario;
import com.coding.modelo.Usuario;
import com.coding.repositorio.RepositorioUsuario;

@Service
public class ServiciosUsuario {
		
		@Autowired
		private final RepositorioUsuario repositorioUsuario;
		
		public ServiciosUsuario(RepositorioUsuario repositorioUsuario) {
			super();
			this.repositorioUsuario = repositorioUsuario;
		}

		public BindingResult validarRegistro(BindingResult validaciones, Usuario usuario) {
			
			if(! usuario.getContraseña().equals(usuario.getConfirmarContraseña())) {
				validaciones.rejectValue("confirmarContraseña", "NoCoinciden", "Las contraseñas no coinciden.");
			}
			
			if(this.obtenerPorCorreo(usuario.getCorreo()) != null) {
				validaciones.rejectValue("correo", "Existente", "Este correo ya está en uso!");
			}
			
			return validaciones;
		}
		
		public BindingResult validarLogin(BindingResult validaciones, LoginUsuario loginUsuario) {
			Usuario usuarioActual = this.obtenerPorCorreo(loginUsuario.getUsuarioCorreo());
			if(usuarioActual == null) {
				validaciones.rejectValue("usuarioCorreo", "NoExistente", "Este usuario no existe");
			}
			else if(! BCrypt.checkpw(loginUsuario.getUsuarioContraseña(), usuarioActual.getContraseña())) {
				validaciones.rejectValue("usuarioContraseña", "NoExistente", "Credenciales no validas");
			}
			return validaciones;
		}
		
		public Usuario insertarUsuario(Usuario usuario) {
			String contraseñaEncriptada = BCrypt.hashpw(usuario.getContraseña(), BCrypt.gensalt());
			usuario.setContraseña(contraseñaEncriptada);
			return this.repositorioUsuario.save(usuario);
		}
		
		public Usuario obtenerPorCorreo(String correo) {
			return this.repositorioUsuario.getByCorreo(correo);
		}
		
		public Usuario obtenerPorId(Long id) {
			return this.repositorioUsuario.findById(id).orElse(null);
		}
		public List<Usuario> obtenerTodosUsuarios(){
			return this.repositorioUsuario.findAll();
		}
	}


