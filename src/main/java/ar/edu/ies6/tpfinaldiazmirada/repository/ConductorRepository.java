package ar.edu.ies6.tpfinaldiazmirada.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.ies6.tpfinaldiazmirada.model.Conductor;

@Repository
public interface ConductorRepository extends CrudRepository<Conductor,Integer> {

    public List<Conductor> findByEstado (Boolean estado);


}
