package repository;
import entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DataSeeder implements CommandLineRunner {

    private final AdresBilgileriRepository adresBilgileriRepository;
    private final IletisimBilgileriRepository iletisimBilgileriRepository;
    private final KisiBilgileriRepository kisiBilgileriRepository;
    private final KullaniciRepository kullaniciRepository;
    private final RolRepository rolRepository;
    private final YazilimDeneyimleriRepository yazilimDeneyimleriRepository;
    private final PasswordEncoder passwordEncoder;

    public DataSeeder(AdresBilgileriRepository adresBilgileriRepository, IletisimBilgileriRepository iletisimBilgileriRepository, KisiBilgileriRepository kisiBilgileriRepository, KullaniciRepository kullaniciRepository, RolRepository rolRepository, YazilimDeneyimleriRepository yazilimDeneyimleriRepository, PasswordEncoder passwordEncoder) {
        this.adresBilgileriRepository = adresBilgileriRepository;
        this.iletisimBilgileriRepository = iletisimBilgileriRepository;
        this.kisiBilgileriRepository = kisiBilgileriRepository;
        this.kullaniciRepository = kullaniciRepository;
        this.rolRepository = rolRepository;
        this.yazilimDeneyimleriRepository = yazilimDeneyimleriRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        seedRoles();
        seedUsers();
        seedKisiBilgileri();
        seedIletisimBilgileri();
        seedAdresBilgileri();
        seedYazilimDeneyimleri() ;
    }
/*
    @Override
    public void run(String... args) {
        System.out.println("DataSeeder başlatıldı...");
        try {
            System.out.println("Rol verilerini yükleme başlatılıyor...");
            seedRoles();
            System.out.println("Roller başarıyla yüklendi. Mevcut rol sayısı: " + rolRepository.count());

            System.out.println("Kullanıcı verilerini yükleme başlatılıyor...");
            seedUsers();
            System.out.println("Kullanıcılar başarıyla yüklendi. Mevcut kullanıcı sayısı: " + kullaniciRepository.count());

            System.out.println("Kişi bilgilerini yükleme başlatılıyor...");
            seedKisiBilgileri();
            System.out.println("Kişi bilgileri başarıyla yüklendi. Mevcut kişi bilgisi sayısı: " + kisiBilgileriRepository.count());

            System.out.println("İletişim bilgilerini yükleme başlatılıyor...");
            seedIletisimBilgileri();
            System.out.println("İletişim bilgileri başarıyla yüklendi. Mevcut iletişim bilgisi sayısı: " + iletisimBilgileriRepository.count());

            System.out.println("Adres bilgilerini yükleme başlatılıyor...");
            seedAdresBilgileri();
            System.out.println("Adres bilgileri başarıyla yüklendi. Mevcut adres bilgisi sayısı: " + adresBilgileriRepository.count());

            System.out.println("Yazılım deneyimlerini yükleme başlatılıyor...");
            seedYazilimDeneyimleri();
            System.out.println("Yazılım deneyimleri başarıyla yüklendi. Mevcut yazılım deneyimi sayısı: " + yazilimDeneyimleriRepository.count());

            System.out.println("Tüm veriler başarıyla yüklendi!");
        } catch (Exception e) {
            System.err.println("Veri yükleme sırasında hata oluştu: " + e.getMessage());
            e.printStackTrace();
        }
    }*/


    private void seedRoles() {
        if (rolRepository.count() == 0) {
            Rol adminRol = new Rol();
            adminRol.setRolAdi("ADMIN");
            rolRepository.save(adminRol);

            Rol userRol = new Rol();
            userRol.setRolAdi("USER");
            rolRepository.save(userRol);

            Rol guestRol = new Rol();
            guestRol.setRolAdi("GUEST");
            rolRepository.save(guestRol);
        }
    }

    private void seedUsers() {
        if (kullaniciRepository.count() == 0) {
            Optional<Rol> adminRolOptional = rolRepository.findByRolAdi("ADMIN");
            Optional<Rol> userRolOptional = rolRepository.findByRolAdi("USER");

            if (adminRolOptional.isPresent() && userRolOptional.isPresent()) {
                Rol adminRol = adminRolOptional.get();
                Rol userRol = userRolOptional.get();

                Kullanici admin = new Kullanici();
                admin.setKullaniciAdi("admin");
                admin.setSifre(passwordEncoder.encode("admin123")); // Şifre hash'leniyor
                admin.setEposta("admin@example.com");
                List<Rol> adminRoles = new ArrayList<>();
                adminRoles.add(adminRol);
                admin.setRoller(adminRoles);
                kullaniciRepository.save(admin);

                Kullanici user1 = new Kullanici();
                user1.setKullaniciAdi("user1");
                user1.setSifre(passwordEncoder.encode("user123")); // Şifre hash'leniyor
                user1.setEposta("user1@example.com");
                List<Rol> user1Roles = new ArrayList<>();
                user1Roles.add(userRol);
                user1.setRoller(user1Roles);
                kullaniciRepository.save(user1);

                Kullanici user2 = new Kullanici();
                user2.setKullaniciAdi("user2");
                user2.setSifre(passwordEncoder.encode("user123")); // Şifre hash'leniyor
                user2.setEposta("user2@example.com");
                List<Rol> user2Roles = new ArrayList<>();
                user2Roles.add(userRol);
                user2.setRoller(user2Roles);
                kullaniciRepository.save(user2);
            }
        }
    }
    private void seedKisiBilgileri() {
        if (kisiBilgileriRepository.count() == 0) {
            Optional<Kullanici> adminOptional = kullaniciRepository.findByKullaniciAdi("admin");
            Optional<Kullanici> user1Optional = kullaniciRepository.findByKullaniciAdi("user1");
            Optional<Kullanici> user2Optional = kullaniciRepository.findByKullaniciAdi("user2");

            if (adminOptional.isPresent() && user1Optional.isPresent() && user2Optional.isPresent()) {
                Kullanici admin = adminOptional.get();
                KisiBilgileri adminKisi = new KisiBilgileri();
                adminKisi.setAd("Admin");
                adminKisi.setSoyad("User");
                adminKisi.setTcKimlikNo("11111111111");
                adminKisi.setKullanici(admin);
                adminKisi.setDogumTarihi(LocalDate.of(1990, 1, 1));
                adminKisi.setDogumYeri("Istanbul");
                adminKisi.setAnneAdi("Admin Anne");
                adminKisi.setBabaAdi("Admin Baba");
                adminKisi.setCinsiyet("Erkek");
                adminKisi.setUyruk("TC");
                kisiBilgileriRepository.save(adminKisi);

                Kullanici user1 = user1Optional.get();
                KisiBilgileri user1Kisi = new KisiBilgileri();
                user1Kisi.setAd("User");
                user1Kisi.setSoyad("One");
                user1Kisi.setTcKimlikNo("22222222222");
                user1Kisi.setKullanici(user1);
                user1Kisi.setDogumTarihi(LocalDate.of(1995, 2, 2));
                user1Kisi.setDogumYeri("Ankara");
                user1Kisi.setAnneAdi("User1 Anne");
                user1Kisi.setBabaAdi("User1 Baba");
                user1Kisi.setCinsiyet("Kadın");
                user1Kisi.setUyruk("TC");
                kisiBilgileriRepository.save(user1Kisi);

                Kullanici user2 = user2Optional.get();
                KisiBilgileri user2Kisi = new KisiBilgileri();
                user2Kisi.setAd("User");
                user2Kisi.setSoyad("Two");
                user2Kisi.setTcKimlikNo("33333333333");
                user2Kisi.setKullanici(user2);
                user2Kisi.setDogumTarihi(LocalDate.of(2000, 3, 3));
                user2Kisi.setDogumYeri("Izmir");
                user2Kisi.setAnneAdi("User2 Anne");
                user2Kisi.setBabaAdi("User2 Baba");
                user2Kisi.setCinsiyet("Erkek");
                user2Kisi.setUyruk("TC");
                kisiBilgileriRepository.save(user2Kisi);
            }
        }
    }
    private void seedIletisimBilgileri() {
        if (iletisimBilgileriRepository.count() == 0) {
            Optional<Kullanici> adminOptional = kullaniciRepository.findByKullaniciAdi("admin");
            Optional<Kullanici> user1Optional = kullaniciRepository.findByKullaniciAdi("user1");
            Optional<Kullanici> user2Optional = kullaniciRepository.findByKullaniciAdi("user2");

            if (adminOptional.isPresent() && user1Optional.isPresent() && user2Optional.isPresent()) {
                Kullanici admin = adminOptional.get();
                IletisimBilgileri adminIletisim = new IletisimBilgileri();
                adminIletisim.setTelefon("1234567890");
                adminIletisim.setEposta("admin@example.com");
                adminIletisim.setKullanici(admin);
                iletisimBilgileriRepository.save(adminIletisim);

                Kullanici user1 = user1Optional.get();
                IletisimBilgileri user1Iletisim = new IletisimBilgileri();
                user1Iletisim.setTelefon("9876543210");
                user1Iletisim.setEposta("user1@example.com");
                user1Iletisim.setKullanici(user1);
                iletisimBilgileriRepository.save(user1Iletisim);

                Kullanici user2 = user2Optional.get();
                IletisimBilgileri user2Iletisim = new IletisimBilgileri();
                user2Iletisim.setTelefon("5555555555");
                user2Iletisim.setEposta("user2@example.com");
                user2Iletisim.setKullanici(user2);
                iletisimBilgileriRepository.save(user2Iletisim);
            }
        }
    }

    private void seedAdresBilgileri() {
        if (adresBilgileriRepository.count() == 0) {
            Optional<Kullanici> adminOptional = kullaniciRepository.findByKullaniciAdi("admin");
            Optional<Kullanici> user1Optional = kullaniciRepository.findByKullaniciAdi("user1");
            Optional<Kullanici> user2Optional = kullaniciRepository.findByKullaniciAdi("user2");

            if (adminOptional.isPresent() && user1Optional.isPresent() && user2Optional.isPresent()) {
                Kullanici admin = adminOptional.get();
                AdresBilgileri adminAdres = new AdresBilgileri();
                adminAdres.setAdres("Admin Adres");
                adminAdres.setSehir("Istanbul");
                adminAdres.setIlce("Besiktas");
                adminAdres.setKullanici(admin);
                adresBilgileriRepository.save(adminAdres);

                Kullanici user1 = user1Optional.get();
                AdresBilgileri user1Adres = new AdresBilgileri();
                user1Adres.setAdres("User1 Adres");
                user1Adres.setSehir("Ankara");
                user1Adres.setIlce("Cankaya");
                user1Adres.setKullanici(user1);
                adresBilgileriRepository.save(user1Adres);

                Kullanici user2 = user2Optional.get();
                AdresBilgileri user2Adres = new AdresBilgileri();
                user2Adres.setAdres("User2 Adres");
                user2Adres.setSehir("Izmir");
                user2Adres.setIlce("Konak");
                user2Adres.setKullanici(user2);
                adresBilgileriRepository.save(user2Adres);
            }
        }
    }
    private void seedYazilimDeneyimleri() {
        if (yazilimDeneyimleriRepository.count() == 0) {
            Optional<Kullanici> adminOptional = kullaniciRepository.findByKullaniciAdi("admin");
            Optional<Kullanici> user1Optional = kullaniciRepository.findByKullaniciAdi("user1");
            Optional<Kullanici> user2Optional = kullaniciRepository.findByKullaniciAdi("user2");

            if (adminOptional.isPresent() && user1Optional.isPresent() && user2Optional.isPresent()) {
                Kullanici admin = adminOptional.get();
                YazilimDeneyimleri adminDeneyim1 = new YazilimDeneyimleri();
                adminDeneyim1.setSirketAdi("Admin Şirket 1");
                adminDeneyim1.setPozisyon("Yazılım Geliştirici");
                adminDeneyim1.setBaslangicTarihi("2020-01-01");
                adminDeneyim1.setBitisTarihi("2022-01-01");
                adminDeneyim1.setKullanici(admin);
                yazilimDeneyimleriRepository.save(adminDeneyim1);

                YazilimDeneyimleri adminDeneyim2 = new YazilimDeneyimleri();
                adminDeneyim2.setSirketAdi("Admin Şirket 2");
                adminDeneyim2.setPozisyon("Kıdemli Yazılım Geliştirici");
                adminDeneyim2.setBaslangicTarihi("2022-01-02");
                adminDeneyim2.setBitisTarihi("2024-01-02");
                adminDeneyim2.setKullanici(admin);
                yazilimDeneyimleriRepository.save(adminDeneyim2);

                YazilimDeneyimleri adminDeneyim3 = new YazilimDeneyimleri();
                adminDeneyim3.setSirketAdi("Admin Şirket 3");
                adminDeneyim3.setPozisyon("Takım Lideri");
                adminDeneyim3.setBaslangicTarihi("2024-01-03");
                adminDeneyim3.setBitisTarihi("Halen Devam Ediyor");
                adminDeneyim3.setKullanici(admin);
                yazilimDeneyimleriRepository.save(adminDeneyim3);
            }

            if (user1Optional.isPresent()) {
                Kullanici user1 = user1Optional.get();
                YazilimDeneyimleri user1Deneyim1 = new YazilimDeneyimleri();
                user1Deneyim1.setSirketAdi("User1 Şirket 1");
                user1Deneyim1.setPozisyon("Jr. Yazılım Geliştirici");
                user1Deneyim1.setBaslangicTarihi("2021-05-01");
                user1Deneyim1.setBitisTarihi("2023-05-01");
                user1Deneyim1.setKullanici(user1);
                yazilimDeneyimleriRepository.save(user1Deneyim1);

                YazilimDeneyimleri user1Deneyim2 = new YazilimDeneyimleri();
                user1Deneyim2.setSirketAdi("User1 Şirket 2");
                user1Deneyim2.setPozisyon("Yazılım Geliştirici");
                user1Deneyim2.setBaslangicTarihi("2023-05-02");
                user1Deneyim2.setBitisTarihi("Halen Devam Ediyor");
                user1Deneyim2.setKullanici(user1);
                yazilimDeneyimleriRepository.save(user1Deneyim2);
            }

            if (user2Optional.isPresent()) {
                Kullanici user2 = user2Optional.get();
                YazilimDeneyimleri user2Deneyim1 = new YazilimDeneyimleri();
                user2Deneyim1.setSirketAdi("User2 Şirket 1");
                user2Deneyim1.setPozisyon("Stajyer");
                user2Deneyim1.setBaslangicTarihi("2022-06-01");
                user2Deneyim1.setBitisTarihi("2022-09-01");
                user2Deneyim1.setKullanici(user2);
                yazilimDeneyimleriRepository.save(user2Deneyim1);

                YazilimDeneyimleri user2Deneyim2 = new YazilimDeneyimleri();
                user2Deneyim2.setSirketAdi("User2 Şirket 2");
                user2Deneyim2.setPozisyon("Jr. Yazılım Geliştirici");
                user2Deneyim2.setBaslangicTarihi("2022-09-02");
                user2Deneyim2.setBitisTarihi("Halen Devam Ediyor");
                user2Deneyim2.setKullanici(user2);
                yazilimDeneyimleriRepository.save(user2Deneyim2);
            }
        }
    }


}
