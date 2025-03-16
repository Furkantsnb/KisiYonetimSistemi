package service;

import dto.RolCreateDto;
import dto.RolDto;
import dto.RolUpdateDto;
import entity.Rol;
import mapper.RolMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.RolRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RolService {

    private final RolRepository rolRepository;
    private final RolMapper rolMapper;

    @Autowired
    public RolService(RolRepository rolRepository, RolMapper rolMapper) {
        this.rolRepository = rolRepository;
        this.rolMapper = rolMapper;
    }

    @Transactional(readOnly = true)
    public List<RolDto> getAllRolDtos() {
        return rolRepository.findAll().stream()
                .map(rolMapper::rolToRolDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<RolDto> getRolDtoById(Long id) {
        return rolRepository.findById(id)
                .map(rolMapper::rolToRolDto);
    }

    @Transactional
    public RolDto createRol(RolCreateDto rolCreateDto) {
        Rol rol = rolMapper.rolCreateDtoToRol(rolCreateDto);
        Rol savedRol = rolRepository.save(rol);
        return rolMapper.rolToRolDto(savedRol);
    }

    @Transactional
    public Optional<RolDto> updateRol(Long id, RolUpdateDto rolUpdateDto) {
        return rolRepository.findById(id)
                .map(existingRol -> {
                    rolMapper.updateRolFromDto(rolUpdateDto, existingRol);
                    Rol updatedRol = rolRepository.save(existingRol);
                    return rolMapper.rolToRolDto(updatedRol);
                });
    }

    @Transactional
    public boolean deleteRol(Long id) {
        if (rolRepository.existsById(id)) {
            rolRepository.deleteById(id);
            return true;
        }
        return false;
    }
}