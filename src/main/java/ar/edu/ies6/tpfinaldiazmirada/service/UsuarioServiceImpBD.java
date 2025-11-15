package ar.edu.ies6.tpfinaldiazmirada.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ar.edu.ies6.tpfinaldiazmirada.model.*;
import ar.edu.ies6.tpfinaldiazmirada.repository.*;


@Service 
@Qualifier ("servicioUsuarioMysql")

public class UsuarioServiceImpBD implements UsuarioService{


    @Autowired
    Usuario newUsuario;
    @Autowired
    UsuarioRepository usuarioRepository;

    @Override

    public void borrarUsuario(String dni) throws Exception {
        Usuario usuarioBorrar = usuarioRepository.findById(dni).orElseThrow(()-> new Exception("usuario no encontrado"));
    usuarioBorrar.setEstado(false);
    usuarioRepository.save(usuarioBorrar);
      //  alumnoRepository.deleteById(dni);
    }

    @Override
    public void agregarUsuario(Usuario usuario) {
        usuario.setEstado(true);
    usuarioRepository.save(usuario);



    
    }

    @Override
    public void modificarUsuario(Usuario usuario) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'modificarAlumno'");
    }

    @Override
    public List<Usuario> listarUsuario() {
     return (List<Usuario>) usuarioRepository.findAll();
    }
//falta castear


    @Override
    public Usuario buscarUsuario(String dni) throws Exception {
      return usuarioRepository.findById(dni).orElseThrow(()-> new Exception("usuario no encontrado"));
    }

    @Override
    public Usuario crearNuevoUsuario() {
    return newUsuario;
    }

    @Override
    public List<Usuario> listarUsuarioActivos() {
    return usuarioRepository.findByEstado(true);
    }


    
}










    

