package service;

import dto.AdresBilgileriCreateDto;
import dto.AdresBilgileriDto;
import dto.AdresBilgileriUpdateDto;
import entity.AdresBilgileri;
import mapper.AdresBilgileriMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.AdresBilgileriRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdresBilgileriService {

    private final AdresBilgileriRepository adresBilgileriRepository;
    private final AdresBilgileriMapper adresBilgileriMapper;

    @Autowired
    public AdresBilgileriService(AdresBilgileriRepository adresBilgileriRepository, AdresBilgileriMapper adresBilgileriMapper) {
        this.adresBilgileriRepository = adresBilgileriRepository;
        this.adresBilgileriMapper = adresBilgileriMapper;
    }

    @Transactional(readOnly = true)
    public List<AdresBilgileriDto> getAllAdresBilgileriDtos() {
        return adresBilgileriRepository.findAll().stream()
                .map(adresBilgileriMapper::adresBilgileriToAdresBilgileriDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<AdresBilgileriDto> getAdresBilgileriDtoById(Long id) {
        return adresBilgileriRepository.findById(id)
                .map(adresBilgileriMapper::adresBilgileriToAdresBilgileriDto);
    }

    @Transactional
    public AdresBilgileriDto createAdresBilgileri(AdresBilgileriCreateDto adresBilgileriCreateDto) {
        AdresBilgileri adresBilgileri = adresBilgileriMapper.adresBilgileriCreateDtoToAdresBilgileri(adresBilgileriCreateDto);
        AdresBilgileri savedAdresBilgileri = adresBilgileriRepository.save(adresBilgileri);
        return adresBilgileriMapper.adresBilgileriToAdresBilgileriDto(savedAdresBilgileri);
    }

    @Transactional
    public Optional<AdresBilgileriDto> updateAdresBilgileri(Long id, AdresBilgileriUpdateDto adresBilgileriUpdateDto) {
        return adresBilgileriRepository.findById(id)
                .map(existingAdresBilgileri -> {
                    adresBilgileriMapper.updateAdresBilgileriFromDto(adresBilgileriUpdateDto, existingAdresBilgileri);
                    AdresBilgileri updatedAdresBilgileri = adresBilgileriRepository.save(existingAdresBilgileri);
                    return adresBilgileriMapper.adresBilgileriToAdresBilgileriDto(updatedAdresBilgileri);
                });
    }

    @Transactional
    public boolean deleteAdresBilgileri(Long id) {
        if (adresBilgileriRepository.existsById(id)) {
            adresBilgileriRepository.deleteById(id);
            return true;
        }
        return false;
    }
}