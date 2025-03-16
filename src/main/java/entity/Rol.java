package entity;


import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Table(name = "rol")
@Data
public class Rol extends BaseEntity {


    @Column(name = "rol_adi", unique = true, nullable = false)
    private String rolAdi;

    @ManyToMany(mappedBy = "roller")
    private List<Kullanici> kullanicilar;
}
