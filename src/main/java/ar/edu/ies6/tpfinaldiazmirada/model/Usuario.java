package ar.edu.ies6.tpfinaldiazmirada.model;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Component
@Entity

public class Usuario {


    @Id
    private String dni;  
    @Column
    private String nombreCliente; 
     @Column
    private String apellidoCliente; 
    @Column
    private String correo; 
    @Column
    private boolean estado;
    


public Usuario () {
}


public Usuario(String dni, String nombreCliente, String apellidoCliente, String correo, boolean estado) {
    this.dni = dni;
    this.nombreCliente = nombreCliente;
    this.apellidoCliente = apellidoCliente;
    this.correo = correo;
    this.estado = estado;
}


public String getDni() {
    return dni;
}


public void setDni(String dni) {
    this.dni = dni;
}


public String getNombreCliente() {
    return nombreCliente;
}


public void setNombreCliente(String nombreCliente) {
    this.nombreCliente = nombreCliente;
}


public String getApellidoCliente() {
    return apellidoCliente;
}


public void setApellidoCliente(String apellidoCliente) {
    this.apellidoCliente = apellidoCliente;
}


public String getCorreo() {
    return correo;
}


public void setCorreo(String correo) {
    this.correo = correo;
}
public boolean isEstado() {
    return estado;
}


public void setEstado(boolean estado) {
    this.estado = estado;
}








}



