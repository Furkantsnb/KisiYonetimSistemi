package controller;



import entity.Kullanici;
import service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/kullanici")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<Kullanici>> getAllKullanicilar() {
        return ResponseEntity.ok(userService.getAllKullanicilar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Kullanici> getKullaniciById(@PathVariable Long id) {
        Optional<Kullanici> kullanici = userService.getKullaniciById(id);
        return kullanici.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Kullanici> createKullanici(@RequestBody Kullanici kullanici) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createKullanici(kullanici));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Kullanici> updateKullanici(@PathVariable Long id, @RequestBody Kullanici kullanici) {
        Kullanici updatedKullanici = userService.updateKullanici(id, kullanici);
        if (updatedKullanici != null) {
            return ResponseEntity.ok(updatedKullanici);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteKullanici(@PathVariable Long id) {
        userService.deleteKullanici(id);
        return ResponseEntity.noContent().build();
    }
}
