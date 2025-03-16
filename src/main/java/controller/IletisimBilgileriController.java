package controller;

import dto.IletisimBilgileriCreateDto;
import dto.IletisimBilgileriDto;
import dto.IletisimBilgileriUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.IletisimBilgileriService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/iletisim-bilgileri")
public class IletisimBilgileriController {

    private final IletisimBilgileriService iletisimBilgileriService;

    @Autowired
    public IletisimBilgileriController(IletisimBilgileriService iletisimBilgileriService) {
        this.iletisimBilgileriService = iletisimBilgileriService;
    }

    @GetMapping
    public ResponseEntity<List<IletisimBilgileriDto>> getAllIletisimBilgileri() {
        return ResponseEntity.ok(iletisimBilgileriService.getAllIletisimBilgileriDtos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<IletisimBilgileriDto> getIletisimBilgileriById(@PathVariable Long id) {
        Optional<IletisimBilgileriDto> iletisimBilgileriDto = iletisimBilgileriService.getIletisimBilgileriDtoById(id);
        return iletisimBilgileriDto.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<IletisimBilgileriDto> createIletisimBilgileri(@RequestBody IletisimBilgileriCreateDto iletisimBilgileriCreateDto) {
        IletisimBilgileriDto savedIletisimBilgileriDto = iletisimBilgileriService.createIletisimBilgileri(iletisimBilgileriCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedIletisimBilgileriDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<IletisimBilgileriDto> updateIletisimBilgileri(@PathVariable Long id, @RequestBody IletisimBilgileriUpdateDto iletisimBilgileriUpdateDto) {
        Optional<IletisimBilgileriDto> updatedIletisimBilgileriDto = iletisimBilgileriService.updateIletisimBilgileri(id, iletisimBilgileriUpdateDto);
        return updatedIletisimBilgileriDto.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIletisimBilgileri(@PathVariable Long id) {
        boolean deleted = iletisimBilgileriService.deleteIletisimBilgileri(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}