// KisiBilgileri.java
package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "kisi_bilgileri")
@Getter
@Setter
@NoArgsConstructor
public class KisiBilgileri extends BaseEntity {

    @Column(name = "tc_kimlik_no", unique = true)
    private String tcKimlikNo;

    @Column(name = "ad", nullable = false)
    private String ad;

    @Column(name = "soyad", nullable = false)
    private String soyad;

    @Column(name = "dogum_tarihi")
    private LocalDate dogumTarihi;

    @Column(name = "dogum_yeri")
    private String dogumYeri;

    @Column(name = "anne_adi")
    private String anneAdi;

    @Column(name = "baba_adi")
    private String babaAdi;

    @Column(name = "cinsiyet")
    private String cinsiyet;

    @Column(name = "uyruk")
    private String uyruk;

    // Diğer TC kimlik bilgileri...

    @OneToOne
    @JoinColumn(name = "kullanici_id", unique = true)
    private Kullanici kullanici;
}