package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody; // RequestBody ekledik
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.LoginService;
import dto.KullaniciCreateDto; // KullaniciCreateDto'yu ekledik

@RestController
public class LoginController {

    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String kullaniciAdi, @RequestParam String sifre) {
        String jwt = loginService.login(kullaniciAdi, sifre);
        return ResponseEntity.ok(jwt);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody KullaniciCreateDto kullaniciCreateDto) {
        String jwt = loginService.register(kullaniciCreateDto);
        return ResponseEntity.ok(jwt);
    }
}