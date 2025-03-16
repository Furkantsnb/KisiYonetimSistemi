package entity;



import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "kisi_bilgileri")
@Data
public class KisiBilgileri extends BaseEntity {


    @Column(name = "tc_kimlik_no", unique = true)
    private String tcKimlikNo;

    @Column(name = "ad", nullable = false)
    private String ad;

    @Column(name = "soyad", nullable = false)
    private String soyad;

    // Diğer TC kimlik bilgileri...

    @OneToOne
    @JoinColumn(name = "kullanici_id", unique = true)
    private Kullanici kullanici;
}
