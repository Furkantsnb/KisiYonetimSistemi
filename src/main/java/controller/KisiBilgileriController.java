// KisiBilgileriController.java
package controller;

import dto.KisiBilgileriCreateDto;
import dto.KisiBilgileriDto;
import dto.KisiBilgileriUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.KisiBilgileriService;

import java.util.List;

@RestController
@RequestMapping("/api/kisi-bilgileri")
public class KisiBilgileriController {

    @Autowired
    private KisiBilgileriService kisiBilgileriService;

    @GetMapping
    public ResponseEntity<List<KisiBilgileriDto>> getAllKisiBilgileri() {
        return ResponseEntity.ok(kisiBilgileriService.getAllKisiBilgileriDtos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<KisiBilgileriDto> getKisiBilgileriById(@PathVariable Long id) {
        KisiBilgileriDto kisiBilgileriDto = kisiBilgileriService.getKisiBilgileriDtoById(id);
        if (kisiBilgileriDto != null) {
            return ResponseEntity.ok(kisiBilgileriDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<KisiBilgileriDto> createKisiBilgileri(@RequestBody KisiBilgileriCreateDto kisiBilgileriCreateDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(kisiBilgileriService.createKisiBilgileri(kisiBilgileriCreateDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<KisiBilgileriDto> updateKisiBilgileri(@PathVariable Long id, @RequestBody KisiBilgileriUpdateDto kisiBilgileriUpdateDto) {
        KisiBilgileriDto updatedKisiBilgileriDto = kisiBilgileriService.updateKisiBilgileri(id, kisiBilgileriUpdateDto);
        if (updatedKisiBilgileriDto != null) {
            return ResponseEntity.ok(updatedKisiBilgileriDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteKisiBilgileri(@PathVariable Long id) {
        kisiBilgileriService.deleteKisiBilgileri(id);
        return ResponseEntity.noContent().build();
    }
}