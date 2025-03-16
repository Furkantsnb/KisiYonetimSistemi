package repository;


import entity.Kullanici;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KullaniciRepository extends JpaRepository<Kullanici, Long> {
    // Özel sorgu metotları (gerekirse buraya eklenebilir)
    Kullanici findByKullaniciAdi(String kullaniciAdi); // Kullanıcı adına göre kullanıcı bulma
}