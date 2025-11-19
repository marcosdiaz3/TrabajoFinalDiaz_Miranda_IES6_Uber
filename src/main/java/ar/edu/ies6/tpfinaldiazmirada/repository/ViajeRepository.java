package ar.edu.ies6.tpfinaldiazmirada.repository;
import ar.edu.ies6.tpfinaldiazmirada.model.*; //llama hace el crud del model Viaje
import org.springframework.data.jpa.repository.JpaRepository; //llama a la librari para hacer la conexion de java y mysql
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
//integer por el ID
public interface ViajeRepository extends JpaRepository <Viaje, Integer> {
//trae(devuelve) Viajes activos
 public List<Viaje> findByEstado(boolean estado); 
    
}     

