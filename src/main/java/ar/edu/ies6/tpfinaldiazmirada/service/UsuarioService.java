package ar.edu.ies6.tpfinaldiazmirada.service;
import java.util.List;
import org.springframework.stereotype.Service;
import ar.edu.ies6.tpfinaldiazmirada.model.Usuario;


@Service
public interface UsuarioService {
    
    //declaracion de metodos
    //nominativo solo nombre


 public void borrarUsuario(String dni) throws Exception ; //void no devuelve nada
public void  agregarUsuario(Usuario usuario);
public void modificarUsuario(Usuario usuario);
public List<Usuario> listarUsuario();
public Usuario buscarUsuario(String dni) throws Exception ;
public Usuario crearNuevoUsuario();
public List<Usuario> listarUsuarioActivos();
}


