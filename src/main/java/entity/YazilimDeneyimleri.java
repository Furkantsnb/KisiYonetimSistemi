package entity;



import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "yazilim_deneyimleri")
@Data
public class YazilimDeneyimleri extends BaseEntity {


    @Column(name = "sirket_adi")
    private String sirketAdi;

    @Column(name = "pozisyon")
    private String pozisyon;

    @Column(name = "baslangic_tarihi")
    private String baslangicTarihi;

    @Column(name = "bitis_tarihi")
    private String bitisTarihi;

    // Diğer yazılım deneyimleri bilgileri...

    @ManyToOne
    @JoinColumn(name = "kullanici_id", nullable = false)
    private Kullanici kullanici;
}
