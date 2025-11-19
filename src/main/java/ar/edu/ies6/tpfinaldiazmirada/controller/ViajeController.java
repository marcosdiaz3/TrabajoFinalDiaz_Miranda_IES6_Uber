package ar.edu.ies6.tpfinaldiazmirada.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.ies6.tpfinaldiazmirada.model.Usuario;
import ar.edu.ies6.tpfinaldiazmirada.model.Viaje;
import ar.edu.ies6.tpfinaldiazmirada.service.UsuarioService;
import ar.edu.ies6.tpfinaldiazmirada.service.VehiculoService;
import ar.edu.ies6.tpfinaldiazmirada.service.ViajeService;

public class ViajeController {

    @Autowired
    ViajeService viajeService;

    @Autowired
    VehiculoService vehiculoService;

    @Qualifier("servicioUsuarioMySQL")
    @Autowired
    UsuarioService usuarioService;

//vehiculo
@GetMapping("/viaje/{patente}")
public ModelAndView getViaje(@PathVariable(name = "patente") String patente) throws Exception {
    ModelAndView carrito = new ModelAndView("viaje");
    Viaje viajeNuevo = viajeService.crearNuevoViaje();
    viajeNuevo.setVehiculo(vehiculoService.buscarUnVehiculo(patente));
    carrito.addObject("nuevoViaje", viajeNuevo);
    carrito.addObject("lista", usuarioService.listarUsuarioActivos());
    return carrito;
}


    @PostMapping("/guardarMateria")
    public ModelAndView guardarViaje(@ModelAttribute("nuevoViaje") Viaje viajeParaGuardar) {
        System.out.println("estoy ingresando al metodo guardar");
        ModelAndView modelAndView = new ModelAndView("index");

        try {
            viajeService.agregarViaje(viajeParaGuardar);
        } catch (Exception e) {
            modelAndView.addObject("errorViaje", "Error al guardar el viaje: " + e.getMessage());
        }

        System.out.println("estoy saliendo al metod guardar");
        return modelAndView;
    }

    @GetMapping("/viaje/precio/{id}")
    public ModelAndView calcularPrecio(@PathVariable Integer id) throws Exception {
        ModelAndView vista = new ModelAndView("precioViaje");
        Viaje viaje = viajeService.buscarViaje(id);
        double precioFinal = viajeService.calcularPrecioFinal(viaje);
        vista.addObject("viaje", viaje);
        vista.addObject("precioFinal", precioFinal);
        return vista;
    }
}
