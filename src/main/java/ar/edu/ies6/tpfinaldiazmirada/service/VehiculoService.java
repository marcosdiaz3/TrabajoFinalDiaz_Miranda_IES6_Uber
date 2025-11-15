package ar.edu.ies6.tpfinaldiazmirada.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.ies6.tpfinaldiazmirada.model.Vehiculo;

@Service
public interface VehiculoService {

    // Declaracion de metodos
    // Nominativo solo nombre
    // Borrar - Agregar - Modificar - Listar - Buscar Alumno

    public void borrarVehiculo (String id) throws Exception;
    public void agregarVehiculo (Vehiculo vehiculo);
    public void modificarVehiculo (Vehiculo vehiculo);
    public List<Vehiculo> listarVehiculo ();
    public Vehiculo buscarUnVehiculo (String patente) throws Exception;
    public Vehiculo buscarUnVehiculoPorNombre (String nombre);
    public Vehiculo crearNuevoVehiculo ();
    public List<Vehiculo> listarTodosVehiculosActivos();

}
