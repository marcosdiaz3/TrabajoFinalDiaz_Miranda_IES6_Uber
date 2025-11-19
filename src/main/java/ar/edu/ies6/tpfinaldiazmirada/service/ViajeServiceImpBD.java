package ar.edu.ies6.tpfinaldiazmirada.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.ies6.tpfinaldiazmirada.model.Viaje;
import ar.edu.ies6.tpfinaldiazmirada.model.Vehiculo;
import ar.edu.ies6.tpfinaldiazmirada.repository.ViajeRepository;

@Service
public class ViajeServiceImpBD implements ViajeService {

    @Autowired
    ViajeRepository viajeRepository;

    @Autowired
    Viaje nuevoViaje;

    @Override
    public void borrarViaje(Integer id) throws Exception {
        Viaje viajeBorrar = viajeRepository.findById(id)
                .orElseThrow(() -> new Exception("Viaje no encontrado"));
        viajeBorrar.setEstado(false);
        viajeRepository.save(viajeBorrar);
    }

    @Override
    public void agregarViaje(Viaje viaje) {
        viaje.setEstado(true);
        viajeRepository.save(viaje);
    }

    @Override
    public void modificarViaje(Viaje viaje) {
        viajeRepository.save(viaje);
    }

    @Override
    public List<Viaje> listarViajes() {
        return (List<Viaje>) viajeRepository.findAll();
    }

    @Override
    public Viaje buscarViaje(Integer id) throws Exception {
        return viajeRepository.findById(id)
                .orElseThrow(() -> new Exception("Viaje no encontrado"));
    }

    @Override
    public Viaje crearNuevoViaje() {
        return nuevoViaje;
    }

    @Override
    public List<Viaje> listarViajesActivos() {
        return viajeRepository.findByEstado(true);
    }

   
    @Override
public double calcularPrecioFinal(Viaje viaje, String tipo) {

    // Ejemplo básico (ajustá según tu lógica)
    double precioBase = viaje.getCostoViaje();

    switch (tipo.toUpperCase()) {
        case "AUTO":
            return precioBase * 1.1;
        case "CAMIONETA":
            return precioBase * 1.2;
        case "MOTO":
            return precioBase * 1.05;
        default:
            return precioBase;
    }
}


}
