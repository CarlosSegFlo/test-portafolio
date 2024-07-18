package com.coding.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.coding.modelo.LoginUsuario;
import com.coding.modelo.Usuario;
import com.coding.servicios.ServiciosUsuario;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;



@Controller
public class ControladorUsuario {
	
	private final ServiciosUsuario servicioUsuario;
	
	
	public ControladorUsuario(ServiciosUsuario servicioUsuario) {
		super();
		this.servicioUsuario = servicioUsuario;
	}
	@GetMapping({"/", "/login"})
	public String inicio(@ModelAttribute("usuario") Usuario usuario,
			 			 @ModelAttribute("loginUsuario") LoginUsuario loginUsuario) {
		return "inicio.jsp";
	}
	@PostMapping("/registrar/usuario")
	public String procesaRegistrarUsuario(@Valid @ModelAttribute("usuario") Usuario usuario,
										  BindingResult validaciones,
										  @ModelAttribute("loginUsuario") LoginUsuario loginUsuario,
										  HttpSession sesion) {
		validaciones = this.servicioUsuario.validarRegistro(validaciones, usuario);
		if(validaciones.hasErrors()) {
			return "inicio.jsp";
		}
		
		Usuario usuarioCreado = this.servicioUsuario.insertarUsuario(usuario);
		sesion.setAttribute("id_usuario", usuarioCreado.getId());
		sesion.setAttribute("nombre_usuario", usuarioCreado.getNombre());
		
		return "redirect:/home";
	}
	@PostMapping("/procesa/login")
	public String procesaLogin(@Valid @ModelAttribute("loginUsuario") LoginUsuario loginUsuario,
							   BindingResult validaciones,
							   @ModelAttribute("usuario") Usuario usuario,
							   HttpSession sesion) {
		validaciones = this.servicioUsuario.validarLogin(validaciones, loginUsuario);
		if(validaciones.hasErrors()) {
			return "inicio.jsp";
		}
		
		Usuario usuarioActual = this.servicioUsuario.obtenerPorCorreo(loginUsuario.getUsuarioCorreo());
		sesion.setAttribute("id_usuario", usuarioActual.getId());
		sesion.setAttribute("nombre_usuario", usuarioActual.getNombre());
		
		return "redirect:/home";
	}
	@GetMapping("/logout")
	public String procesaLogount(HttpSession sesion) {
		sesion.invalidate();
		return "redirect:/login";
	}
}
