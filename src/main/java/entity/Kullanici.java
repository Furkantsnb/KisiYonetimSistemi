package entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "kullanici")
@Data
public class Kullanici extends BaseEntity {


    @Column(name = "kullanici_adi", unique = true, nullable = false)
    private String kullaniciAdi;

    @Column(name = "sifre", nullable = false)
    private String sifre;

    @Column(name = "eposta", unique = true, nullable = false)
    private String eposta;

    @OneToOne(mappedBy = "kullanici", cascade = CascadeType.ALL)
    private KisiBilgileri kisiBilgileri;

    @OneToMany(mappedBy = "kullanici", cascade = CascadeType.ALL)
    private List<IletisimBilgileri> iletisimBilgileri;

    @OneToMany(mappedBy = "kullanici", cascade = CascadeType.ALL)
    private List<AdresBilgileri> adresBilgileri;

    @OneToMany(mappedBy = "kullanici", cascade = CascadeType.ALL)
    private List<YazilimDeneyimleri> yazilimDeneyimleri;

    @ManyToMany
    @JoinTable(
            name = "kullanici_rol",
            joinColumns = @JoinColumn(name = "kullanici_id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id")
    )
    private List<Rol> roller;




}

