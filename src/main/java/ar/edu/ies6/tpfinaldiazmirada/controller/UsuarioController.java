package ar.edu.ies6.tpfinaldiazmirada.controller;


import java.util.ArrayList;
import java.util.List;

import ar.edu.ies6.tpfinaldiazmirada.model.Usuario;
import ar.edu.ies6.tpfinaldiazmirada.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.validation.Valid;


@Controller

public class UsuarioController {

    // Lista de alumnos
    // List<Alumno> listadoDeAlumnos = new ArrayList<>();

    // CORRECCIÓN: inyectar AlumnoService
    
    @Qualifier ("servicioUsuarioMysql")
    @Autowired
    UsuarioService usuarioService;



    @GetMapping ("/usuario")
    public ModelAndView getUsuario() {
        ModelAndView carrito = new ModelAndView("usuario");
        // Se asegura que alumnoService no sea null
        carrito.addObject("nuevoUsuario", usuarioService.crearNuevoUsuario());
        carrito.addObject("band", false);
        return carrito;
    }


    @PostMapping("/guardarUsuario")
    public ModelAndView guardarUsuario (@Valid @ModelAttribute("nuevoUsuario") Usuario usuarioParaGuardar,BindingResult result
    ) {
        
        System.out.println("estoy ingresando al metodo de guardar");
        ModelAndView modelAndView = new ModelAndView();
        if (result.hasErrors()) {
            modelAndView.setViewName("usuario");
            modelAndView.addObject("nuevoUsuario", usuarioParaGuardar);
        } else {
            try {
                usuarioService.agregarUsuario(usuarioParaGuardar);
                modelAndView.setViewName("listaUsuario");
                modelAndView.addObject("correcto", "usuario registrado con éxito!");
            } catch (Exception e) {
                // Mensaje de ERROR
                modelAndView.addObject("errorUsuario", "Error al guardar el usuario: " + e.getMessage());
            }
            modelAndView.addObject("lista", usuarioService.listarUsuarioActivos());
            System.out.println("estoy saliendo al metodo de guardar");
        }

        return modelAndView;
    }

        //estructura de datos que permite alacenar datos
        //guardar alumno
      //listadoDeAlumnos.add(alumnoParaGuardar);
      // llama al servicio y realiza las acciones de los metodos
  
      
        //System.out.println(listadoAlumn);
    


//Eliminar
@GetMapping("/eliminarUsuario/{dni}")
public ModelAndView eliminarUsuario (@PathVariable   String dni) throws Exception {
    ModelAndView CarritoEliminar = new ModelAndView("listaUsuario");
    usuarioService.borrarUsuario(dni);
    CarritoEliminar.addObject("lista", usuarioService.listarUsuarioActivos());
return CarritoEliminar;

}


//modificar
@GetMapping("/modificarUsuario/{dni}")
public ModelAndView buscarUsuarioParaModificar(@PathVariable String dni) throws Exception {

ModelAndView CariitoModificaUsuario = new ModelAndView("usuario"); 
CariitoModificaUsuario.addObject("nuevoUsuario", usuarioService.buscarUsuario(dni));
CariitoModificaUsuario.addObject("band", true);
return CariitoModificaUsuario;
}



@PostMapping ("/modificarUsuario")
public ModelAndView modificarUsuario (@ModelAttribute("nuevoUsuario") Usuario unUsuarioModificado) {
ModelAndView listaEditado = new ModelAndView("listaUsuario");
usuarioService.agregarUsuario(unUsuarioModificado);
listaEditado.addObject("lista", usuarioService.listarUsuarioActivos());
return listaEditado;

}


@GetMapping ("/listaUsuario")
public ModelAndView listarUsuarioActivos() {
ModelAndView carritoParaMostrarUsuario = new ModelAndView("listaUsuario");
carritoParaMostrarUsuario.addObject("lista", usuarioService.listarUsuarioActivos());

return carritoParaMostrarUsuario;

}

}



    

