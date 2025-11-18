package ar.edu.ies6.tpfinaldiazmirada.model;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Component
@Entity
public class Vehiculo {
    @Id
    @Column
    @NotBlank(message="patente es requerido")
    @NotNull(message="patente is required")
    @Size(min = 4, max = 6, message="patente is requiredo")
    private String patente;
    @Column
    private String modelo;
    @Column
    private String marca;
    //@Column
    //private String tipoVehiculo;
    @Column
    private String color;
    @Column
    private Integer asientos;
    @Column
    private Boolean estado;

    @Column 
    @Enumerated(EnumType.STRING)
    private TipoDeVehiculo tipoDeVehiculo;

    @OneToOne
    @JoinColumn(name = "id_Conductor")
    private Conductor conductor;
 //referencedColumnName = "id"
    public enum TipoDeVehiculo {
        X,
        LUXE,
        PREMIUM
    }


    public Vehiculo() {
    }

    
    public Vehiculo(String patente, String modelo, String marca, TipoDeVehiculo tipoDeVehiculo, String color,
            Integer asientos, Boolean estado) {
        //this.id = id;
        this.patente = patente;
        this.modelo = modelo;
        this.marca = marca;
        this.tipoDeVehiculo = tipoDeVehiculo;
        this.color = color;
        this.asientos = asientos;
        this.estado = estado;
        //this.conductor = conductor;
    }



    public String getPatente() {
        return patente;
    }



    public void setPatente(String patente) {
        this.patente = patente;
    }



    public String getModelo() {
        return modelo;
    }



    public void setModelo(String modelo) {
        this.modelo = modelo;
    }



    public String getMarca() {
        return marca;
    }



    public void setMarca(String marca) {
        this.marca = marca;
    }



    public TipoDeVehiculo getTipoDeVehiculo() {
        return tipoDeVehiculo;
    }



    public void setTipoDeVehiculo(TipoDeVehiculo tipoDeVehiculo) {
        this.tipoDeVehiculo = tipoDeVehiculo;
    }



    public String getColor() {
        return color;
    }



    public void setColor(String color) {
        this.color = color;
    }



    public Integer getAsientos() {
        return asientos;
    }



    public void setAsientos(Integer asientos) {
        this.asientos = asientos;
    }



    public Boolean isEstado() {
        return estado;
    }



    public void setEstado(Boolean estado) {
        this.estado = estado;
    }



   public Conductor getConductor() {
       return conductor;
    }


    public void setConductor(Conductor conductor) {
      this.conductor = conductor;
    }


}
