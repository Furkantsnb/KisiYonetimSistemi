package controller;

import dto.YazilimDeneyimleriCreateDto;
import dto.YazilimDeneyimleriDto;
import dto.YazilimDeneyimleriUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.YazilimDeneyimleriService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/yazilim-deneyimleri")
public class YazilimDeneyimleriController {

    private final YazilimDeneyimleriService yazilimDeneyimleriService;

    @Autowired
    public YazilimDeneyimleriController(YazilimDeneyimleriService yazilimDeneyimleriService) {
        this.yazilimDeneyimleriService = yazilimDeneyimleriService;
    }

    @GetMapping
    public ResponseEntity<List<YazilimDeneyimleriDto>> getAllYazilimDeneyimleri() {
        return ResponseEntity.ok(yazilimDeneyimleriService.getAllYazilimDeneyimleriDtos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<YazilimDeneyimleriDto> getYazilimDeneyimleriById(@PathVariable Long id) {
        Optional<YazilimDeneyimleriDto> yazilimDeneyimleriDto = yazilimDeneyimleriService.getYazilimDeneyimleriDtoById(id);
        return yazilimDeneyimleriDto.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<YazilimDeneyimleriDto> createYazilimDeneyimleri(@RequestBody YazilimDeneyimleriCreateDto yazilimDeneyimleriCreateDto) {
        YazilimDeneyimleriDto savedYazilimDeneyimleriDto = yazilimDeneyimleriService.createYazilimDeneyimleri(yazilimDeneyimleriCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedYazilimDeneyimleriDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<YazilimDeneyimleriDto> updateYazilimDeneyimleri(@PathVariable Long id, @RequestBody YazilimDeneyimleriUpdateDto yazilimDeneyimleriUpdateDto) {
        Optional<YazilimDeneyimleriDto> updatedYazilimDeneyimleriDto = yazilimDeneyimleriService.updateYazilimDeneyimleri(id, yazilimDeneyimleriUpdateDto);
        return updatedYazilimDeneyimleriDto.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteYazilimDeneyimleri(@PathVariable Long id) {
        boolean deleted = yazilimDeneyimleriService.deleteYazilimDeneyimleri(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}