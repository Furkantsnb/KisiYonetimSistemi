package repository;

import entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {
    // Özel sorgu metotları (gerekirse buraya eklenebilir)
    Rol findByRolAdi(String rolAdi); // Rol adına göre rol bulma
}
