package entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "iletisim_bilgileri")
@Data
public class IletisimBilgileri extends BaseEntity {


    @Column(name = "telefon")
    private String telefon;

    @Column(name = "eposta")
    private String eposta;

    // Diğer iletişim bilgileri...

    @ManyToOne
    @JoinColumn(name = "kullanici_id", nullable = false)
    private Kullanici kullanici;
}
