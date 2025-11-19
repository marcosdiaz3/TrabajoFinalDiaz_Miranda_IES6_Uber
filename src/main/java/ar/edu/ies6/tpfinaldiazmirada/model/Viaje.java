package ar.edu.ies6.tpfinaldiazmirada.model;
import java.time.LocalDate;

import org.springframework.stereotype.Component;
        
import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Component
@Entity

public class Viaje {
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id; //id pk

    @Column
    private boolean estado = true;
  
    @Enumerated(EnumType.STRING)
    private TipViaje tipViaje;

    @Column
    private double costoViaje;
    @Column
    private LocalDate fechaViaje;


//relalcion Na1  muchos viajes realizan un usuario
@ManyToOne (fetch = FetchType.LAZY)
@JoinColumn(name="dni")
private Usuario usuario;

//relacion Na1 muchos viajes realizan el mismo vehiculo
@ManyToOne
@JoinColumn(name="patente")
private Vehiculo vehiculo;



public enum TipViaje{
    BASE,
    LUXE,
    PREMIUM
}

public Viaje() {
}


public Viaje(Integer id, boolean estado, double costoViaje, LocalDate fechaViaje) {
    this.id = id;
    this.estado = estado;
    this.costoViaje = costoViaje;
    this.fechaViaje = fechaViaje;
}


public Integer getId() {
    return id;
}


public void setId(Integer id) {
    this.id = id;
}


public boolean isEstado() {
    return estado;
}


public void setEstado(boolean estado) {
    this.estado = estado;
}


public double getCostoViaje() {
    return costoViaje;
}


public void setCostoViaje(double costoViaje) {
    this.costoViaje = costoViaje;
}


public LocalDate getFechaViaje() {
    return fechaViaje;
}


public void setFechaViaje(LocalDate fechaViaje) {
    this.fechaViaje = fechaViaje;
}




public TipViaje getTipViaje() {
    return tipViaje;
}

public void setTipViaje(TipViaje tipViaje) {
    this.tipViaje = tipViaje;
}



public Vehiculo getVehiculo() {
    return vehiculo;
}

public void setVehiculo(Vehiculo vehiculo) {
    this.vehiculo = vehiculo;
}





}
