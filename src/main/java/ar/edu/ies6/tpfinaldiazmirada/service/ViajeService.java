package ar.edu.ies6.tpfinaldiazmirada.service;

import java.util.List;
import ar.edu.ies6.tpfinaldiazmirada.model.Viaje;
import ar.edu.ies6.tpfinaldiazmirada.model.Vehiculo;

public interface ViajeService {

    public void borrarViaje(Integer id) throws Exception; 
    public void agregarViaje(Viaje viaje);
    public void modificarViaje(Viaje viaje);
    public List<Viaje> listarViajes();
    public Viaje buscarViaje(Integer id) throws Exception;
    public Viaje crearNuevoViaje();
    public List<Viaje> listarViajesActivos();

    public double calcularPrecioFinal(Viaje viaje, String tipo);

}
