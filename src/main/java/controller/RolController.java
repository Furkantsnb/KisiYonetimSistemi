package controller;

import dto.RolCreateDto;
import dto.RolDto;
import dto.RolUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.RolService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/roller")
public class RolController {

    private final RolService rolService;

    @Autowired
    public RolController(RolService rolService) {
        this.rolService = rolService;
    }

    @GetMapping
    public ResponseEntity<List<RolDto>> getAllRoller() {
        return ResponseEntity.ok(rolService.getAllRolDtos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RolDto> getRolById(@PathVariable Long id) {
        Optional<RolDto> rolDto = rolService.getRolDtoById(id);
        return rolDto.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<RolDto> createRol(@RequestBody RolCreateDto rolCreateDto) {
        RolDto savedRolDto = rolService.createRol(rolCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRolDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RolDto> updateRol(@PathVariable Long id, @RequestBody RolUpdateDto rolUpdateDto) {
        Optional<RolDto> updatedRolDto = rolService.updateRol(id, rolUpdateDto);
        return updatedRolDto.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRol(@PathVariable Long id) {
        boolean deleted = rolService.deleteRol(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}