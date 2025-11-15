package ar.edu.ies6.tpfinaldiazmirada.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository; 
import ar.edu.ies6.tpfinaldiazmirada.model.*;


//JpaReposiotory

@Repository
public interface UsuarioRepository extends CrudRepository <Usuario, String> {

         
    public List<Usuario> findByEstado (boolean estado);
    
}


