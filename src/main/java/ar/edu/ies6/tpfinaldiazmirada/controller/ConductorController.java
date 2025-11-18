package ar.edu.ies6.tpfinaldiazmirada.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.ies6.tpfinaldiazmirada.model.Conductor;
import ar.edu.ies6.tpfinaldiazmirada.service.ConductorService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ConductorController {

    @Qualifier("servicioConductorBD")
    @Autowired
    ConductorService conductorService;

     @GetMapping("/conductor")
    public ModelAndView getConductor() {
        ModelAndView carrito = new ModelAndView("conductor");

        carrito.addObject("nuevoConductor", conductorService.crearNuevoConductor());
        carrito.addObject("band", false);

        return carrito;
    }
    

     @PostMapping("/guardarConductor")
    public ModelAndView saveConductor(@Valid @ModelAttribute("nuevoConductor") Conductor conductorParaGuardar, BindingResult result) {


        ModelAndView modelAndView = new ModelAndView();

        if (result.hasErrors()) {

            modelAndView.setViewName("conductor");
            modelAndView.addObject("nuevoConductor", conductorParaGuardar);
        } else {

            try {
                conductorService.agregarConductor(conductorParaGuardar);
                modelAndView.setViewName("listaConductores");

                modelAndView.addObject("correcto", "El Conductor se Guardo con exito");

            } catch (Exception e) {
                modelAndView.addObject("incorrecto", "El Conductor no se Guardo" + e.getMessage());
            }

            modelAndView.addObject("clista", conductorService.listarTodosConductoresActivos());
            System.out.println("estoy saliendo - Conductor");
            
        }

        return modelAndView;

        
    }



    @GetMapping("/eliminarConductor/{dni}")
    public ModelAndView eliminarConductor(@PathVariable("dni") String dni) throws Exception {
        ModelAndView eliminarConductor = new ModelAndView("listaConductores");
        conductorService.borrarConductor(dni);
        eliminarConductor.addObject("clista", conductorService.listarTodosConductoresActivos());

        return eliminarConductor;
    } 


    @GetMapping("/modificarConductor/{dni}")
    public ModelAndView buscarConductorParaModificar(@PathVariable("dni") String dni) throws Exception{
        ModelAndView carritoParaModificarConductor = new ModelAndView("conductor");
        carritoParaModificarConductor.addObject("nuevoConductor", conductorService.buscarUnConductor(dni));
        carritoParaModificarConductor.addObject("band", true);

        return carritoParaModificarConductor;

    }

    @PostMapping("/modificarConductor")
    public ModelAndView modificarConductor(@ModelAttribute("nuevoConductor") Conductor conductorModificado) {
        ModelAndView listadoEditado = new ModelAndView("listaConductores");
        conductorService.agregarConductor(conductorModificado);
        listadoEditado.addObject("clista", conductorService.listarTodosConductoresActivos());

        
        return listadoEditado;

    }

    @GetMapping("/listarConductores")
    public ModelAndView listarConductoresActivos() {
        ModelAndView carritoParaMostrarConductores = new ModelAndView("listaConductores");
        carritoParaMostrarConductores.addObject("clista", conductorService.listarTodosConductoresActivos());

        return carritoParaMostrarConductores;
    }

}
