package ar.edu.ies6.tpfinaldiazmirada.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ar.edu.ies6.tpfinaldiazmirada.model.Vehiculo;
import ar.edu.ies6.tpfinaldiazmirada.repository.VehiculoRepository;

@Service
@Qualifier("servicioVehiculoBD")
public class VehiculoServiceImpBD implements VehiculoService {

    //Inyeccion de dependencia usando el singletone
    @Autowired
    Vehiculo nuevoVehiculo;

    @Autowired
    VehiculoRepository vehiculoRepository;

    @Override
    public void borrarVehiculo(String patente) throws Exception {
        Vehiculo vehiculoBorrar = vehiculoRepository.findById(patente).orElseThrow(()-> new Exception("vehiculo no encontrado"));
        vehiculoBorrar.setEstado(false);
        vehiculoRepository.save(vehiculoBorrar);
    }

    @Override
    public void agregarVehiculo(Vehiculo vehiculo) {
        vehiculo.setEstado(true);
        vehiculoRepository.save(vehiculo);
    }

    @Override
    public void modificarVehiculo(Vehiculo vehiculo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'modificarAlumno'");
    }

    @Override
    public List<Vehiculo> listarVehiculo() {
        return (List<Vehiculo>) vehiculoRepository.findAll(); 
    }

    @Override
    public Vehiculo buscarUnVehiculo(String patente) throws Exception {
        return vehiculoRepository.findById(patente).orElseThrow(()-> new Exception("vehiculo no encontrado"));
    }

    @Override
    public Vehiculo buscarUnVehiculoPorNombre(String nombre) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarUnAlumnoPorNombre'");
    }

    @Override
    public Vehiculo crearNuevoVehiculo() {
        return nuevoVehiculo;
    }

    @Override
    public List<Vehiculo> listarTodosVehiculosActivos() {
        return vehiculoRepository.findByEstado(true);
    }

}