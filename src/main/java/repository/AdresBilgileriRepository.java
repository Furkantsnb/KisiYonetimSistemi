package repository;

import entity.AdresBilgileri;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdresBilgileriRepository extends JpaRepository<AdresBilgileri, Long> {
    // Özel sorgu metotları (gerekirse buraya eklenebilir)
}
