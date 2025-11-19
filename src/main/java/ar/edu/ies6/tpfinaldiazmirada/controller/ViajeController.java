package ar.edu.ies6.tpfinaldiazmirada.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.ies6.tpfinaldiazmirada.model.Vehiculo;
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

    @Qualifier("servicioUsuarioMysql")
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

        ModelAndView modelAndView = new ModelAndView("precioViaje");

        try {
            // 1. Buscar el vehículo real en la BD
        Vehiculo vehiculoReal = vehiculoService.buscarUnVehiculo(
                viajeParaGuardar.getVehiculo().getPatente()
        );

        // 2. Asociarlo al viaje
        viajeParaGuardar.setVehiculo(vehiculoReal);

        // 3. Copiar el tipo de vehículo EN el objeto Viaje
        viajeParaGuardar.setTipoDeVehiculo(vehiculoReal.getTipoDeVehiculo());

        // 4. Calcular el precio final usando los valores correctos
        double precioFinal = viajeService.calcularPrecioFinal(
                viajeParaGuardar.getTipViaje(),
                viajeParaGuardar.getTipoDeVehiculo()
        );

        // Guardarlo en el viaje antes de guardar en BD
        viajeParaGuardar.setPrecioFinal(precioFinal);

        // Guardar el viaje ya con el precio
        viajeService.agregarViaje(viajeParaGuardar);

        // ENVÍO DE OBJETOS A LA VISTA
        modelAndView.addObject("viaje", viajeParaGuardar);
        modelAndView.addObject("vehiculo", viajeParaGuardar.getVehiculo());
        modelAndView.addObject("precioFinal", precioFinal);

        } catch (Exception e) {
            modelAndView.addObject("errorViaje", "Error al guardar el viaje: " + e.getMessage());
        }

        return modelAndView;
    }

    // MOSTRAR PRECIO FINAL
  @GetMapping("/viaje/precio/{id}")
public ModelAndView calcularPrecio(@PathVariable Integer id) throws Exception {

    ModelAndView vista = new ModelAndView("precioViaje");

    //buscar el viaje por id
    Viaje viaje = viajeService.buscarViaje(id);

    // Intentamos obtener el tipo del vehículo sin importar cómo se llame el getter
    String tipo ="DESCONOCIDO";

    if (viaje.getVehiculo() != null && viaje.getVehiculo().getTipoDeVehiculo() != null) {

        // Si existe getTipoVehiculo()
        tipo = viaje.getVehiculo().getTipoDeVehiculo().name();
    }

    double precioFinal = viajeService.calcularPrecioFinal(null, null);

    vista.addObject("viaje", viaje);
    vista.addObject("precioFinal", precioFinal);

    return vista;
}

}
