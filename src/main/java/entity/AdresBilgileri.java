package entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "adres_bilgileri")
@Data
public class AdresBilgileri extends BaseEntity {


    @Column(name = "adres")
    private String adres;

    @Column(name = "sehir")
    private String sehir;

    // Diğer adres bilgileri...

    @ManyToOne
    @JoinColumn(name = "kullanici_id", nullable = false)
    private Kullanici kullanici;
}