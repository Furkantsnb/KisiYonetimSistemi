package service;


import entity.Kullanici;
import repository.KullaniciRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private KullaniciRepository kullaniciRepository;

    @Override
    public UserDetails loadUserByUsername(String kullaniciAdi) throws UsernameNotFoundException {
        Optional<Kullanici> kullanici = kullaniciRepository.findByKullaniciAdi(kullaniciAdi);

        if (kullanici.isPresent()) {
            Kullanici bulunanKullanici = kullanici.get();
            return new User(bulunanKullanici.getKullaniciAdi(), bulunanKullanici.getSifre(), new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("Kullanıcı bulunamadı: " + kullaniciAdi);
        }
    }
}