package service;

import entity.Kullanici;
import exception.ResourceNotFoundException;
import mapper.KullaniciMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import repository.KullaniciRepository;
import security.JWTUtil;
import dto.KullaniciCreateDto; // KullaniciCreateDto'yu ekledik

import java.util.Optional;

@Service
public class LoginService {

    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;
    private final JWTUtil jwtUtil;
    private final KullaniciRepository kullaniciRepository;
    private final PasswordEncoder passwordEncoder;
    private final KullaniciMapper kullaniciMapper; // KullaniciMapper'ı ekledik

    @Autowired
    public LoginService(AuthenticationManager authenticationManager,
                        CustomUserDetailsService userDetailsService,
                        JWTUtil jwtUtil,
                        KullaniciRepository kullaniciRepository,
                        PasswordEncoder passwordEncoder,
                        KullaniciMapper kullaniciMapper) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
        this.kullaniciRepository = kullaniciRepository;
        this.passwordEncoder = passwordEncoder;
        this.kullaniciMapper = kullaniciMapper;
    }

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

    public boolean sifreKontrol(String girilenSifre, String veritabanindakiSifre) {
        return passwordEncoder.matches(girilenSifre, veritabanindakiSifre);
    }

    public String register(KullaniciCreateDto kullaniciCreateDto) {
        // Kullanıcı adı ve e-posta kontrolü (isteğe bağlı)
        if (kullaniciRepository.existsByKullaniciAdi(kullaniciCreateDto.getKullaniciAdi())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Kullanıcı adı zaten kullanımda");
        }
        if (kullaniciRepository.existsByEposta(kullaniciCreateDto.getEposta())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "E-posta adresi zaten kullanımda");
        }

        // Kullanıcıyı oluştur
        Kullanici kullanici = kullaniciMapper.kullaniciCreateDtoToKullanici(kullaniciCreateDto);

        // Şifreyi şifrele
        kullanici.setSifre(passwordEncoder.encode(kullaniciCreateDto.getSifre()));

        // Kullanıcıyı kaydet
        Kullanici savedKullanici = kullaniciRepository.save(kullanici);

        // JWT token'ı oluştur ve döndür
        UserDetails userDetails = userDetailsService.loadUserByUsername(savedKullanici.getKullaniciAdi());
        return jwtUtil.generateToken(userDetails);
    }
}