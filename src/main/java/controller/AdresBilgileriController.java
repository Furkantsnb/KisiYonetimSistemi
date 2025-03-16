package controller;

import dto.AdresBilgileriCreateDto;
import dto.AdresBilgileriDto;
import dto.AdresBilgileriUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.AdresBilgileriService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/adres-bilgileri")
public class AdresBilgileriController {

    private final AdresBilgileriService adresBilgileriService;

    @Autowired
    public AdresBilgileriController(AdresBilgileriService adresBilgileriService) {
        this.adresBilgileriService = adresBilgileriService;
    }

    @GetMapping
    public ResponseEntity<List<AdresBilgileriDto>> getAllAdresBilgileri() {
        return ResponseEntity.ok(adresBilgileriService.getAllAdresBilgileriDtos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdresBilgileriDto> getAdresBilgileriById(@PathVariable Long id) {
        Optional<AdresBilgileriDto> adresBilgileriDto = adresBilgileriService.getAdresBilgileriDtoById(id);
        return adresBilgileriDto.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AdresBilgileriDto> createAdresBilgileri(@RequestBody AdresBilgileriCreateDto adresBilgileriCreateDto) {
        AdresBilgileriDto savedAdresBilgileriDto = adresBilgileriService.createAdresBilgileri(adresBilgileriCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAdresBilgileriDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdresBilgileriDto> updateAdresBilgileri(@PathVariable Long id, @RequestBody AdresBilgileriUpdateDto adresBilgileriUpdateDto) {
        Optional<AdresBilgileriDto> updatedAdresBilgileriDto = adresBilgileriService.updateAdresBilgileri(id, adresBilgileriUpdateDto);
        return updatedAdresBilgileriDto.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdresBilgileri(@PathVariable Long id) {
        boolean deleted = adresBilgileriService.deleteAdresBilgileri(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}