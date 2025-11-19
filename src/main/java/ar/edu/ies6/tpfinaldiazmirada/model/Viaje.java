package ar.edu.ies6.tpfinaldiazmirada.model;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import ar.edu.ies6.tpfinaldiazmirada.model.Vehiculo.TipoDeVehiculo;
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
    private Integer id;

    @Column
    private boolean estado = true;

    @Enumerated(EnumType.STRING)
    private TipViaje tipViaje;

    @Column
    private Double precioFinal;

    @Column
    @Enumerated(EnumType.STRING)
    private Vehiculo.TipoDeVehiculo tipoDeVehiculo;


    // MUCHOS viajes → 1 usuario
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dni")
    private Usuario usuario;

    // MUCHOS viajes → 1 vehículo
    @ManyToOne
    @JoinColumn(name = "patente")
    private Vehiculo vehiculo;

    public enum TipViaje {
        CORTO(7000),
        MEDIO(15000),
        LARGO(20000);

        TipViaje(int i) {
   
        }
    }

    public Viaje() {}

    public Viaje(Integer id, boolean estado, double precioFinal, Vehiculo.TipoDeVehiculo tipoDeVehiculo) {
        this.id = id;
        this.estado = estado;
        this.precioFinal = precioFinal;
        this.tipoDeVehiculo = tipoDeVehiculo;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public boolean isEstado() { return estado; }
    public void setEstado(boolean estado) { this.estado = estado; }

    public double getPrecioFinal() { return precioFinal; }
    public void setPrecioFinal(double precioFinal) { this.precioFinal = precioFinal; }

    public TipoDeVehiculo getTipoDeVehiculo() { return tipoDeVehiculo; }

    public void setTipoDeVehiculo(TipoDeVehiculo tipoDeVehiculo) { this.tipoDeVehiculo = tipoDeVehiculo; }


    public TipViaje getTipViaje() { return tipViaje; }
    public void setTipViaje(TipViaje tipViaje) { this.tipViaje = tipViaje; }

    public Vehiculo getVehiculo() { return vehiculo; }
    public void setVehiculo(Vehiculo vehiculo) { this.vehiculo = vehiculo; }

    
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

}
