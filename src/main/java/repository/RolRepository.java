package repository;

import entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {
    // Özel sorgu metotları (gerekirse buraya eklenebilir)
    Optional<Rol> findByRolAdi(String rolAdi); // Rol adına göre rol bulma

}
