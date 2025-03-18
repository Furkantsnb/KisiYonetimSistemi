package service;

import entity.Kullanici;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import repository.KullaniciRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor // @RequiredArgsConstructor anotasyonunu ekledim
public class CustomUserDetailsService implements UserDetailsService {

    private final KullaniciRepository kullaniciRepository; // Alanı final olarak işaretledim

    @Override
    public UserDetails loadUserByUsername(String kullaniciAdi) throws UsernameNotFoundException {
        Optional<Kullanici> kullanici = kullaniciRepository.findByKullaniciAdi(kullaniciAdi);

        if (kullanici.isPresent()) {
            Kullanici bulunanKullanici = kullanici.get();
            return JwtUserDetails.create(bulunanKullanici); // JwtUserDetails.create() metodunu kullandım
        } else {
            throw new UsernameNotFoundException("Kullanıcı bulunamadı: " + kullaniciAdi);
        }
    }

    // JwtUserDetails sınıfı (örnek)
    public static class JwtUserDetails implements UserDetails {

        private final Long id;
        private final String username;
        private final String password;
        private final List<GrantedAuthority> authorities;

        private JwtUserDetails(Long id, String username, String password, List<GrantedAuthority> authorities) {
            this.id = id;
            this.username = username;
            this.password = password;
            this.authorities = authorities;
        }

        public static JwtUserDetails create(Kullanici kullanici) {
            List<GrantedAuthority> authorities = kullanici.getRolIds().stream() // Rol ID'lerini kullanarak GrantedAuthority listesi oluşturuyorum
                    .map(rolId -> new SimpleGrantedAuthority("ROLE_" + rolId)) // Örnek rol formatı: ROLE_1, ROLE_2, vb.
                    .collect(Collectors.toList());

            return new JwtUserDetails(
                    kullanici.getId(),
                    kullanici.getKullaniciAdi(),
                    kullanici.getSifre(),
                    authorities
            );
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return authorities;
        }

        @Override
        public String getPassword() {
            return password;
        }

        @Override
        public String getUsername() {
            return username;
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
    }
}