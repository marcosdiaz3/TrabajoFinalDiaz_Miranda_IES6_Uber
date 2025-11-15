package ar.edu.ies6.tpfinaldiazmirada.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.ies6.tpfinaldiazmirada.model.Conductor;

@Service
public interface ConductorService {

    public void borrarConductor (String id) throws Exception;
    public void agregarConductor (Conductor conductor);
    public void modificarConductor (Conductor conductor);
    public List<Conductor> listarConductor ();
    public Conductor buscarUnConductor (String dni) throws Exception;
    public Conductor buscarUnConductorPorNombre (String nombre);
    public Conductor crearNuevoConductor ();
    public List<Conductor> listarTodosConductoresActivos();

}
