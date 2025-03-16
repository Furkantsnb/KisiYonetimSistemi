package repository;

import entity.YazilimDeneyimleri;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YazilimDeneyimleriRepository extends JpaRepository<YazilimDeneyimleri, Long> {
    // Özel sorgu metotları (gerekirse buraya eklenebilir)
}
