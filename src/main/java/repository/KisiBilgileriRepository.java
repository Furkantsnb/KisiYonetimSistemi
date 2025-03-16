package repository;


import entity.KisiBilgileri;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KisiBilgileriRepository extends JpaRepository<KisiBilgileri, Long> {
    // Özel sorgu metotları (gerekirse buraya eklenebilir)
}
