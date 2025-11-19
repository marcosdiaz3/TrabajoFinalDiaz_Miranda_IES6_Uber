package ar.edu.ies6.tpfinaldiazmirada.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.ies6.tpfinaldiazmirada.model.Viaje;
import ar.edu.ies6.tpfinaldiazmirada.service.UsuarioService;
import ar.edu.ies6.tpfinaldiazmirada.service.VehiculoService;
import ar.edu.ies6.tpfinaldiazmirada.service.ViajeService;

@Controller
public class ViajeController {

    @Autowired
    ViajeService viajeService;

    @Autowired
    VehiculoService vehiculoService;

    @Qualifier("servicioUsuarioMySQL")
    @Autowired
    UsuarioService usuarioService;

    // FORMULARIO VIAJE
    @GetMapping("/viaje/{patente}")
    public ModelAndView getViaje(@PathVariable(name = "patente") String patente) throws Exception {
        ModelAndView carrito = new ModelAndView("viaje");

        Viaje viajeNuevo = viajeService.crearNuevoViaje();
        viajeNuevo.setVehiculo(vehiculoService.buscarUnVehiculo(patente));

        carrito.addObject("nuevoViaje", viajeNuevo);
        carrito.addObject("lista", usuarioService.listarUsuarioActivos());

        return carrito;
    }

    // GUARDAR VIAJE
    @PostMapping("/guardarViaje")
    public ModelAndView guardarViaje(@ModelAttribute("nuevoViaje") Viaje viajeParaGuardar) {

        ModelAndView modelAndView = new ModelAndView("index");

        try {
            viajeService.agregarViaje(viajeParaGuardar);
        } catch (Exception e) {
            modelAndView.addObject("errorViaje", "Error al guardar el viaje: " + e.getMessage());
        }

        return modelAndView;
    }

    // MOSTRAR PRECIO FINAL
  @GetMapping("/viaje/precio/{id}")
public ModelAndView calcularPrecio(@PathVariable Integer id) throws Exception {

    ModelAndView vista = new ModelAndView("precioViaje");

    Viaje viaje = viajeService.buscarViaje(id);

    // Intentamos obtener el tipo del vehículo sin importar cómo se llame el getter
    String tipo;

    try {
        // Si existe getTipoVehiculo()
        tipo = viaje.getVehiculo().getTipoVehiculo();
    } catch (Exception e) {
        // Si existe getTipoDeVehiculo()
        tipo = viaje.getVehiculo().getTipoVehiculo();
    }

    double precioFinal = viajeService.calcularPrecioFinal(viaje, tipo);

    vista.addObject("viaje", viaje);
    vista.addObject("precioFinal", precioFinal);

    return vista;
}

}
