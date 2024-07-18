package com.coding.controladores;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.coding.modelo.Canciones;
import com.coding.modelo.Usuario;
import com.coding.servicios.ServicioCanciones;
import com.coding.servicios.ServiciosUsuario;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class ControladorCanciones {

    private final ServiciosUsuario serviciosUsuarios;
    private final ServicioCanciones servicioCanciones;

    public ControladorCanciones(ServiciosUsuario serviciosUsuarios, ServicioCanciones servicioCanciones) {
        this.serviciosUsuarios = serviciosUsuarios;
        this.servicioCanciones = servicioCanciones;
    }

    @GetMapping("/home")
    public String home(Model modelo, HttpSession sesion) {
        if (sesion.getAttribute("id_usuario") == null) {
            return "redirect:/";
        }
        Long idUsuario = (Long) sesion.getAttribute("id_usuario");
        Usuario usuario = serviciosUsuarios.obtenerPorId(idUsuario);
        modelo.addAttribute("nombre_usuario", usuario.getNombre());

        List<Canciones> canciones = servicioCanciones.obtenerTodasCanciones();
        modelo.addAttribute("canciones", canciones);

        return "panelCancion.jsp";
    }

    @GetMapping("/songs/new")
    public String despliegaFormulario(@ModelAttribute("canciones") Canciones canciones) {
        return "formularioNuevaCancion.jsp";
    }

    @PostMapping("/registrar/cancion")
    public String agregarCancion(@Valid @ModelAttribute("canciones") Canciones canciones,
                                 BindingResult validaciones, HttpSession sesion) {

        if (validaciones.hasErrors()) {
            return "formularioNuevaCancion.jsp";
        }

        Long idUsuario = (Long) sesion.getAttribute("id_usuario");
        if (idUsuario == null) {
            return "redirect:/";
        }

        Usuario usuario = this.serviciosUsuarios.obtenerPorId(idUsuario);
        if (usuario == null) {
            return "redirect:/";
        }

        canciones.setUsuario(usuario);

        this.servicioCanciones.agregarCancion(canciones);

        return "redirect:/home";
    }

    @GetMapping("/songs/{id}")
    public String detallesCanciones(@PathVariable("id") Long id, HttpSession sesion, Model modelo) {
        if (sesion.getAttribute("id_usuario") == null) {
            return "redirect:/";
        }
        Canciones canciones = servicioCanciones.obtenerPorId(id);
        modelo.addAttribute("canciones", canciones);
        return "detallesCanciones.jsp";
    }

    @GetMapping("/songs/{id}/edit")
    public String editar(@PathVariable("id") Long id, Model modelo) {
        Canciones canciones = servicioCanciones.obtenerPorId(id);
        modelo.addAttribute("canciones", canciones);
        return "editarCanciones.jsp";
    }

    @PutMapping("/songs/{id}/edit")
    public String actualizar(@Valid @ModelAttribute("canciones") Canciones canciones, @PathVariable("id") Long id,
                             BindingResult validaciones, HttpSession sesion) {

        if (validaciones.hasErrors()) {
            return "formularioNuevaCancion.jsp";
        }

        Long idUsuario = (Long) sesion.getAttribute("id_usuario");

        if (idUsuario == null) {
            return "redirect:/";
        }

        Usuario usuario = serviciosUsuarios.obtenerPorId(idUsuario);
        if (usuario == null) {
            return "redirect:/";
        }

        Canciones cancionActual = this.servicioCanciones.obtenerPorId(id);

        // Verificar si el usuario actual es el creador de la canción
        if (!usuario.equals(cancionActual.getUsuario())) {
            List<Usuario> colaboradores = cancionActual.getColaboradores();
            colaboradores.add(usuario);
            cancionActual.setColaboradores(colaboradores);
        }

        this.servicioCanciones.actualizarCancion(cancionActual);
        return "redirect:/home";
    }

    @DeleteMapping("/delete/song/{id}")
    public String eliminarCanciones(@PathVariable("id") Long id) {
        servicioCanciones.eliminarPorId(id);
        return "redirect:/home";
    }
}
