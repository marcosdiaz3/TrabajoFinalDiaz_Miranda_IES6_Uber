package ar.edu.ies6.tpfinaldiazmirada.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ar.edu.ies6.tpfinaldiazmirada.model.Conductor;
import ar.edu.ies6.tpfinaldiazmirada.repository.ConductorRepository;

@Service
@Qualifier("servicioConductorBD")
public class ConductorServiceImpBD implements ConductorService {

    @Autowired
    Conductor nuevoConductor;

    @Autowired
    ConductorRepository conductorRepository;

    @Override
    public void borrarConductor(String id) throws Exception {
        //alumnoRepository.deleteById(dni);
        Conductor conductorBorrar = conductorRepository.findById(id).orElseThrow(()-> new Exception("conductor no encontrado"));
        conductorBorrar.setEstado(false);
        conductorRepository.save(conductorBorrar);
    }

    @Override
    public void agregarConductor(Conductor conductor) {
        conductor.setEstado(true);
        conductorRepository.save(conductor);
    }

    @Override
    public void modificarConductor(Conductor conductor) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'modificarAlumno'");
    }

    @Override
    public List<Conductor> listarConductor() {
        return (List<Conductor>) conductorRepository.findAll(); 
    }

    @Override
    public Conductor buscarUnConductor(String id) throws Exception {
        return conductorRepository.findById(id).orElseThrow(()-> new Exception("Conductor no encontrado"));
    }

    @Override
    public Conductor buscarUnConductorPorNombre(String nombre) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarUnAlumnoPorNombre'");
    }

    @Override
    public Conductor crearNuevoConductor() {
        return nuevoConductor;
    }

    @Override
    public List<Conductor> listarTodosConductoresActivos() {
        return conductorRepository.findByEstado(true);
    }
}
