package ar.edu.ies6.tpfinaldiazmirada.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.ies6.tpfinaldiazmirada.model.Vehiculo;

@Repository
public interface VehiculoRepository extends CrudRepository<Vehiculo,String> {
    public List<Vehiculo> findByEstado (Boolean estado);

}
