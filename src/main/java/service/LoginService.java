package service;


import entity.Kullanici;
import repository.KullaniciRepository;
import security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private KullaniciRepository kullaniciRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String login(String kullaniciAdi, String sifre) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(kullaniciAdi, sifre));
        } catch (AuthenticationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Kullanıcı adı veya şifre hatalı");
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(kullaniciAdi);
        return jwtUtil.generateToken(userDetails);
    }

    public Kullanici findKullaniciByKullaniciAdi(String kullaniciAdi) {
        Optional<Kullanici> kullanici = kullaniciRepository.findByKullaniciAdi(kullaniciAdi);
        if (kullanici.isPresent()) {
            return kullanici.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Kullanıcı bulunamadı");
        }
    }

    public boolean sifreKontrol(String girilenSifre, String veritabanindakiSifre){
        return passwordEncoder.matches(girilenSifre, veritabanindakiSifre);
    }
}
