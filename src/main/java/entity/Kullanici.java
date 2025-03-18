package entity;



import entity.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.stream.Collectors;
import jakarta.annotation.PostConstruct;
import java.util.ArrayList;

import java.util.List;

@Entity
@Table(name = "kullanici")
@Getter
@Setter
@NoArgsConstructor
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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "kullanici_rol",
            joinColumns = @JoinColumn(name = "kullanici_id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id")
    )
    private List<Rol> roller;

    @PostConstruct // @PostConstruct anotasyonunu ekleyin
    public void init() {
        this.roller = new ArrayList<>(); // roller listesini başlatın
    }

    public List<Long> getRolIds() {
        if(roller == null) return new ArrayList<>();
        return roller.stream()
                .map(Rol::getId)
                .collect(Collectors.toList());
    }
}

