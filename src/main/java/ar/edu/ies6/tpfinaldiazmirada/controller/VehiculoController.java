package ar.edu.ies6.tpfinaldiazmirada.controller;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

//import ar.edu.ies6.tpfinaldiazmirada.model.Conductor;
import ar.edu.ies6.tpfinaldiazmirada.model.Vehiculo;
//import ar.edu.ies6.tpfinaldiazmirada.service.ConductorService;
import ar.edu.ies6.tpfinaldiazmirada.service.VehiculoService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class VehiculoController {

    @Qualifier("servicioVehiculoBD")
    @Autowired
    VehiculoService vehiculoService;

    //@Autowired
    //@Qualifier("servicioConductorBD")
    //ConductorService conductorService;


    @GetMapping("/vehiculo")
    public ModelAndView getVehiculo() {
        ModelAndView carrito = new ModelAndView("vehiculo");

        carrito.addObject("nuevoVehiculo", vehiculoService.crearNuevoVehiculo());
        //carrito.addObject("clista", conductorService.listarTodosConductoresActivos());
        carrito.addObject("band", false);


        return carrito;
    }


   @PostMapping("/guardarVehiculo")
    public ModelAndView saveVehiculo(@Valid @ModelAttribute("nuevoVehiculo") Vehiculo vehiculoParaGuardar, BindingResult result, @RequestParam("dniConductor") String dniConductor) {

        System.out.println("estoy entrando - Vehiculo");
        ModelAndView modelAndView = new ModelAndView();

        if (result.hasErrors()) {

            modelAndView.setViewName("vehiculo");
            modelAndView.addObject("nuevoVehiculo", vehiculoParaGuardar);

            //modelAndView.addObject("clista", conductorService.listarTodosConductoresActivos());
        } else {

            try {
                vehiculoService.agregarVehiculo(vehiculoParaGuardar);
                modelAndView.setViewName("listaVehiculos");

                modelAndView.addObject("correcto", "El Vehiculo se Guardo con exito");

            } catch (Exception e) {
                modelAndView.addObject("incorrecto", "El Vehiculo no se Guardo" + e.getMessage());
            }

            // alumnoService.agregarAlumno(alumnoParaGuardar);
            modelAndView.addObject("lista", vehiculoService.listarTodosVehiculosActivos());
            System.out.println("estoy saliendo - Vehiculo");
            // return modelAndView;
        }

        return modelAndView;

    } 



    @GetMapping("/eliminarVehiculo/{patente}")
    public ModelAndView eliminarVehiculo(@PathVariable("patente") String patente) throws Exception {
        ModelAndView eliminarVehiculo = new ModelAndView("listaVehiculos");
        vehiculoService.borrarVehiculo(patente);
        eliminarVehiculo.addObject("lista", vehiculoService.listarTodosVehiculosActivos());

        return eliminarVehiculo;
    }

    @GetMapping("/modificarVehiculo/{patente}")
    public ModelAndView buscarVehiculoParaModificar(@PathVariable("patente") String patente) throws Exception {
        ModelAndView carritoParaModificarVehiculo = new ModelAndView("vehiculo");
        carritoParaModificarVehiculo.addObject("nuevoVehiculo", vehiculoService.buscarUnVehiculo(patente));
        carritoParaModificarVehiculo.addObject("band", true);

        //carritoParaModificarVehiculo.addObject("clista", conductorService.listarTodosConductoresActivos());

        return carritoParaModificarVehiculo;

    }

    @PostMapping("/modificarVehiculo")
    public ModelAndView modificarVehiculo(@ModelAttribute("nuevoVehiculo") Vehiculo vehiculoModificado) {
        ModelAndView listadoEditado = new ModelAndView("listaVehiculos");
        vehiculoService.agregarVehiculo(vehiculoModificado);
        listadoEditado.addObject("lista", vehiculoService.listarTodosVehiculosActivos());

        return listadoEditado;

    }

    @GetMapping("/listarVehiculos")
    public ModelAndView listarVehiculosActivos() {
        ModelAndView carritoParaMostrarVehiculos = new ModelAndView("listaVehiculos");
        carritoParaMostrarVehiculos.addObject("lista", vehiculoService.listarTodosVehiculosActivos());

        return carritoParaMostrarVehiculos;
    }

}
