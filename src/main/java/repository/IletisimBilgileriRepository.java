package repository;


import entity.IletisimBilgileri;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IletisimBilgileriRepository extends JpaRepository<IletisimBilgileri, Long> {
    // Özel sorgu metotları (gerekirse buraya eklenebilir)
}
