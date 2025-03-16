package controller;

import dto.KullaniciCreateDto;
import dto.KullaniciDto;
import dto.KullaniciUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/kullanici")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<KullaniciDto>> getAllKullanicilar() {
        return ResponseEntity.ok(userService.getAllKullaniciDtos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<KullaniciDto> getKullaniciById(@PathVariable Long id) {
        KullaniciDto kullaniciDto = userService.getKullaniciDtoById(id);
        if (kullaniciDto != null) {
            return ResponseEntity.ok(kullaniciDto);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<KullaniciDto> createKullanici(@RequestBody KullaniciCreateDto kullaniciCreateDto) {
        KullaniciDto createdKullaniciDto = userService.createKullanici(kullaniciCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdKullaniciDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<KullaniciDto> updateKullanici(@PathVariable Long id, @RequestBody KullaniciUpdateDto kullaniciUpdateDto) {
        KullaniciDto updatedKullaniciDto = userService.updateKullanici(id, kullaniciUpdateDto);
        if (updatedKullaniciDto != null) {
            return ResponseEntity.ok(updatedKullaniciDto);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteKullanici(@PathVariable Long id) {
        userService.deleteKullanici(id);
        return ResponseEntity.noContent().build();
    }
}