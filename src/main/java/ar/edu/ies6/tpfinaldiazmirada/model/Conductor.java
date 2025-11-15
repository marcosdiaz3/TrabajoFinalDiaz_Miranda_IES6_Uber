package ar.edu.ies6.tpfinaldiazmirada.model;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Component
@Entity
public class Conductor {

    @Id
    private String dni;
    @Column
    private String nombre;
    @Column
    private String apellido;
    @Column
    private Integer edad;
    @Column
    private String telefono;
    @Column
    private Boolean estado;

    @OneToOne(mappedBy = "conductor")
    private Vehiculo vehiculo;

    public Conductor() {
    }

    
    public Conductor(String dni, String nombre, String apellido, Integer edad, String telefono, Boolean estado) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.telefono = telefono;
        this.estado = estado;
    }
    
    public String getDni() {
        return dni;
    }


    public void setDni(String dni) {
        this.dni = dni;
    }


    public String getNombre() {
        return nombre;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public String getApellido() {
        return apellido;
    }


    public void setApellido(String apellido) {
        this.apellido = apellido;
    }


    public Integer getEdad() {
        return edad;
    }


    public void setEdad(Integer edad) {
        this.edad = edad;
    }


    public String getTelefono() {
        return telefono;
    }


    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }


    public Boolean getEstado() {
        return estado;
    }


    public void setEstado(Boolean estado) {
        this.estado = estado;
    }


}
