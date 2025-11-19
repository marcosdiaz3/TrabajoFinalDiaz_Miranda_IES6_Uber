package ar.edu.ies6.tpfinaldiazmirada.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ar.edu.ies6.tpfinaldiazmirada.model.*;
import ar.edu.ies6.tpfinaldiazmirada.repository.*;


@Service 
@Qualifier ("servicioViajeMysql")

public class ViajeServiceImpBD implements ViajeService{


    @Autowired
    Viaje newViaje;
    @Autowired
    ViajeRepository viajeRepository;
    
    
    
    @Override
    public void borrarViaje(Integer id) throws Exception {
        Viaje viajeBorrar = viajeRepository.findById(id).orElseThrow(()-> new Exception("viaje no encontrado"));
    viajeBorrar.setEstado(false);
    viajeRepository.save(viajeBorrar);
      //  alumnoRepository.deleteById(dni);
    }


    @Override
    public void agregarViaje(Viaje viaje) {
             viaje.setEstado(true);
    viajeRepository.save(viaje);
    }

    @Override
    public void modificarViaje(Viaje viaje) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'modificarViaje'");
    }
    @Override
    public List<Viaje> listarViajes() {
          return (List<Viaje>) viajeRepository.findAll();
    }


    @Override
    public Viaje buscarViaje(Integer id) throws Exception {
     return viajeRepository.findById(id).orElseThrow(()-> new Exception("Viaje no encontrado"));
    }
    
    
    @Override
    public Viaje crearNuevoViaje() {
         return newViaje;
    }

    @Override
    public List<Viaje> listarViajesActivos() {
         return viajeRepository.findByEstado(true);
    }


@Override
public double calcularPrecioFinal(Viaje viaje) {

    if (viaje.getTipViaje() == null) {
        return viaje.getCostoViaje();
    }

    switch (viaje.getTipViaje().name()) {
        case "BASE":
            return viaje.getCostoViaje();

        case "LUXE":
            return viaje.getCostoViaje() * 1.10;

        case "PREMIUM":
            return viaje.getCostoViaje() * 1.20;

        default:
            return viaje.getCostoViaje();
    }
}





    
    
}