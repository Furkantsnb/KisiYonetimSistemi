package service;



import entity.Kullanici;
import repository.KullaniciRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private KullaniciRepository kullaniciRepository;

    public List<Kullanici> getAllKullanicilar() {
        return kullaniciRepository.findAll();
    }

    public Optional<Kullanici> getKullaniciById(Long id) {
        return kullaniciRepository.findById(id);
    }

    public Kullanici createKullanici(Kullanici kullanici) {
        return kullaniciRepository.save(kullanici);
    }

    public Kullanici updateKullanici(Long id, Kullanici kullanici) {
        Optional<Kullanici> existingKullanici = kullaniciRepository.findById(id);
        if (existingKullanici.isPresent()) {
            kullanici.setId(id); // ID'yi ayarlıyoruz ki güncelleme yapılabilsin
            return kullaniciRepository.save(kullanici);
        }
        return null; // Kullanıcı bulunamadı
    }

    public void deleteKullanici(Long id) {
        kullaniciRepository.deleteById(id);
    }
}