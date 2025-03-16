package repository;


import entity.Kullanici;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KullaniciRepository extends JpaRepository<Kullanici, Long> {
    // Özel sorgu metotları (gerekirse buraya eklenebilir)
    Optional<Kullanici> findByKullaniciAdi(String kullaniciAdi); // Kullanıcı adına göre kullanıcı bulma
}