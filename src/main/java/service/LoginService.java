package service;

import entity.Kullanici;
import lombok.RequiredArgsConstructor;
import mapper.KullaniciMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import repository.KullaniciRepository;
import security.JWTUtil;
import dto.KullaniciCreateDto;
import dto.KullaniciUpdateDto;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;
    private final JWTUtil jwtUtil;
    private final KullaniciRepository kullaniciRepository;
    private final PasswordEncoder passwordEncoder;
    private final KullaniciMapper kullaniciMapper;

    public String login(String kullaniciAdi, String sifre) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(kullaniciAdi, sifre));
        } catch (BadCredentialsException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Kullanıcı adı veya şifre hatalı");
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(kullaniciAdi);
        return jwtUtil.generateToken(userDetails);
    }

    public Kullanici findKullaniciByKullaniciAdi(String kullaniciAdi) {
        return kullaniciRepository.findByKullaniciAdi(kullaniciAdi) // Güncellendi
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Kullanıcı bulunamadı"));
    }

    public boolean checkPassword(String girilenSifre, String veritabanindakiSifre) {
        return passwordEncoder.matches(girilenSifre, veritabanindakiSifre);
    }

    @Transactional
    public String register(KullaniciCreateDto kullaniciCreateDto) {
        if (kullaniciRepository.existsByKullaniciAdi(kullaniciCreateDto.getKullaniciAdi())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Kullanıcı adı zaten kullanımda");
        }
        if (kullaniciRepository.existsByEposta(kullaniciCreateDto.getEposta())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "E-posta adresi zaten kullanımda");
        }

        Kullanici kullanici = kullaniciMapper.toEntity(kullaniciCreateDto); // Güncellendi
        kullanici.setSifre(passwordEncoder.encode(kullaniciCreateDto.getSifre()));

        Kullanici savedKullanici = kullaniciRepository.save(kullanici);

        UserDetails userDetails = userDetailsService.loadUserByUsername(savedKullanici.getKullaniciAdi());
        return jwtUtil.generateToken(userDetails);
    }

    public String update(KullaniciUpdateDto kullaniciUpdateDto) {
        Kullanici kullanici = kullaniciRepository.findById(kullaniciUpdateDto.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Kullanıcı bulunamadı"));
        kullaniciMapper.updateKullaniciFromDto(kullaniciUpdateDto, kullanici);
        Kullanici updatedKullanici = kullaniciRepository.save(kullanici);

        UserDetails userDetails = userDetailsService.loadUserByUsername(updatedKullanici.getKullaniciAdi());
        return jwtUtil.generateToken(userDetails);
    }
}