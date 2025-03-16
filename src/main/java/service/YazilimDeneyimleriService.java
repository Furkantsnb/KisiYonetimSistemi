package service;

import dto.YazilimDeneyimleriCreateDto;
import dto.YazilimDeneyimleriDto;
import dto.YazilimDeneyimleriUpdateDto;
import entity.YazilimDeneyimleri;
import mapper.YazilimDeneyimleriMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.YazilimDeneyimleriRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class YazilimDeneyimleriService {

    private final YazilimDeneyimleriRepository yazilimDeneyimleriRepository;
    private final YazilimDeneyimleriMapper yazilimDeneyimleriMapper;

    @Autowired
    public YazilimDeneyimleriService(YazilimDeneyimleriRepository yazilimDeneyimleriRepository, YazilimDeneyimleriMapper yazilimDeneyimleriMapper) {
        this.yazilimDeneyimleriRepository = yazilimDeneyimleriRepository;
        this.yazilimDeneyimleriMapper = yazilimDeneyimleriMapper;
    }

    @Transactional(readOnly = true)
    public List<YazilimDeneyimleriDto> getAllYazilimDeneyimleriDtos() {
        return yazilimDeneyimleriRepository.findAll().stream()
                .map(yazilimDeneyimleriMapper::yazilimDeneyimleriToYazilimDeneyimleriDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<YazilimDeneyimleriDto> getYazilimDeneyimleriDtoById(Long id) {
        return yazilimDeneyimleriRepository.findById(id)
                .map(yazilimDeneyimleriMapper::yazilimDeneyimleriToYazilimDeneyimleriDto);
    }

    @Transactional
    public YazilimDeneyimleriDto createYazilimDeneyimleri(YazilimDeneyimleriCreateDto yazilimDeneyimleriCreateDto) {
        YazilimDeneyimleri yazilimDeneyimleri = yazilimDeneyimleriMapper.yazilimDeneyimleriCreateDtoToYazilimDeneyimleri(yazilimDeneyimleriCreateDto);
        YazilimDeneyimleri savedYazilimDeneyimleri = yazilimDeneyimleriRepository.save(yazilimDeneyimleri);
        return yazilimDeneyimleriMapper.yazilimDeneyimleriToYazilimDeneyimleriDto(savedYazilimDeneyimleri);
    }

    @Transactional
    public Optional<YazilimDeneyimleriDto> updateYazilimDeneyimleri(Long id, YazilimDeneyimleriUpdateDto yazilimDeneyimleriUpdateDto) {
        return yazilimDeneyimleriRepository.findById(id)
                .map(existingYazilimDeneyimleri -> {
                    yazilimDeneyimleriMapper.updateYazilimDeneyimleriFromDto(yazilimDeneyimleriUpdateDto, existingYazilimDeneyimleri);
                    YazilimDeneyimleri updatedYazilimDeneyimleri = yazilimDeneyimleriRepository.save(existingYazilimDeneyimleri);
                    return yazilimDeneyimleriMapper.yazilimDeneyimleriToYazilimDeneyimleriDto(updatedYazilimDeneyimleri);
                });
    }

    @Transactional
    public boolean deleteYazilimDeneyimleri(Long id) {
        if (yazilimDeneyimleriRepository.existsById(id)) {
            yazilimDeneyimleriRepository.deleteById(id);
            return true;
        }
        return false;
    }
}