package repository;


import entity.Kullanici;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

@Repository
public interface KullaniciRepository extends JpaRepository<Kullanici, Long> {
    // Özel sorgu metotları (gerekirse buraya eklenebilir)
    Optional<Kullanici> findByKullaniciAdi(String kullaniciAdi); // Kullanıcı adına göre kullanıcı bulma

    boolean existsByKullaniciAdi(String kullaniciAdi);

    boolean existsByEposta(String eposta);

   /* @Query("SELECT k FROM Kullanici k LEFT JOIN FETCH k.roller WHERE k.kullaniciAdi = :kullaniciAdi") // Sorguyu ekleyin
    Optional<Kullanici> findByKullaniciAdiWithRoller(@Param("kullaniciAdi") String kullaniciAdi);*/
}